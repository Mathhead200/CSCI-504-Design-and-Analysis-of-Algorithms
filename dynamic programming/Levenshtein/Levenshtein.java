import java.util.HashMap;
import java.util.Scanner;

class Levenshtein {

	private static final HashMap<UnorderedPair<Substring>, Integer> cache = new HashMap<>();

	/**
	 * Note we do not use dynamic programming in case 3, even though there is recursion
	 * becasue it is simple tail recursion. We can reduce the space used by cache in this way.
	 * 
	 * @param a - First string
	 * @param b - Second string
	 * @return Minimum edit distance
	 */
	private static int distance(Substring a, Substring b) {
		int m = a.length();
		int n = b.length();

		// case 3:
		int head = 0;
		while (head < m && head < n && a.charAt(head) == b.charAt(head))
			head++;
		if (head > 0) {
			a = a.subSequence(head);
			b = b.subSequence(head);
			m -= head;
			n -= head;
		}

		// cases 1, 2: one of the strings is (effectively) empty
		if (m == 0)  return n;
		if (n == 0)  return m;

		// dynamic programming: check cache
		UnorderedPair<Substring> pair = new UnorderedPair<>(a, b);
		Integer distance = cache.get(pair);
		if (distance != null)  return distance;

		// case 4:
		Substring aTail = a.subSequence(1);
		Substring bTail = b.subSequence(1);
		int opt1 = distance(aTail, b);
		int opt2 = distance(a, bTail);
		int opt3 = distance(aTail, bTail);
		distance = 1 + min(opt1, opt2, opt3);
		cache.put(pair, distance);
		return distance;
	}

	private static int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}

	public static int distance(String a, String b) {
		int d = distance(new Substring(a), new Substring(b));
		System.out.println("DEBUG: cache = " + cache);  // TODO: remove
		cache.clear();  // To avoid cache growing too large. TODO: look into better caching algorithms.
		return d;
	}

	private static void test(String a, String b) {
		System.out.printf("distance(%s, %s) = %d%n", a, b, distance(a, b));
	}

	public static void main(String[] args) {
		test("apple", "banana");
		test("pinapple", "apple");
		
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter two string (space separated): ");
			test(sc.next(), sc.next());
		}
	}
}