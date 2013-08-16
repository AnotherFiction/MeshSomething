package fmi.cagd.domain;

import java.io.File;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import fmi.cagd.Mesh;
import fmi.cagd.MeshParser;

public class TriangleTest {

	private static Mesh mesh;

	@BeforeClass
	public static void beforeClass() {
		mesh = MeshParser.load(new File("res/cube.obj"));
	}

	@Test
	public void test() {
		Triangle t = mesh.getTriangles().get(0);

		System.out.println(t);
	}

	@Test
	public void triangles() {
		Assert.assertEquals(mesh.getFaces().size(), mesh.getTriangles().size());
	}
}
