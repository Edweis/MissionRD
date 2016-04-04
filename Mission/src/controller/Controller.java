package controller;

import java.awt.HeadlessException;
import java.util.ArrayList;
import usefullFunctions.FillLayer;

import model.Item;
import model.SetBoxes;

public class Controller {

	// Attributes for processing

	// Attributes for box ranking
	private String priorityRule = "b"; // Ranking priority for a set of box
	private int sizeOfRankedReturn = 5; // Size of M1 or M2
	private int whichFrequencyFunction = 1; // Number of the frequency function
											// {1,
	// 2, 3}

	// Algorithm Attributes
	private SetBoxes currentBestFilling = new SetBoxes(); // L
	private int bestFilling_ObjectiveValue; // U*

	// fill_layer(w/, h/, d', U, N'')
	public SetBoxes fill_layer(int width, int height, int LayerDepth, int VolAlreadyPlacedBoxes, SetBoxes boites) {

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

			// ---------- PACK VERTICAL STRIP
			boites.rotateBoxesMaxWidth();
			M2 = selectBestRank(boites);

			for (Integer w : M2) {
				// setBoxes K = fill_single_strip(height, boxes);
				// fill_layer(width - w, height, LayerDepth,
				// VolAlreadyPlacedBoxes + K.getVolume(), boites.exclude(K));
			}

			// ---------- PACK VERTICAL STRIP
			boites.rotateBoxesMaxHeight();
			M2 = selectBestRank(boites);

			for (Integer h : M2) {
				// setBoxes K = fill_single_strip(height, boxes);
				// fill_layer(width, height - h, LayerDepth,
				// VolAlreadyPlacedBoxes + util.volumeOfBoxes(K),
				// util.exclude(boites, K));
			}

		}

		return null;

	}

	// Algorithm attributes
	private int Vinit = 0; // V*
	private SetBoxes BoxInside = new SetBoxes(); // X*

	/**
	 * 
	 * @param depth
	 * @param Volume
	 */
	public void chooseDepth(int depth, int Volume,SetBoxes sb) {
		
		ArrayList <Integer> depths = new ArrayList<Integer>();
		
		if (Volume > Vinit) {
			Vinit = Volume;
			for(int i=0;i<currentBestFilling.size();i++){
				BoxInside.add(currentBestFilling.get(i));
			}				
		}
		
		for (int i = 0; i < sb.size(); i++) {
			if (depth < sb.get(0).minEdge()) {
				depths=selectBestRank(sb);
			}
			
		for (int k =0;k<depths.size();k++){
			sb.pairBoxes(???, depths.get(k));
			currentBestFilling=fill_layer(W,H,depth,0,sb);
			chooseDepth(depth-depths.get(k),Volume+U,sb);
		}
		
		}

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
