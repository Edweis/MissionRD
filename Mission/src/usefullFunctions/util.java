package usefullFunctions;

import java.util.ArrayList;

import model.Item;
import model.setBoxes;

public class util {

	/*
	 * * Return an array of a chosen dimension of a set of boxes
	 * 
	 * @param N = set of boxes
	 * 
	 * @param cote = {"w", "d", "h"} select width, height or depth of all boxes
	 * 
	 * @return Array of boxes chosen dimension
	 */
	public static ArrayList<Integer> extractBoxesEdges(ArrayList<Item> N, String cote) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		switch (cote) {
		case "w":
			for (Item i : N) {
				res.add(i.getWidth());
			}
			break;
		case "h":
			for (Item i : N) {
				res.add(i.getHeight());
			}
			break;
		case "d":
			for (Item i : N) {
				res.add(i.getDepth());
			}
			break;
		}

		return res;
	}



}
