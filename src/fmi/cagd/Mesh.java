package fmi.cagd;

import java.util.List;

import fmi.cagd.entities.Point3D;

public class Mesh {

	private final String name;
	private final List<Point3D> vertices;

	public Mesh(String name, List<Point3D> vertices) {
		this.name = name;
		this.vertices = vertices;
	}

	public String getObjectName() {
		return this.name;
	}

	public List<Point3D> getVertices() {
		return this.vertices;
	}

}
