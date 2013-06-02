package fmi.cagd;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import fmi.cagd.domain.Face;
import fmi.cagd.domain.Point3D;

public class MeshParserTest {

	private Mesh mesh = MeshParser.load(new File("cube.obj"));

	@Test
	public void name() {
		Assert.assertEquals("cube", mesh.getObjectName());
	}

	@Test
	public void numberOfVertices() {
		Assert.assertEquals(8, mesh.getVertices().size());
	}

	@Test
	public void firstAndLastV() {
		Assert.assertEquals(new Point3D(0.0, 0.0, 0.0),
				mesh.getVertices().get(0));
		Assert.assertEquals(new Point3D(1.0, 1.0, 1.0),
				mesh.getVertices().get(mesh.getVertices().size() - 1));
	}

	@Test
	public void numberOfNormals() {
		Assert.assertEquals(6, mesh.getNormals().size());
	}

	@Test
	public void firstAndThirdNormal() {
		Assert.assertEquals(new Point3D(0.0, 0.0, 1.0), mesh.getNormals()
				.get(0));
		Assert.assertEquals(new Point3D(0.0, 1.0, 0.0), mesh.getNormals()
				.get(2));
	}

	@Test
	public void numberOfFaces() {
		Assert.assertEquals(12, mesh.getFaces().size());
	}

	@Test
	public void firstAndLastFaces() {
		Assert.assertEquals(new Face(0,6,4), mesh.getFaces().get(0));
		Assert.assertEquals(new Face(1,7,3), mesh.getFaces().get(mesh.getFaces().size() - 1));
	}
}
