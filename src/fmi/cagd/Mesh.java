package fmi.cagd;

import java.util.List;

import fmi.cagd.domain.Face;
import fmi.cagd.domain.Point3D;

public class Mesh {

	private final String name;
	private final List<Point3D> vertices;
	private final List<Point3D> normals;
	private final List<Face> faces;

	public Mesh(String name, List<Point3D> vertices, List<Point3D> normals, List<Face> faces) {
		this.name = name;
		this.vertices = vertices;
		this.normals = normals;
		this.faces = faces;
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
}
