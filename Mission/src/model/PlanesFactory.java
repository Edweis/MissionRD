package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Date;

public class PlanesFactory {

	static private SetPlanes set= new SetPlanes();

	/**
	 * Import planes from a CSV file
	 * 
	 * @param path
	 * @return true if success, no otherwise
	 */
	public static boolean importPlanes(String path) {
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
					p.addSpace(new Item(       Integer.parseInt(heights[i]), Integer.parseInt(widths[i]), Integer.parseInt(depths[i])          ));
				}
				
				
				set.add(p);
				
				line = br.readLine();
			}
			br.close();
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
	public static boolean exportPlanes(String path) {
		boolean success = true;
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.println(new Date());
			writer.println("name;setOfHeight; SetOfWidth; setOfDepth");
			writer.println();

			for (Plane p : set) {
				writer.print(p.getName()+";");
				
				for(Item i : p.getSpaces()){
					writer.print("," +i.getHeight());
				}
				writer.print(";");
				for(Item i : p.getSpaces()){
					writer.print("," + i.getWidth());
				}
				writer.print(";");
				for(Item i : p.getSpaces()){
					writer.print("," + i.getDepth());
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
	public static void generatePlanes(int nbPlanes, int numberMaxOfSpaces, int maxEdge, int minEdge) {

		int w, h, d, nbPlaces;
		Plane p;
		
		

		for (int i = 0; i < nbPlanes; i++) {
			p = new Plane("Plane number " + (i+1));
			
			nbPlaces = (int) (Math.random() * numberMaxOfSpaces + 1);
			
			for (int j = 0; j < nbPlaces; j++) {
				w = (int) (Math.random() * (maxEdge - minEdge) + minEdge) + 1;
				h = (int) (Math.random() * (maxEdge - minEdge) + minEdge) + 1;
				d = (int) (Math.random() * (maxEdge - minEdge) + minEdge) + 1;

				p.addSpace(new Item(h,w,d));
			}
			
			
			set.add(p);
		
		}	

	}

	public static SetPlanes getSet(){
		return set;
	}
}
