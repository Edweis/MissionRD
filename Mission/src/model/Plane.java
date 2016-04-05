package model;

import java.util.ArrayList;

/**
 * 
 * @author piou
 * 
 *         A plane with his name and all of his spaces assimilated as Items
 *
 */
public class Plane {

	private String name;
	private ArrayList<Item> spaces;

	public Plane(String s) {
		name = s;
		spaces = new ArrayList<Item>();
	}

	public void addSpace(Item i) {
		spaces.add(i);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Item> getSpaces() {
		return spaces;
	}

	public String toString() {
		String s;

		s = "Plane name : " + name;

		for (Item i : spaces) {
			s = s + "\n\t" + i;
		}

		return s;
	}
}
