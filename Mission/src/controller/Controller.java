package controller;

import java.util.ArrayList;

import model.Item;
import model.SetBoxes;
import model.SetPlanes;
import usefullFunctions.FillLayer;

public class Controller {

	// Attributes for processing

	// Attributes for box ranking
	final private String priorityRule = "b"; // Ranking priority for a set of
												// box
	final private int sizeOfRankedReturn = 5; // Size of M1 or M2
	final private int whichFrequencyFunction = 1; // Number of the frequency
	// 2, 3}
	// function {1,

	// Algorithm Attributes
	private SetBoxes currentBestFilling = new SetBoxes(); // L
	private long bestFilling_ObjectiveValue; // U*

	// fill_layer(w/, h/, d', U, N'')
	public SetBoxes fill_layer(int width, int height, int LayerDepth, long VolAlreadyPlacedBoxes, SetBoxes boites) {

		// We update the volume of placed boxes
		if (VolAlreadyPlacedBoxes > bestFilling_ObjectiveValue) {
			currentBestFilling = boites;
			bestFilling_ObjectiveValue = VolAlreadyPlacedBoxes;
		}

		// If it remains boxes witch can fit in the space, we continue.
		int l = boites.shortestEdge(); // l = min{w, h, d)
		if (width < l || height < l) {
			return boites;
		} else {
			ArrayList<Integer> M2;

			/** 
			 *  PACK HORIZONTAL STRIP
			 */
			boites.rotateBoxesMaxWidth();
			M2 = selectBestRank(boites);

			for (Integer w : M2) {

				SetBoxes K = fill_single_strip(height, width, LayerDepth, boites, "height");
				boites.exclude(K);
				fill_layer(width - w, height, LayerDepth, VolAlreadyPlacedBoxes + K.getVolume(), boites);
			}

			/** 
			 *  PACK VERTICAL STRIP
			 */
			boites.rotateBoxesMaxHeight();
			M2 = selectBestRank(boites);

			for (Integer h : M2) {
				SetBoxes K = fill_single_strip(height, width, LayerDepth, boites, "width");
				boites.exclude(K);
				fill_layer(width, height - h, LayerDepth, VolAlreadyPlacedBoxes + K.getVolume(), boites);
			}

		}

		return null;

	}

	// Algorithm attributes
	private long Vinit = 0; // V*
	private SetBoxes BoxInside = new SetBoxes(); // X*

	/**
	 * 
	 * @param depth
	 * @param Volume
	 */
	public void chooseDepth(int depth, long Volume, SetBoxes sb, SetPlanes sp) {

		ArrayList<Integer> depths = new ArrayList<Integer>();

		if (Volume > Vinit) {
			Vinit = Volume;
			for (int i = 0; i < currentBestFilling.size(); i++) {
				BoxInside.add(currentBestFilling.get(i));
			}
		}

		for (int i = 0; i < sb.size(); i++) {
			if (depth < sb.get(0).minEdge()) {
				depths = selectBestRank(sb);
			}

			for (int k = 0; k < depths.size(); k++) {
				sb.pairBoxes(depths.get(k));
				currentBestFilling = fill_layer(sp.get(0).getSpaces().get(0).getWidth(),
						sp.get(0).getSpaces().get(0).getHeight(), depth, 0, sb);
				long U = currentBestFilling.getVolume();
				for(int j=0;j<currentBestFilling.size();j++){
					for(int l=0;l<sb.size();l++){
						if(currentBestFilling.get(j)==sb.get(l)){
							sb.remove(sb.get(l));
						}
					}
				}
				chooseDepth(depth - depths.get(k), Volume + U, sb, sp);
			}

		}

	}

	public SetBoxes fill_single_strip(int stripH, int stripW, int stripD, SetBoxes boxes, String contrainte) {
		int capacity = 0;
		SetBoxes K = new SetBoxes();
		ArrayList<Item> feasibleBoxes = new ArrayList<Item>();
		ArrayList<Item> discardedBoxes = new ArrayList<Item>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		int compteur = 0;
		int box1 = 0, strip1 = 0, box2 = 0, strip2 = 0;

		if (contrainte == "heigth") {
			capacity = stripH;
		} else if (contrainte == "width") {
			capacity = stripW;
		}

		for (Item box : boxes) {
			if (contrainte == "height") {
				box2=box.getWidth();
				strip2=stripW;
			} else if (contrainte == "width") {
				box2 = box.getHeight();
				strip2 = stripH;
			}
			for (int i = 0; i < 2; i++) {
				if (box.getDepth() > stripD || box2 > strip2) {

					if (contrainte == "heigth") {
						box.switchDimension("wd");
					} else if (contrainte == "width") {
						box.switchDimension("hd");
					}
					compteur = compteur + 1;
				} else {
					
					feasibleBoxes.add(box);
					if (contrainte == "heigth") {
						a.add(box.getHeight());
					} else if (contrainte == "width") {
						a.add(box.getWidth());
					}
					c.add(box.getHeight() * box.getDepth() * box.getWidth());
					break;
				}
				if (compteur == 2) {
					
					discardedBoxes.add(box);
				}
			}
		}
		// Then solve a KP with capacity h/w
		int[][] keep = new int[feasibleBoxes.size()][a.size()];
		knapsack(capacity, feasibleBoxes.size(), a, c, keep);

		int KK = capacity;
		for (int i = feasibleBoxes.size(); i >= 1; i--) {
			if (keep[i][KK] == 1) {
				K.add(feasibleBoxes.get(i));
				KK = KK - a.get(i);
			}
		}

		return K;
	}

	private int knapsack(int capacity, int numberOfItems, ArrayList<Integer> weights, ArrayList<Integer> values,
			int[][] keep) {
		int[][] m = new int[numberOfItems][weights.size()];
		keep = new int[numberOfItems][weights.size()];
		for (int w = 0; w <= capacity; w++) {
			m[0][w] = 0;
		}
		for (int i = 1; i <= numberOfItems; i++) {
			for (int w = 0; w <= capacity; w++) {
				if (weights.get(i) <= w && values.get(i) + m[i - 1][w - weights.get(i)] > m[i - 1][w]) {
					m[i][w] = values.get(i) + m[i - 1][w - weights.get(i)];
					keep[i][w] = 1;
				} else {
					m[i][w] = m[i - 1][w];
					keep[i][w] = 0;
				}
			}
		}

		return m[numberOfItems][capacity];

	}

	public ArrayList<Integer> selectBestRank(SetBoxes N) {

		// CREATE FREQUENCY FUNCTION
		ArrayList<Integer> f = FillLayer.createFrequencyFunction(N, whichFrequencyFunction);

		// CREATE ArrayList ACCORDING TO THE PriorityRule
		ArrayList<Integer> indexes = FillLayer.generateIndexRankedList(f, priorityRule, sizeOfRankedReturn);

		ArrayList<Integer> res = new ArrayList<Integer>();
		for (Integer i : indexes) {
			res.add(N.getArrayOfPos().get(i));
		}

		// ###print the rank list for debug
		System.out.println("*Ranks");
		for (Integer i : res) {
			System.out.print("\t" + i);
		}
		System.out.println();

		return res;
	}

}
