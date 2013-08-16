package fmi.cagd;

import java.util.ArrayList;
import java.util.List;

import fmi.cagd.domain.Face;
import fmi.cagd.domain.Point3D;
import fmi.cagd.domain.Triangle;

public class Mesh {

	private final String name;
	private final List<Point3D> vertices;
	private final List<Point3D> normals;
	private final List<Face> faces;
	private final List<Triangle> triangles;

	public Mesh(String name, List<Point3D> vertices, List<Point3D> normals,
			List<Face> faces) {
		this.name = name;
		this.vertices = vertices;
		this.normals = normals;
		this.faces = faces;
		this.triangles = buildTriangles();
	}

	public String getObjectName() {
		return this.name;
	}

	public List<Point3D> getVertices() {
		return this.vertices;
	}

	public List<Point3D> getNormals() {
		return this.normals;
	}

	public List<Face> getFaces() {
		return this.faces;
	}

	public List<Triangle> getTriangles() {
		return this.triangles;
	}

	public Point3D geometricCenter() {
		Point3D p = new Point3D(0, 0, 0);
		for (Point3D point : vertices) {
			p.x += point.x;
			p.y += point.y;
			p.z += point.z;
		}
		p.x /= vertices.size();
		p.y /= vertices.size();
		p.z /= vertices.size();
		return p;
	}

	public Mesh recenter() {
		Point3D center = geometricCenter();
		for (Point3D p : vertices) {
			p.x -= center.x;
			p.y -= center.y;
			p.z -= center.z;
		}
		return this;
	}

	public Mesh translate(Point3D vector) {
		for (Point3D p : vertices) {
			p.x += vector.x;
			p.y += vector.y;
			p.z += vector.z;
		}
		return this;
	}

	public Double scaleFactor() {
		Point3D max = new Point3D(0, 0, 0);
		Point3D center = geometricCenter();
		for (Point3D p : vertices) {
			if (center.distance(max) < center.distance(p))
				max = p;
		}
		return center.distance(max) * 4;
	}

	public Mesh scale(double scaleFactor) {
		Point3D center = geometricCenter();
		for (Point3D p : vertices) {
			p.x *= scaleFactor;
			p.y *= scaleFactor;
			p.z *= scaleFactor;
		}
		return this;
	}

	private List<Triangle> buildTriangles() {
		List<Triangle> list = new ArrayList<>();
		for (Face face : getFaces()) {
			Point3D a = getVertices().get(face.a);
			Point3D b = getVertices().get(face.b);
			Point3D c = getVertices().get(face.c);
			list.add(new Triangle(a, b, c));
		}
		return list;
	}
}
