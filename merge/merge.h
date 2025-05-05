
/**
 * @brief Merge contents of soted arrays a and b into output array c.
 * 
 * Preconditions:
 * 1. a and b are sorted.
 * 2. None of the arrays (a, b, c) overlap.
 * 
 * @param a A sorted array 
 * @param n Size of array a
 * @param b A sorted array
 * @param m Size of array b
 * @param c Output array. Must be at least size n+m.
 */
template <typename T> void merge(T *a, size_t n, T *b, size_t m, T *c) {
	size_t i = 0;  // index for a
	size_t j = 0;  // index for b
	size_t k = 0;  // index for c

	while (i < n || j < m) {
		if (!(i < n))           // Case 1: array a is out of elements, so
			c[k++] = b[j++];    //     move 1 element from b to c.
		else if (!(j < m))      // Case 2: array b is out of elements, so
			c[k++] = a[i++];    //     move 1 element from a to c.
		else if (a[i] <= b[j])  // Case 3: element in a is smaller (or equal), so
			c[k++] = a[i++];    //     move 1 element from a to c.
		else                    // Case 4: element in b is smaller (not equal), so
			c[k++] = b[j++];    //     move 1 element from b to c.
	}                           // Stop when both arrays a and b are out of elements (or empty).
}
