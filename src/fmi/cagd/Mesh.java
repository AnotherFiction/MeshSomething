package fmi.cagd;

import java.util.List;

import fmi.cagd.entities.Point3D;

public class Mesh {

	private final String name;
	private final List<Point3D> vertices;
	private final List<Point3D> normals;

	public Mesh(String name, List<Point3D> vertices, List<Point3D> normals) {
		this.name = name;
		this.vertices = vertices;
		this.normals = normals;
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

	public List<Point3D> getFaces() {
		// TODO Auto-generated method stub
		return null;
	}
}
