package usefullFunctions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import model.Item;
import model.SetBoxes;

public class BoxesFactory {
	static private Item container;
	static private SetBoxes set = new SetBoxes();

	static private boolean containerReady = false;
	static private boolean setReady = false;

	/**
	 * Sets the container.
	 *
	 * @param h
	 *            the height
	 * @param w
	 *            the width
	 * @param d
	 *            the depth
	 */
	public static void setContainer(int h, int w, int d) {
		container = new Item(h, w, d);
		containerReady = true;
	}

	/**
	 * Import boxes from a CSV file
	 * @param path
	 * @return true if success, no otherwise
	 */
	public static boolean importBoxes(String path) {
		boolean success = true;

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
			while (line != null) {

				lines = line.split(";");

				// add Item to the set
				set.add(new Item(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]), Integer.parseInt(lines[2])));

				line = br.readLine();
			}
			br.close();
			setReady = true;
		} catch (Exception e) {

			success = false;
		}

		return success;
	}

	/**
	 * Export set of boxes into a CSV file.
	 *
	 * @param path
	 *            the path
	 * @return true, if successful, false otherwise
	 */
	public static boolean exportBoxes(String path) {
		boolean success = true;
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(new Date());
			writer.println("height; width; depth");
			writer.println();

			for (Item i : set) {
				writer.println(i.getHeight() + ";" + i.getWidth() + ";" + i.getDepth());
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
	public static void generateBoxes(int nbDifferentBoxes, double ratio, int lowerEdge, int longerEdge) {

		if (!containerReady) {
			System.out.println("Container has not been defined");
			return;
		}

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
		setReady = true;
	}

	public static SetBoxes getSet() {
		if (containerReady && setReady) {
			return set;
		} else {
			System.out.println("The container or the set has not been defined");
			return null;
		}
	}

	public static Item getContainer() {
		return container;
	}
}
