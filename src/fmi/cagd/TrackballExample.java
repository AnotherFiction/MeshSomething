package fmi.cagd;

import java.io.File;

import casmi.Applet;
import casmi.AppletRunner;
import casmi.KeyEvent;
import casmi.MouseButton;
import casmi.MouseEvent;
import casmi.Trackball;
import casmi.graphics.color.ColorSet;
import casmi.graphics.element.Box;
import casmi.graphics.element.Line;
import casmi.graphics.element.Polygon;
import casmi.graphics.object.Camera;
import casmi.graphics.object.GraphicsObject;
import casmi.graphics.object.Perspective;
import fmi.cagd.domain.Face;
import fmi.cagd.domain.Point3D;

/**
 * Trackball example.
 * 
 * @author T. Takeuchi
 */
public class TrackballExample extends Applet {

	GraphicsObject group;
	Line[] lines = new Line[3];
	Box box;

	Trackball trackball;

	int prvMouseX = 0, prvMouseY = 0;

	Perspective p;
	Camera c;

	@Override
	public void setup() {
		setSize(800, 600);

		group = new GraphicsObject();

		Mesh mesh = MeshParser.load(new File("cube.obj")).recenter();
		Point3D dimm = mesh.geometricCenter();

		for (Face face : mesh.getFaces()) {
			Point3D p1 = mesh.getVertices().get(face.a);
			Point3D p2 = mesh.getVertices().get(face.b);
			Point3D p3 = mesh.getVertices().get(face.c);

			Line l1 = new Line(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z);
			l1.setStrokeColor(ColorSet.GREEN);
			Line l2 = new Line(p1.x, p1.y, p1.z, p3.x, p3.y, p3.z);
			l2.setStrokeColor(ColorSet.GREEN);
			Line l3 = new Line(p3.x, p3.y, p3.z, p2.x, p2.y, p2.z);
			l3.setStrokeColor(ColorSet.GREEN);
			group.add(l1);
			group.add(l2);
			group.add(l3);
			
			Polygon p = new Polygon();
			p.vertex(p1.x, p1.y, p1.z);
			p.vertex(p2.x, p2.y, p2.z);
			p.vertex(p3.x, p3.y, p3.z);
			//Triangle s = new Triangle(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z, p3.x, p3.y, p3.z);
			p.setFillColor(ColorSet.BLUE);
			group.add(p);
		}

		addObject(group);

		p = new Perspective(30.0, (double) getWidth() / (double) getHeight(),
				1.0, 100.0);
		c = new Camera(3.0, 4.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

		setPerspective(p);
		setCamera(c);

		// Create Trackball object.
		trackball = new Trackball(this);
	}

	@Override
	public void update() {
	}

	@Override
	public void mouseEvent(MouseEvent e, MouseButton b) {
		if (e == MouseEvent.PRESSED) {
			prvMouseX = getMouseX();
			prvMouseY = getMouseY();
		}

		if (e == MouseEvent.DRAGGED && b == MouseButton.LEFT) {
			int mouseX = getMouseX();
			int mouseY = getMouseY();

			// Update Trackball.
			trackball.update(mouseX, mouseY, prvMouseX, prvMouseY);

			// Rotate an object with Trackball.
			trackball.rotate(group);

			prvMouseX = mouseX;
			prvMouseY = mouseY;
		}
	}

	@Override
	public void keyEvent(KeyEvent e) {
	}

	public static void main(String[] args) {
		AppletRunner.run("casmi.TrackballExample", "TrackballExample");
	}

}
