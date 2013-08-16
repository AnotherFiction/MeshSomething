package fmi.cagd.domain;

public class Triangle {
	public Point3D a;
	public Point3D b;
	public Point3D c;

	public Triangle(Point3D a, Point3D b, Point3D c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Triangle [a=" + a + ", b=" + b + ", c=" + c + "]";
	}

	public double getHighestAngle() {
		return 0.0;
	}
	
	public double getLowestAngle() {
		return 0.0;
	}
}
