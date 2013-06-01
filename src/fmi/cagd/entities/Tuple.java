package fmi.cagd.entities;

public class Tuple<X, Y> {
	public final X first;
	public Y second;

	public Tuple(X x, Y y) {
		this.first = x;
		this.second = y;
	}
}
