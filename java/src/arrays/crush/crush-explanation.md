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

index -> 1   2   3   4
        [7,  0,  0, -7]
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

In our code, `arr[a - 1]` essentially points to element *a* in our example (the *-1* is there because the given indices are 1-indexed). 





To find the max value in our new solution, we loop through every index and 





Instead of incrementing each element in the range, we will add the value to the beginning index, *a*, and subtract the value from the end index, *b*. 

After we go through each of the queries, we can calculate the max value. To do this we iterate over every index in the array and calculate the rolling sum of every index.











It's difficult to visualize this, so let's go through an example.

Here is our sample input:

```
5 3

1 2 100
2 5 100
3 4 100
```

*n = 5*

We start by creating an array of size *n + 1*:

```
Index ->  1  2  3  4  5  6
         [0, 0, 0, 0, 0, 0]
```





In our code, we add the value *k* to *arr[a - 1]*, so when *a = 1*, we add *k* to index 1. The next step is to subtract value *k* from *arr[b]*. When *b = 5*, we subtract *k* from the last index in our example (index 6) at *arr[5]*. We subtract *k* from *a[b]* instead of *a[b - 1]* because our interval defined from *a* to *b* is *inclusive*. If we subtracted *k* from *arr[b - 1]*, then we would be excluding the index at *a[b]* and would 

Let's get back to our example. We read the first query and get:

```
a = 1
b = 2
k = 100
```

When we add *k* to *arr[0]* (*arr[a - 1]*) and subtract *k* from *arr[2]*, our array 

```
[100, -100, 0, 0, 0, 0]
```


## No writing for today, I set up my dev enviornment on bluearch