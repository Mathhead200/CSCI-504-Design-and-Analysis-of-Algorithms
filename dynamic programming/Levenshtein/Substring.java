


public class Substring implements CharSequence, Comparable<Substring>, Iterable<Character> {
	private CharSequence str;
	private int start;
	private int end;

	private int hash = 0;
	private boolean hashIsZero = false;  // in case hash actually is 0

	public Substring(CharSequence str, int start, int end) {
		this.str = str;
		this.start = start;
		this.end = end;
	}

	public Substring(CharSequence str, int start) {
		this(str, start, str.length());
	}

	public Substring(CharSequence str) {
		this(str, 0, str.length());
	}

	public Substring() {
		this(new String(), 0, 0);
	}

	private int offset(int index) {
		return start + index;
	}

	@Override
	public char charAt(int index) {
		return str.charAt(offset(index));
	}

	@Override
	public int length() {
		return end - start;
	}

	@Override
	public Substring subSequence(int start, int end) {
		return new Substring(str, offset(start), offset(end));
	}

	public Substring subSequence(int start) {
		return new Substring(str, offset(start), end);
	}

	public char[] toCharArray() {
		final int n = length();
		char[] arr = new char[n];
		for (int i = 0; i < length(); i++)
			arr[i] = charAt(i);
		return arr;
	}

	@Override
	public String toString() {
		return new String(toCharArray());
	}

	@Override
	public int compareTo(Substring that) {
		int i = this.start;
		int j = that.start;
		while (true) { 
			boolean iDone = i >= this.str.length();
			boolean jDone = j >= that.str.length();
			if (iDone && jDone)  return 0;
			if (iDone)  return -1;
			if (jDone)  return 1;
			int cmp = that.str.charAt(j) - this.str.charAt(i);
			if (cmp != 0)  return cmp;
			i++;
			j++;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)  return true;
		if (!(obj instanceof Substring))  return false;
		return compareTo((Substring) obj) == 0;
	}

	@Override
	public int hashCode() {
        if (hash != 0 || hashIsZero)  return hash;
        for (int i = start; i < end; i++)
			hash = 31 * hash + (int) str.charAt(i);
		if (hash == 0)  hashIsZero = true;
		return hash;
	}

	public class Iterator implements java.util.Iterator<Character> {
		private int i = start;

		@Override
		public boolean hasNext() {
			return i < end;
		}

		@Override
		public Character next() {
			return str.charAt(i++);
		}
	}

	@Override
	public Iterator iterator() {
		return new Iterator();
	}
}