
#include <iostream>
#include "merge.h"

using namespace std;

int main() {
	int a[] = { 2, 3, 5, 7, 11, 13, 17, 19 };
	const size_t n = sizeof(a) / sizeof(int);
	cout << "n=" << n << '\n';

	int b[] = { 2, 4, 6, 8, 10, 12, 14, 16, 18 };
	const size_t m = sizeof(b) / sizeof(int);
	cout << "m=" << m << '\n';
	
	const size_t k = n + m;
	cout << "k=" << k << '\n';
	int c[k];
	merge(a, n, b, m, c);

	for (size_t i = 0; i < k; i++) {
		if (i != 0)
			cout << ", ";
		cout << c[i];
	}
	cout << '\n';

	return 0;
}
