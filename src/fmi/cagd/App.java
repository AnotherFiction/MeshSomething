package fmi.cagd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import casmi.Applet;
import casmi.AppletRunner;
import casmi.KeyEvent;
import casmi.MouseButton;
import casmi.MouseEvent;
import casmi.graphics.color.Color;
import casmi.graphics.color.RGBColor;
import casmi.graphics.element.Triangle;
import casmi.matrix.Vertex;
import fmi.cagd.domain.Face;
import fmi.cagd.domain.Point3D;

/**
 * casmi main class.
 */
public class App extends Applet {

	private List<Triangle> triangles = new ArrayList<>();;
	private Color c = new RGBColor("#0572B9");

	@Override
	public void setup() {
		setSize(1024, 768);
		setFPS(30);

		Mesh mesh = MeshParser.load(new File("cube.obj")).recenter().scale(50)
				.translate(new Point3D(512, 384, 200));
		for (Face face : mesh.getFaces()) {
			Point3D p1 = mesh.getVertices().get(face.a);
			Vertex v1 = new Vertex(p1.x, p1.y, p1.z);
			Point3D p2 = mesh.getVertices().get(face.b);
			Vertex v2 = new Vertex(p2.x, p2.y, p2.z);
			Point3D p3 = mesh.getVertices().get(face.c);
			Vertex v3 = new Vertex(p3.x, p3.y, p3.z);

			Triangle t = new Triangle(v1, v2, v3);
			// t.setPosition(512, 384);
			t.setFillColor(c);

			triangles.add(t);
			addObject(t);

		}
	}

	@Override
	public void update() {
		long value = System.currentTimeMillis();
		double rot = value % (360 * 100);
		setRotation(rot / 100.0, 1.0, 2.0, 1.0);
		// long value = System.currentTimeMillis();
		//
		// // rotate rect
		// double rot = value % (360 * 100);
		// t.setRotation(rot / 100.0, 1.0, 2.0, 1.0);
		//
		// // blink rect
		// c.setAlpha((Math.sin(value / 400.0) + 1.0) / 2.0);
		// t.setFillColor(c);
	}

	// Comment out if you want to exec terminating processes.
	// This is called when the application is quitted.
	// @Override
	// public void exit() {
	// // Implement here.
	// }

	@Override
	public void mouseEvent(MouseEvent e, MouseButton b) {
		// Implement here.
	}

	@Override
	public void keyEvent(KeyEvent e) {
		if (e == KeyEvent.PRESSED) {
			if (getKeyCode() == 27) { // if pressed ESC
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		AppletRunner
				.run("fmi.cagd.App", "This is a sample quick start project");
	}
}