package usefullFunctions;

import java.util.ArrayList;

import model.Item;
import model.SetBoxes;

public class FillLayer {
	/**
	 * Return the frequency function of type 1, 2 or 3
	 * 
	 * @param whichOne
	 *            = 1, 2 or 3, number of the frequency function, see comments
	 *            inside the method
	 * @return frequency function
	 */
	public static ArrayList<Integer> createFrequencyFunction(SetBoxes N, int whichOne) {

		// "Alpha" and "Beta" initialization
		int alpha = N.shortestEdge();
		int beta = N.largestEdge();

		// empty frequency function
		ArrayList<Integer> f = new ArrayList<Integer>();
		ArrayList<Integer> pos = N.getArrayOfPos();

		//initializing f
		for (int i = 0; i < pos.size(); i++) {
			f.add(0);			
		}
		
		
		//Creating the function
		switch (whichOne) {
		case 1:
			/**
			 * k = alpha..beta f = sum( g(i,k) , i=1..nb boxes) with g(i,k)= 1
			 * if (w(i) == k OR h(i) == k OR d(i) == k )
			 **/
			for (Item i : N) {
				
				f.set(pos.indexOf(i.getDepth()), 1 + f.get(pos.indexOf(i.getDepth())));
				f.set(pos.indexOf(i.getHeight()), 1 + f.get(pos.indexOf(i.getHeight())));
				f.set(pos.indexOf(i.getWidth()), 1 + f.get(pos.indexOf(i.getWidth())));
			}
			break;
		case 2:
			/**
			 * k = alpha..beta f = sum( g(i,k) , i=1..nb boxes) with g(i,k)= 1
			 * if (max(w(i), h(i), d(i) == k
			 **/
			int max;
			for (Item i : N) {
				max = pos.indexOf(i.maxEdge());
				f.set(max, f.get(max) + 1);
			}
			break;
		case 3:
			/**
			 * k = alpha..beta f = sum( g(i,k) , i=1..nb boxes) with g(i,k)= 1
			 * if (min(w(i), h(i), d(i) == k
			 **/
			int min;
			for (Item i : N) {
				min = pos.indexOf(i.minEdge());
				f.set(min, f.get(min) + 1);
			}
			break;
		}

		// ###print the frequency function for debug
				System.out.println("\n*Frequency function");
				for (int i=0; i<pos.size(); i++) {
					System.out.print("\t" + pos.get(i));
				}
				System.out.println();
				for (Integer i : f) {
					System.out.print("\t" + i);
				}
				System.out.println();

		return f;
	}

	public static ArrayList<Integer> generateIndexRankedList(ArrayList<Integer> freqFunct, String priorityRule,
			int sizeOfRankedReturn) {

		ArrayList<Integer> indexOfSize = new ArrayList<Integer>();

		switch (priorityRule) {
		case "a":
			break;

		/**
		 * Choose the largest dimension with increasing frequency
		 **/
		case "b":
			int max = -1;
			int pos = 0;
			// Until the function contains only zeros or we have picked enough
			// value
			while (max != 0 && indexOfSize.size() != sizeOfRankedReturn) {
				max = 0;
				pos = 0;
				
				// We pick the highest value of the frequency function
				for (int i = 0; i < freqFunct.size(); i++) {
					if (freqFunct.get(i) > max) {
						max = freqFunct.get(i);
						pos = i;
						freqFunct.set(i, 0);// And we set it at 0
					}
				}
				indexOfSize.add(pos);
			}

			break;
		case "c":
			break;
		case "d":
			break;
		case "e":
			break;
		case "f":
			break;
		case "g":
			break;
		case "h":
			break;
		case "i":
			break;
		}
		
				
		return indexOfSize;
	}

}
