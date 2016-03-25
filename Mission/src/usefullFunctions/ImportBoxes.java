package usefullFunctions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import model.Item;
import model.setBoxes;

public class ImportBoxes {
	static private Item container;
	static private setBoxes set = new setBoxes();

	static private boolean containerReady = false;
	static private boolean setReady = false;

	public ImportBoxes() {

	}

	/**
	 * Sets the container.
	 *
	 * @param h
	 *            the h
	 * @param w
	 *            the w
	 * @param d
	 *            the d
	 */
	public static void setContainer(int h, int w, int d) {
		container = new Item(h, w, d);
		containerReady = true;
	}

	public static void importBoxes(String path) {
		boolean success = false;

	}

	/**
	 * Export set of boxes into a file.
	 *
	 * @param path
	 *            the path
	 * @return true, if successful
	 */
	public static boolean exportBoxes(String path) {
		boolean success = true;
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(new Date());
			writer.println();

			for (Item i : set) {
				writer.println(i);
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

		setBoxes myBoxes = new setBoxes();
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

	public static setBoxes getSet(){
		if(containerReady && setReady){
			return set;
		}else{
			System.out.println("The container or the set has not been defined");
			return null;
		}
	}
}
