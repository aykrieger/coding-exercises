## Solution

The simple solution to this problem is to create an array of size `n` and add the value `k` to each array element between the given indices, inclusive. After we go through each query we can calculate the max value by iterating over every element of the array and checking if that element is larger than the current max value.

This solution works, but it takes `O(n * m)` time.

In order to reduce our runtime, we will have to get creative.

Instead of adding the given value to each element in our array, we will add the value to the given beginning index (a) and subtract the value from the given end index (b). 

After we go through each of the queries, we can calculate the max value. To do this we iterate over every index in the array and calculate the rolling sum of every index.

It's difficult to visualize this, so let's go through an example.

Here is our sample input:

```
5 3

1 2 100
2 5 100
3 4 100
```

`n = 5`

We start by creating an array of size `n + 1`:

```
Index ->  1  2  3  4  5  6
         [0, 0, 0, 0, 0, 0]
```

We read the first query and get:

`a = 1`
`b = 2`
`k = 100`

We will treat this array as 1-indexed so index 1 will be at `arr[0]`.  In our code, the beginning index where we add the value to our array is at `arr[a - 1]`. For this example, the beginning index is at `arr[0]` when the `a = 1` and the beginning index is at `arr[9]` when `a = `  






We add 100 to the beginning index (`arr[a - 1]` from our code) and subtract 100 from our end index (`arr[b]` from our code):

```
[100, -100, 0, 0, 0, 0]
```

