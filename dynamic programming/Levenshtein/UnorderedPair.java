
public final class UnorderedPair<T> {
	public final T a, b;

	public UnorderedPair(T a, T b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)  return true;
		if (!(obj instanceof UnorderedPair))  return false;
		UnorderedPair that = (UnorderedPair) obj;
		return (this.a.equals(that.a) && this.b.equals(that.b))
		    || (this.a.equals(that.b) && this.b.equals(that.a));
	}

	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();  // communative hashing function
	}

	@Override
	public String toString() {
		return new StringBuilder("{").append(a).append(", ").append(b).append("}").toString();
	}
}