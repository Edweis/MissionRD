package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class PlanesFactory {


	/**
	 * Import planes from a CSV file
	 * 
	 * @param path
	 * @return true if success, no otherwise
	 */
	public static SetPlanes importPlanes(String path) {
		SetPlanes res = null;
		SetPlanes set = new SetPlanes();
		try {

			BufferedReader br = new BufferedReader(new FileReader(path));

			String line;

			System.out.println("*Importing boxes from " + path);
			line = br.readLine();
			System.out.println("\t from : " + line);
			System.out.println();

			br.readLine();// line "height;width;depth"
			br.readLine();// void line

			line = br.readLine();

			String[] lines;
			String[] heights;
			String[] widths;
			String[] depths;
			Plane p;
			while (line != null) {

				lines = line.split(";");

				p = new Plane(lines[0]);

				heights = lines[1].split(",");
				widths = lines[2].split(",");
				depths = lines[3].split(",");

				for (int i = 0; i < depths.length; i++) {
					p.addSpace(new Item(Integer.parseInt(heights[i]), Integer.parseInt(widths[i]),
							Integer.parseInt(depths[i])));
				}

				set.add(p);

				line = br.readLine();
			}
			br.close();

			res = set;
		} catch (Exception e) {
		}

		return res;
	}

	/**
	 * Export set of boxes into a CSV file.
	 *
	 * @param path
	 *            the path
	 * @return true, if successful, false otherwise
	 */
	public static boolean exportPlanes(SetPlanes set, String path) {
		boolean success = true;
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(new Date());
			writer.println("name;setOfHeight; SetOfWidth; setOfDepth");
			writer.println();

			for (Plane p : set) {
				writer.print(p.getName() + ";");
				ArrayList<Item> spaces = p.getSpaces();

				writer.print(spaces.get(0).getHeight());
				for (int i = 1; i < spaces.size(); i++) {
					writer.print("," + spaces.get(i).getHeight());
				}

				writer.print(";" + spaces.get(0).getWidth());
				for (int i = 1; i < spaces.size(); i++) {
					writer.print("," + spaces.get(i).getWidth());
				}

				writer.print(";" + spaces.get(0).getDepth());
				for (int i = 1; i < spaces.size(); i++) {
					writer.print("," + spaces.get(i).getDepth());
				}

				writer.println();
			}
			writer.close();
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	/**
	 * Generate randomly boxes.
	 *
	 * @param nbDifferentBoxes
	 *            the number of different boxes
	 * @param ratio
	 *            the ration volume total
	 * @param lowerEdge
	 *            the lower edge of each boxes
	 * @param longerEdge
	 *            the longer edge of each boxes
	 */
	public static SetPlanes generatePlanes(int nbPlanes, int numberMaxOfSpaces, int maxEdge, int minEdge) {

		SetPlanes set = new SetPlanes();
		int w, h, d, nbPlaces;
		Plane p;

		for (int i = 0; i < nbPlanes; i++) {
			p = new Plane("Plane number " + (i + 1));

			nbPlaces = (int) (Math.random() * numberMaxOfSpaces + 1);

			for (int j = 0; j < nbPlaces; j++) {
				w = (int) (Math.random() * (maxEdge - minEdge) + minEdge) + 1;
				h = (int) (Math.random() * (maxEdge - minEdge) + minEdge) + 1;
				d = (int) (Math.random() * (maxEdge - minEdge) + minEdge) + 1;

				p.addSpace(new Item(h, w, d));
			}

			set.add(p);

		}
		return set;

	}
}
