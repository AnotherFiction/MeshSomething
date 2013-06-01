package fmi.cagd;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fmi.cagd.entities.Edge;
import fmi.cagd.entities.Point3D;
import fmi.cagd.entities.Tuple;

/**
 * Rather trivial class name.
 * 
 * @author kiril
 * 
 */
public class MainAppletClass extends Applet implements MouseListener,
		MouseMotionListener {

	int width;
	int height;
	int mx;
	int my; // the most recently recorded mouse coordinates

	private Image backbuffer;
	private Graphics backg;

	int azimuth = 35;
	int elevation = 30;

	List<Tuple<Point3D, Point>> vertices;
	List<Edge> edges;

	public void init() {
		this.setSize(getSize());
		width = getSize().width;
		height = getSize().height;

		String file = "";
		try {
			file = FileUtils.readFileToString(new File("cube.obj"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(file);

		vertices = new ArrayList<>();
		vertices.add(new Tuple<Point3D, Point>(new Point3D(-1, -1.5, -1), null));
		vertices.add(new Tuple<Point3D, Point>(new Point3D(-1, -1, 1), null));
		vertices.add(new Tuple<Point3D, Point>(new Point3D(-1, 1, -1), null));
		vertices.add(new Tuple<Point3D, Point>(new Point3D(-1, 1, 1), null));
		vertices.add(new Tuple<Point3D, Point>(new Point3D(1, -1, -1), null));
		vertices.add(new Tuple<Point3D, Point>(new Point3D(1, -1, 1), null));
		vertices.add(new Tuple<Point3D, Point>(new Point3D(1, 1, -1), null));
		vertices.add(new Tuple<Point3D, Point>(new Point3D(1, 1, 1), null));

		edges = new ArrayList<>();
		edges.add(new Edge(0, 1));
		edges.add(new Edge(0, 2));
		edges.add(new Edge(0, 4));
		edges.add(new Edge(1, 3));
		edges.add(new Edge(1, 5));
		edges.add(new Edge(2, 3));
		edges.add(new Edge(2, 6));
		edges.add(new Edge(3, 7));
		edges.add(new Edge(4, 5));
		edges.add(new Edge(4, 6));
		edges.add(new Edge(5, 7));
		// edges.add(new Edge(6, 7));

		backbuffer = createImage(width, height);
		backg = backbuffer.getGraphics();
		drawWireframe(backg);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	void drawWireframe(Graphics g) {

		// compute coefficients for the projection
		final double theta = Math.PI * azimuth / 180.0;
		final double phi = Math.PI * elevation / 180.0;
		final float cosT = (float) Math.cos(theta), sinT = (float) Math
				.sin(theta);
		final float cosP = (float) Math.cos(phi), sinP = (float) Math.sin(phi);
		final float cosTcosP = cosT * cosP, cosTsinP = cosT * sinP, sinTcosP = sinT
				* cosP, sinTsinP = sinT * sinP;

		// project vertices onto the 2D viewport
		// final Point[] points = new Point[vertices.size()];

		final int scaleFactor = width / 4;
		final float near = 3; // distance from eye to near plane
		final float nearToObj = 1.5f; // distance from near plane to center of
										// object
		for (Tuple<Point3D, Point> tuple : vertices) {
			double x0 = tuple.first.x;
			double y0 = tuple.first.y;
			double z0 = tuple.first.z;

			// compute an orthographic projection
			double x1 = cosT * x0 + sinT * z0;
			double y1 = -sinTsinP * x0 + cosP * y0 + cosTsinP * z0;

			// now adjust things to get a perspective projection
			double z1 = cosTcosP * z0 - sinTcosP * x0 - sinP * y0;
			x1 = x1 * near / (z1 + near + nearToObj);
			y1 = y1 * near / (z1 + near + nearToObj);

			// the 0.5 is to round off when converting to int
			tuple.second = new Point(
					(int) (width / 2 + scaleFactor * x1 + 0.5), (int) (height
							/ 2 - scaleFactor * y1 + 0.5));
		}

		// draw the wireframe
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		for (Edge edge : edges) {
			g.drawLine(vertices.get(edge.a).second.x,
					vertices.get(edge.a).second.y,
					vertices.get(edge.b).second.x,
					vertices.get(edge.b).second.y);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		e.consume();
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		// get the latest mouse position
		int new_mx = e.getX();
		int new_my = e.getY();

		// adjust angles according to the distance travelled by the mouse
		// since the last event
		azimuth -= new_mx - mx;
		elevation += new_my - my;

		// update the backbuffer
		drawWireframe(backg);

		// update our data
		mx = new_mx;
		my = new_my;

		repaint();
		e.consume();
	}

	@Override
	public void update(Graphics g) {
		g.drawImage(backbuffer, 0, 0, this);
		showStatus("Elev: " + elevation + " deg, Azim: " + azimuth + " deg");
	}

	@Override
	public void paint(Graphics g) {
		update(g);
	}

	@Override
	public Dimension getSize() {
		return new Dimension(640, 480);
	}
}