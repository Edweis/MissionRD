package usefullFunctions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import model.Item;
import model.SetBoxes;

public class BoxesFactory {

	/**
	 * Import boxes from a CSV file
	 * 
	 * @param path
	 * @return true if success, no otherwise
	 */
	public static SetBoxes importBoxes(String path) {
		SetBoxes res = null;

		try {
			SetBoxes set = new SetBoxes();
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
			while (line != null) {

				lines = line.split(";");

				// add Item to the set
				set.add(new Item(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]), Integer.parseInt(lines[2])));

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
	public static boolean exportBoxes(SetBoxes set, String path) {

		boolean success;
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(new Date());
			writer.println("height; width; depth");
			writer.println();

			for (Item i : set) {
				writer.println(i.getHeight() + ";" + i.getWidth() + ";" + i.getDepth());
			}

			writer.close();
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	/**
	 * Generate randomly boxes.
	 *
	 * @param container
	 *            the container
	 * @param nbDifferentBoxes
	 *            the number of different boxes
	 * @param ratio
	 *            the ration volume total
	 * @param lowerEdge
	 *            the lower edge of each boxes
	 * @param longerEdge
	 *            the longer edge of each boxes
	 */
	public static SetBoxes generateBoxes(Item container, int nbDifferentBoxes, double ratio, int lowerEdge,
			int longerEdge) {

	

		SetBoxes set = new SetBoxes();

		SetBoxes myBoxes = new SetBoxes();
		ArrayList<Integer> nbBoxes = new ArrayList<Integer>();

		// Generate boxes that will be used
		int w, h, d;
		for (int i = 0; i < nbDifferentBoxes; i++) {
			w = (int) (Math.random() * (longerEdge - lowerEdge) + lowerEdge) + 1;
			h = (int) (Math.random() * (longerEdge - lowerEdge) + lowerEdge) + 1;
			d = (int) (Math.random() * (longerEdge - lowerEdge) + lowerEdge) + 1;

			myBoxes.add(new Item(w, h, d));
			nbBoxes.add(0);
		}

		// Choose randomly boxes until the volume desired is reached
		System.out.println("\n*************Generate Boxes***************");
		int volume = 0;
		int i;
		while (volume * ratio < container.getVolume()) {
			i = (int) (Math.random() * nbDifferentBoxes);
			set.add(myBoxes.get(i));
			volume += myBoxes.get(i).getVolume();
			nbBoxes.set(i, nbBoxes.get(i) + 1);

			System.out.println("added : " + myBoxes.get(i));
		}

		System.out.println("\nIn short***");
		System.out.println("added " + set.size() + " boxes");
		System.out.println("\t with : ");
		for (int j = 0; j < nbBoxes.size(); j++) {
			System.out.println("\t \t" + nbBoxes.get(j) + " boxe of type : " + myBoxes.get(j));
		}

		System.out.println("using " + Double.toString(100 * (double) ((double) container.getVolume() / volume))
				+ "% of the total volume");

		System.out.println("\n*************End Generate Boxes***************");

		return set;
	}

}
