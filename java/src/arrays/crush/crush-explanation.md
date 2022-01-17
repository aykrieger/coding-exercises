# Algorithmic Crush

I was working through a coding problem I found on HackerRank and I wanted to explain my thought process and present an efficient solution.

You can check out the problem on HackerRank [here](https://www.hackerrank.com/challenges/crush/problem).

## Problem

You are given the size of a 1-indexed array and a list of operations to perform. Perform each operation on the array and return the maximum value of the array after all operations have been completed.

Let's go through an example.

The size of the array is *n = 8* and the number of operations is *m = 3*. 

```
n = 8
m = 3
```

The operations are given as a list of queries where each query consists of *a*, the left index, *b*, the right index, and *k*, the value to add to the array. To perform an operation, add the value *k* to each array element between the two given indices, inclusive.

Here is the given list of queries:

```
a b k
1 4 5
2 5 3
4 8 1
```

Here is the array after each operation:

```
index -> 1  2  3  4  5  6  7  8     a b k
        [0, 0, 0, 0, 0, 0, 0, 0]
		[5, 5, 5, 5, 0, 0, 0, 0]    1 4 5
		[5, 8, 8, 8, 3, 0, 0, 0]    2 5 3
        [5, 8, 8, 9, 4, 1, 1, 1]    4 8 1
```

The maximum value in the final array is 9.

Here are the problem constraints:

```
3 <= n <= 10^7
1 <= m < 2 * 10^5
1 <= a <= b <= n
0 <= k <= 10^9
```

## Solution

The simple solution to this problem is to create an array of size *n* and add the value *k* to each array element between the given indices, inclusive. While we're adding *k* to the array, we keep track of the largest value we've seen with a variable, *max*, and update it whenever we see a larger value.

Here is the simple solution in Java:

```java
long[] arr = new long[n];
long max = 0;

for (List<Integer> query : queries) {
	int a = query.get(0);
	int b = query.get(1);
	long k = query.get(2);

	for (int i = a; i < b + 1; i++) {
		arr[i - 1] = arr[i - 1] + k;
		max = Math.max(max, arr[i - 1]);
	}
}
return max;
```

This solution works, but its runtime is *O(n × m)* because in the worst case scenario we're updating *n* elements of the array *m* times.

The space complexity is *O(n)* because we're storing an an array of *n* elements.

How can we improve this?

### My Thought Process

I began to think of a more efficient solution to this problem. What is the bare minimum amount of data we need to store? Can we get away with storing less than *n* elements?

I thought of a solution where instead of using an array of size *n*, I would create a hashmap that maps each index we update to its value. That would save space in the case where *n* is large and *m* is small. It would take up more space than a simple array when *m* is as large than *n* though. Similarly, the runtime would be better than the simple array solution if the final array is sparse, and worse when the final array is dense. I don't think that solution would be more efficient for most scenarios.

For the worst case scenario when the final array is dense, I can't think of a more efficient solution than a simple array. 

How can we reduce the runtime?

I thought about how updating every element between *a* and *b* could be made more efficient. What if we stored *k* and the difference between *a* and *b* at index *a* in a list?

For example, if *n = 3* and the first query is:

```
a b k
1 3 5
```

We would create a list of *n* elements, with each element storing *k* and the difference between *a* and *b*, and store this as:

```
[ [5, 2], [0, 0], [0, 0] ]
```

With *k* = 5 and *b - a = 2*. Then to calculate the max value we would iterate over every element in the list. The first element would tell us this index's value is 5 and we need to add 5 to the next two elements. 

Wait, that wouldn't work. What if the next query was:

```
a b k
1 2 7
```

We can't store two different values of *k* with different ranges at the first index. What if we created a two dimensional dynamic list like this?

```
[ [5, 2], [0, 0], [0, 0] ]
    |
    V
  [7, 1]
```

That begins to make things complicated. I would have to make multiple stacks and pop elements in the right order to take into account overlapping query ranges. I don't think this would more efficient than the simple solution.

I looked to the Internet for a tip.

### Efficient Solution

I read a creative solution where they they solved the problem of overlapping indices. Let's go back to the example where *n = 3* and the first query is:

```
a b k
1 3 5
```

We create an array of size *n + 1*. Then we add *k* to index *a* and subtract *k* from index *b + 1*. Here is what the array would look like after the first operation:

```
[5, 0, 0, -5]
```

To find the max value, we first keep track of a new variable called *sum*. We iterate over the array and add the value at the current index to *sum*. At every index we check if *sum* is larger than the current max value. When we reach the end of the array, we will have the maximum value.

Let's find the max value of the example. I labeled what the values for *sum* and *max* are at every index:

```
       [5, 0, 0, -5]
sum ->  5  5  5  0
max ->  5  5  5  5
```

At the first index, we add 5 to the sum, then add 0, add 0, and add -5 at the last index. The maximum value *sum* reached was 5, and that's the answer.

What happens when the second query is:

```
a b k
1 2 7
```

We add 7 to index *a* (1), subtract 7 from index *b + 1* (3), and go through the same process to calculate the max value. Here is the array:

```
[12, 0, -7, -5]
```

Here are *sum* and *max* at every index:

```
       [12,   0,  -7,  -5]
sum ->  12   12    5    0
max ->  12   12   12   12
```

When we reach the end of the array, *max* is 12 and that is the answer.

Here is the efficient solution in Java:

```java
long[] arr = new long[n + 1];

for (int i = 0; i < queries.size(); i++) {
	List<Integer> query = queries.get(i);
	int a = query.get(0);
	int b = query.get(1);
	int k = query.get(2);
	arr[a - 1] += k;
	arr[b] -= k;
}

long sum = 0;
long max = 0;
for (int i = 0; i < n; i++) {
	sum += arr[i];
	max = Math.max(max, sum);
}

return max;
```

What is the runtime and space complexity of the efficient solution?

The runtime of the algorithm is *O(n + m)* because we have to initially add *2m* values to the array and then iterate over *n* elements to calculate the max value.

The space complexity is the same as the simple solution, *O(n)*, because we're storing an array of size *n + 1*.