package fmi.cagd;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fmi.cagd.entities.Point3D;

public class MeshParser {

	public static Mesh load(final File file) {
		String m = "";
		try {
			m = FileUtils.readFileToString(new File("cube.obj"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> lines = Arrays.asList(m.split("\\n"));

		String name = getName(lines);
		List<Point3D> v = getVertices(lines);

		// System.out.println(m);

		return new Mesh(name, v);
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
				//TODO: 4th coordinate?
				result.add(new Point3D(x, y, z));
			}
		}
		return result;
	}
	
	private static List<Point3D> getFaces(List<String> lines) {
		List<Point3D> result = new ArrayList<>();
		for (String line : lines) {
			if (line.trim().startsWith("f ")) {
				String s = reduceWitespaces(line.substring(2));
				List<String> nums = Arrays.asList(s.split(" "));
				Double x = Double.valueOf(nums.get(0));
				Double y = Double.valueOf(nums.get(1));
				Double z = Double.valueOf(nums.get(2));
				//TODO: 4th coordinate?
				result.add(new Point3D(x, y, z));
			}
		}
		return result;
	}

	private static String reduceWitespaces(String s) {
		while(s.contains("  "))
			s = s.replace("  ", " ");
		return s.trim();
	}
}
