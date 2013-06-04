package fmi.cagd;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fmi.cagd.domain.Face;
import fmi.cagd.domain.Point3D;

public class MeshParser {

	public static Mesh load(final File file) {
		String m = "";
		try {
			m = FileUtils.readFileToString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> lines = Arrays.asList(m.split("\\n"));

		String name = getName(lines);
		List<Point3D> v = getVertices(lines);
		List<Point3D> n = getNormals(lines);
		List<Face> f = getFaces(lines);

		return new Mesh(name, v, n, f);
	}

	private static String getName(List<String> lines) {
		for (String line : lines) {
			if (line.trim().startsWith("g "))
				return reduceWitespaces(line.substring(2));
		}
		return null;
	}

	private static List<Point3D> getVertices(List<String> lines) {
		List<Point3D> result = new ArrayList<>();
		for (String line : lines) {
			if (line.trim().startsWith("v ")) {
				String s = reduceWitespaces(line.substring(2));
				List<String> nums = Arrays.asList(s.split(" "));
				Double x = Double.valueOf(nums.get(0));
				Double y = Double.valueOf(nums.get(1));
				Double z = Double.valueOf(nums.get(2));
				// TODO: 4th coordinate?
				result.add(new Point3D(x, y, z));
			}
		}
		return result;
	}

	private static List<Point3D> getNormals(List<String> lines) {
		List<Point3D> result = new ArrayList<>();
		for (String line : lines) {
			if (line.trim().startsWith("vn ")) {
				String s = reduceWitespaces(line.substring(2));
				List<String> nums = Arrays.asList(s.split(" "));
				Double x = Double.valueOf(nums.get(0));
				Double y = Double.valueOf(nums.get(1));
				Double z = Double.valueOf(nums.get(2));
				// TODO: 4th coordinate?
				result.add(new Point3D(x, y, z));
			}
		}
		return result;
	}

	private static List<Face> getFaces(List<String> lines) {
		List<Face> result = new ArrayList<>();
		for (String line : lines) {
			if (line.trim().startsWith("f ")) {
				String s = reduceWitespaces(line.substring(2));
				List<String> nums = Arrays.asList(s.split(" "));
				int x = Integer.valueOf(nums.get(0).split("/")[0]) - 1;
				int y = Integer.valueOf(nums.get(1).split("/")[0]) - 1;
				int z = Integer.valueOf(nums.get(2).split("/")[0]) - 1;
				result.add(new Face(x, y, z));
			}
		}
		return result;
	}

	private static String reduceWitespaces(String s) {
		while (s.contains("  "))
			s = s.replace("  ", " ");
		return s.trim();
	}
}
