package fmi.cagd;

import casmi.Applet;
import casmi.AppletRunner;
import casmi.KeyEvent;
import casmi.MouseButton;
import casmi.MouseEvent;
import casmi.graphics.color.Color;
import casmi.graphics.color.RGBColor;
import casmi.graphics.element.Rect;
import casmi.graphics.element.Triangle;
import casmi.matrix.Vertex;

/**
 * casmi main class.
 */
public class App extends Applet {

	
	private Triangle t;
	private Color c = new RGBColor("#0572B9");

	@Override
	public void setup() {
		setSize(1024, 768);
		setFPS(30);

		// create rect element, set size, position and fill color,
//		r = new Rect(320, 240);
//		r.setPosition(512, 384);
//		r.setFillColor(c);
		Vertex v1 = new Vertex(0, 0, 0);
		Vertex v2 = new Vertex(100, 100, 0);
		Vertex v3 = new Vertex(0, 100, 100);
		t = new Triangle(v1, v2, v3);
		t.setPosition(512, 384);
		t.setFillColor(c);
		

		// add rect to rendering object tree
		addObject(t);
	}

	@Override
	public void update() {
//		long value = System.currentTimeMillis();
//
//		// rotate rect
//		double rot = value % (360 * 100);
//		t.setRotation(rot / 100.0, 1.0, 2.0, 1.0);
//
//		// blink rect
//		c.setAlpha((Math.sin(value / 400.0) + 1.0) / 2.0);
//		t.setFillColor(c);
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
		AppletRunner.run("fmi.cagd.App", "This is a sample quick start project");
	}
}