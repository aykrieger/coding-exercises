## Solution

The simple solution to this problem is to create an array of size *n* and add the value *k* to each array element between the given indices, inclusive. After we go through each query we can calculate the max value by iterating over every element of the array and checking if that element is larger than the current max value.

This solution works, but it takes *O(n * m)* time.

In order to reduce our runtime, we will have to get creative.

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

