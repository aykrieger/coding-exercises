## Solution

The simple solution to this problem is to create an array of size *n* and add the value *k* to each array element between the given indices, inclusive. While we're adding *k* to our array elements, we keep track of the largest value we've seen with a variable, *max*, and update it whenever we see a larger value.

Here is the simple solution in Java:

```java
long[] arr = new long[n];
long max = 0;

for (List<Integer> query : queries) {
	int start = query.get(0);
 	int end = query.get(1);
	long val = query.get(2);

	for (int i = start; i < end + 1; i++) {
		arr[i - 1] = arr[i - 1] + val;
		max = Math.max(max, arr[i - 1]);
	}
}
return max;
```

This solution works, but its runtime is *O(n × m)* because in the worst case scenario we're updating *n* rows of our array *m* times.

The space complexity is *O(n)* because we're storing an an array of *n* elements.

How can we improve this?

### Efficient Solution

I began to think of a more efficient solution to this problem. What is the bare minimum amount of data I need to store? Can I get away with storing less than *n* elements?

I thought of a solution where instead of using an array of size *n*, I would create a hashmap that maps each index we update to its value. That would save some space in the case where *n* is large and *m* is small. It would take up more space than a simple array when *m* is as large or larger than *n* though. Similarly, the runtime would be better when than the simple array solution if the final array is sparse, and worse when the final array is dense.

I think an array of size *n* is the best we can do in the worst case scenario.










In order to reduce our runtime, we will have to get creative.

Instead of adding *k* to each array element between the left and right indices, we're going to add *k* to the left index and subtract *k* from the element immediately to the right of the right index.  

Let's say *n = 3* and our first query is:

```
a = 1
b = 3
k = 7
```

In our simple solution we would create an array with *n* (3) elements and add *k* (7) to each element between indices *a* (1) and *b* (3), inclusive:

```
Simple Solution

index -> 1  2  3
        [7, 7, 7]
```

In our new solution we start by creating an array with *n + 1* (4) elements. For each query, we add *k* (7) to index *a* (1) and subtract *k* (7) from index *b + 1* (4).

```
New Solution

index -> 1  2  3  4
        [7, 0, 0, -7]
```

Here is our new solution so far:

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
```

In our problem statement, the given indices are 1-indexed, and in our code, the array is 0-indexed. So instead of adding *k* to *a*, we add *k* to *arr[a - 1]*. Instead of subtracting *k* from *b + 1*, we subtract *k* from *arr[b]*.

Back to our example. Here's our array after the first update in the new solution:

```
index -> 1   2   3   4
        [7,  0,  0, -7]
```

Let's say the next query is:

```
a = 3
b = 3
k = 5
```

After the update, our array will look like:

```
index -> 1  2  3  4
        [7, 0, 5, -12]
```

The next query is:

```
a = 2
b = 2
k = 10
```

And after the final update:

```
index -> 1   2   3   4
        [7,  10, -5, -12]
```

To find the max value in our new solution, we loop through our array from left to right and add each element to a *sum* variable. We also store the max value we've seen so far in a *max* variable.

```
index -> 1   2   3   4
        [7,  10, -5, -12]
sum   -> 7   17  12  0
max   -> 7   17  17  17
```

The value of *max* is 17 when we reach the end of the array and that's our answer. Here is the code for the full solution:

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

