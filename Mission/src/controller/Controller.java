package controller;

import java.awt.HeadlessException;
import java.util.ArrayList;
import usefullFunctions.FillLayer;
import usefullFunctions.util;


import model.Item;
import model.setBoxes;

public class Controller {
	// Attributes for processing
	

	// Attributes for box ranking
	private String priorityRule = "b"; // Ranking priority for a set of box
	private int sizeOfRankedReturn = 5; // Size of M1 or M2
	private int whichFrequencyFunction = 1; // Number of the frequency function {1,
										// 2, 3}

	// Algorithm Attributes
	private setBoxes currentBestFilling = new setBoxes(); // L
	private int bestFilling_ObjectiveValue; // U*

	// fill_layer(w/, h/, d', U, N'')
	public setBoxes fill_layer(int width, int height, int LayerDepth, int VolAlreadyPlacedBoxes,
			setBoxes boites) {

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
			boites.rotateBoxesMaxWidth();;
			M2 = selectBestRank(boites);

			for (Integer w : M2) {
				//setBoxes K = fill_single_strip(height, boites);
				//fill_layer(width - w, height, LayerDepth, VolAlreadyPlacedBoxes + K.getVolume(), boites.exclude(K));
			}

			
			
			
			// ---------- PACK VERTICAL STRIP
			boites.rotateBoxesMaxHeight();
			M2 = selectBestRank(boites);

			for (Integer h : M2) {
				//setBoxes K = fill_single_strip(height, boites);
				//fill_layer(width, height - h, LayerDepth, VolAlreadyPlacedBoxes + util.volumeOfBoxes(K), util.exclude(boites, K));
			}

		}
		
		
		return null;

	}

	private ArrayList<Integer> selectBestRank(setBoxes N) {

		// CREATE FREQUENCY FUNCTION
		ArrayList<Integer> f = FillLayer.createFrequencyFunction(N, whichFrequencyFunction);

		// CREATE ArrayList ACCORDING TO THE PriorityRule
		ArrayList<Integer> res = FillLayer.generateIndexRankedList(f, priorityRule, sizeOfRankedReturn);

		return res;
	}

	
}
