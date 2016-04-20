package controller;

import java.util.ArrayList;

import model.Item;
import model.SetBoxes;
import model.SetPlanes;
import model.Solution;
import usefullFunctions.FillLayer;

public class Controller {

	// Attributes for processing

	// Attributes for box ranking
	final private String priorityRule = "b"; // Ranking priority for a set of
												// boxes
	final private int sizeOfRankedReturn = 3; // Size of M1 or M2
	final private int whichFrequencyFunction = 2; // Number of the frequency
													// function {1, 2, 3}

	// fill_layer(w/, h/, d', U, N'')
	public SetBoxes fill_layer(int height, int width, int LayerDepth, long VolAlreadyPlacedBoxes, SetBoxes boites)
			throws Throwable {

		SetBoxes res = new SetBoxes();

		// We update the volume of placed boxes
		if (VolAlreadyPlacedBoxes > Solution.FLvolume) {
			Solution.FLplacedBoxes = boites;
			Solution.FLvolume = VolAlreadyPlacedBoxes;

			System.out.println("*****\n NOUVEL AJOUT !");
			System.out.println("  with " + boites.size() + " boxes");
			System.out.println("  and a total volume of " + 0.1 * VolAlreadyPlacedBoxes / 100000 + " m³");
			System.out.println("*****");
		}

		// If it remains boxes witch can fit in the space, we continue.
		int l = boites.shortestEdge(); // l = min{w, h, d)
		if (width < l || height < l) {
			return res;
		} else {
			ArrayList<Integer> M2;

			/**
			 * PACK HORIZONTAL STRIP
			 */
			boites.rotateBoxesMinWidth();

			M2 = selectBestRank(boites);

			SetBoxes cpBoites;
			for (Integer w : M2) {
				cpBoites = boites.clone();

				System.out.println("**Horiontal** bounds = " + w + "  #boxes = " + cpBoites.size());
				SetBoxes K = fill_single_strip(height, w, LayerDepth, cpBoites, "height");
				cpBoites.exclude(K);
				SetBoxes J = fill_layer(height, width - w, LayerDepth, VolAlreadyPlacedBoxes + K.getVolume(), cpBoites);
				System.out.println("\t J : " + J);
			}

			/**
			 * PACK VERTICAL STRIP
			 */
			boites.rotateBoxesMinHeight();
			M2 = selectBestRank(boites);

			for (Integer h : M2) {
				cpBoites = boites.clone();

				System.out.println("**Vertical  bounds = " + h + "  #boxes = " + cpBoites.size());
				SetBoxes K = fill_single_strip(h, width, LayerDepth, cpBoites, "width");
				cpBoites.exclude(K);
				System.out.println("\t K : " + K);

				SetBoxes J = fill_layer(height - h, width, LayerDepth, VolAlreadyPlacedBoxes + K.getVolume(), cpBoites);
				System.out.println("\t J : " + J);
			}

		}

		return Solution.FLplacedBoxes;

	}

	// Algorithm attributes
	private long Vinit = 0; // V*
	private SetBoxes BoxInside = new SetBoxes(); // X*

	/**
	 * @param depth
	 * @param v
	 * @throws Throwable
	 */
	public SetBoxes chooseDepth(int depth, long v, SetBoxes sb, SetPlanes sp) throws Throwable {

		ArrayList<Integer> depths = new ArrayList<Integer>();

		if (v > Vinit) {
			Vinit = (long) v;
			for (int i = 0; i < Solution.FLplacedBoxes.size(); i++) {
				BoxInside.add(Solution.FLplacedBoxes.get(i));
			}
		}

		for (int i = 0; i < sb.size(); i++) {
			if (depth < sb.get(i).minEdge()) {
				return null;
			}
		}
		depths = selectBestRank(sb);
		for (int k = 0; k < depths.size(); k++) {
			if (sb.size() > 1) {
				sb.pairBoxes(depths.get(k));
			}
			Solution.FLplacedBoxes = fill_layer(sp.get(0).getSpaces().get(0).getWidth(),
					sp.get(0).getSpaces().get(0).getHeight(), depth, 0, sb);
			long U = Solution.FLplacedBoxes.getVolume();
			for (int j = 0; j < Solution.FLplacedBoxes.size(); j++) {
				for (int l = 0; l < sb.size(); l++) {
					if (Solution.FLplacedBoxes.get(j) == sb.get(l)) {
						sb.remove(sb.get(l));

					}
				}
			}
			chooseDepth(depth - depths.get(k), v + U, sb, sp);
		}
return BoxInside;
	}

	public SetBoxes fill_single_strip(int stripH, int stripW, int stripD, SetBoxes boxes, String contrainte) {
		int capacity = 0;
		SetBoxes K = new SetBoxes();
		ArrayList<Item> feasibleBoxes = new ArrayList<Item>();
		ArrayList<Item> discardedBoxes = new ArrayList<Item>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		int compteur = 0;
		int box2 = 0, strip2 = 0;

		if (contrainte == "height") {
			capacity = stripH;
		} else if (contrainte == "width") {
			capacity = stripW;
		}

		for (Item box : boxes) {
			compteur = 0;
			if (contrainte == "height") {
				box2 = box.getWidth();
				strip2 = stripW;
			} else if (contrainte == "width") {
				box2 = box.getHeight();
				strip2 = stripH;
			}
			for (int i = 0; i < 2; i++) {
				if (box.getDepth() > stripD || box2 > strip2) {

					if (contrainte == "height") {
						box.switchDimension("wd");
						box2 = box.getWidth();
					} else if (contrainte == "width") {
						box.switchDimension("hd");
						box2 = box.getHeight();
					}
					compteur = compteur + 1;
				} else {

					feasibleBoxes.add(box);
					if (contrainte == "height") {
						a.add(box.getHeight());
					} else if (contrainte == "width") {
						a.add(box.getWidth());
					}
					c.add(box.getHeight() * box.getDepth() * box.getWidth());
					break;
				}
			}
			if (compteur == 2) {

				discardedBoxes.add(box);
			}
		}
		// Then solve a KP with capacity h/w
		int[][] keep = new int[feasibleBoxes.size() + 1][capacity + 1];
		keep = knapsack(capacity, feasibleBoxes.size(), a, c);

		int KK = capacity;
		for (int i = feasibleBoxes.size(); i >= 1; i--) {
			if (keep[i][KK] == 1) {
				K.add(feasibleBoxes.get(i - 1));
				KK = KK - a.get(i - 1);
			}
		}

		return K;
	}

	private int[][] knapsack(int capacity, int numberOfItems, ArrayList<Integer> weights, ArrayList<Integer> values) {
		int[][] m = new int[numberOfItems + 1][capacity + 1];
		int[][] keep = new int[numberOfItems + 1][capacity + 1];
		for (int w = 0; w <= capacity; w++) {
			m[0][w] = 0;
		}
		for (int i = 1; i <= numberOfItems; i++) {
			for (int w = 0; w <= capacity; w++) {
				if (weights.get(i - 1) <= w && values.get(i - 1) + m[i - 1][w - weights.get(i - 1)] > m[i - 1][w]) {
					m[i][w] = Math.max(values.get(i - 1) + m[i - 1][w - weights.get(i - 1)], m[i - 1][w]);
					keep[i][w] = 1;
				} else {
					m[i][w] = m[i - 1][w];
					keep[i][w] = 0;
				}
			}
		}

		return keep;

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
		// System.out.println("*Ranks");
		// for (Integer i : res) {
		// System.out.print("\t" + i);
		// }
		// System.out.println();

		return res;
	}

}
