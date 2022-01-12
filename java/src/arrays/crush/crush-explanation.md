## Solution

The simple solution to this problem is to create an array of size *n* and add the value *k* to each array element between the given indices, inclusive. While we're adding *k* to our array elements, we check if the sum of the current element and *k* is larger than our max value. If it is, we replace the max value with the sum we just calculated.

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

This solution works, but it takes *O(n × m)* time because in the worst case scenario we're updating *n* rows of our array *m* times.

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

To find the max value in our new solution, we loop through our array from left to right and add that value to our *sum* variable. We store the max value we've seen so far in our *max* variable and when we reach the end of the array we've arrived at our answer.

```
index -> 1   2   3   4
        [7,  10, -5, -12]
sum   -> 7   17  12  0
max   -> 7   17  17  17
```


## No writing for today, I set up my dev enviornment on bluearch