package model;

import java.util.ArrayList;
import java.util.Iterator;

public class SetBoxes implements Iterable<Item> {

	int Vinit = 0;

	/**
	 * set = set of Boxes
	 */
	protected ArrayList<Item> set;

	/**
	 * sizesOfItems = array with two values in each case : a size and his
	 * occurrence. For instance [20][5] means that there is 5 boxes which have
	 * an edge which size 20.
	 */
	protected ArrayList<int[]> sizesOfItems;

	protected long volume;

	/**
	 * Constructor that just initiates
	 */
	public SetBoxes() {
		set = new ArrayList<Item>();
		sizesOfItems = new ArrayList<int[]>();
		volume = 0;
	}

	/**
	 * Rotate boxes of N in order to maximize their width.
	 */
	public void rotateBoxesMaxWidth() {
		int max;
		for (Item i : set) {
			max = i.maxEdge();
			if (i.getHeight() == max) {
				i.switchDimension("wh");
			} else if (i.getDepth() == max) {
				i.switchDimension("dw");
			}
		}
	}

	/**
	 * Rotate boxes of N in order to maximize their height.
	 */
	public void rotateBoxesMaxHeight() {
		int max;
		for (Item i : set) {
			max = i.maxEdge();
			if (i.getWidth() == max) {
				i.switchDimension("wh");
			} else if (i.getDepth() == max) {
				i.switchDimension("dh");
			}
		}
	}

	/**
	 * Group two Boxes
	 */
	public void pairBoxes(int index, int depth) {
		CalculPairBoxes cpb;
		set.get(index).rotateBoxMaxDepth(depth, set.get(index));
		cpb = rotatePairBoxes(depth, index);

		if (cpb.getPos() == "up") {
			set.get(index).switchDimension("hd");
			if (cpb.getRot() == "hd") {
				set.get(cpb.getIndexitem()).switchDimension("hd");
			} else if (cpb.getRot() == "wd") {
				set.get(cpb.getIndexitem()).switchDimension("wd");
			}
		} else if (cpb.getPos() == "next") {
			set.get(index).switchDimension("wd");
			if (cpb.getRot() == "hd") {
				set.get(cpb.getIndexitem()).switchDimension("hd");
			} else if (cpb.getRot() == "wd") {
				set.get(cpb.getIndexitem()).switchDimension("wd");
			}
		} else if (cpb.getPos() == "behind") {
			if (cpb.getRot() == "hd") {
				set.get(cpb.getIndexitem()).switchDimension("hd");
			} else if (cpb.getRot() == "wd") {
				set.get(cpb.getIndexitem()).switchDimension("wd");
			}
		}
		Item it = new Item(Math.max(set.get(index).getHeight(), set.get(cpb.getIndexitem()).getHeight()),
				Math.max(set.get(index).getWidth(), set.get(cpb.getIndexitem()).getWidth()), depth);
		set.remove(index);
		set.remove(cpb.getIndexitem());
		set.add(it);
		set.trimToSize();
	}

	/**
	 * 
	 * @param depth
	 * @param Volume
	 */
	public void chooseDepth(int depth, int Volume) {
		if (Volume > Vinit) {

			Vinit = Volume;
		}
		for (int i = 0; i < set.size(); i++) {
			if (depth < set.get(0).minEdge()) {

			}
		}

	}

	/**
	 * Turn all boxes except one
	 * 
	 * @param i
	 *            = Index of the box which is not rotated
	 * @param rot
	 *            = Direction of rotation
	 */
	public void switchAllDimensions(int i, String rot) {
		for (int k = 0; k < set.size(); k++) {
			if (k != i) {
				set.get(k).switchDimension(rot);
			}
		}
	}

	/**
	 * Through all boxes except the one studied(index box) and tries to couple
	 * the 2 in every possible way. Then selects the best combination as the sum
	 * of boxes depths less than the tested depth and that beta is maximum.
	 * 
	 * @param depth
	 *            = depth not to exceed
	 * @param index
	 *            = index of the tested box
	 */
	public CalculPairBoxes rotatePairBoxes(int depth, int index) {

		CalculPairBoxes[] calcul = new CalculPairBoxes[(set.size() * 9) - 9];
		String rot = null, pos = "behind";
		double vi = set.get(index).getVolume();
		double vj = 0;
		int n = 0;
		double max = 0;
		double b, c;

		for (int k = 0; k < calcul.length; k++) {
			if (n != index) {

				if (k == set.size() - 1) {
					n = 0;
					rot = "wd";
					switchAllDimensions(index, rot);
				} else if (k == set.size() * 2 - 2) {
					rot = "dw";
					switchAllDimensions(index, rot);
					rot = "hd";
					switchAllDimensions(index, rot);
					n = 0;
				} else if (k == set.size() * 3 - 3) {
					n = 0;
					rot = "dh";
					pos = "up";
					switchAllDimensions(index, rot);
					rot = null;
					set.get(index).switchDimension("hd");
				} else if (k == set.size() * 4 - 4) {
					n = 0;
					rot = "wd";
					switchAllDimensions(index, rot);
				} else if (k == set.size() * 5 - 5) {
					n = 0;
					rot = "dw";
					switchAllDimensions(index, rot);
					rot = "hd";
					switchAllDimensions(index, rot);
				} else if (k == set.size() * 6 - 6) {
					n = 0;
					rot = "dh";
					pos = "next";
					switchAllDimensions(index, rot);
					rot = null;
					set.get(index).switchDimension("dh");
					set.get(index).switchDimension("wd");
				} else if (k == set.size() * 7 - 7) {
					n = 0;
					rot = "wd";
					switchAllDimensions(index, rot);
				} else if (k == set.size() * 8 - 8) {
					n = 0;
					n = 0;
					rot = "dw";
					switchAllDimensions(index, rot);
					rot = "hd";
					switchAllDimensions(index, rot);
				}
				vj = set.get(n).getVolume();
				b = (vi + vj) / (depth * Math.max(set.get(index).getWidth(), set.get(n).getWidth())
						* Math.max(set.get(index).getHeight(), set.get(n).getHeight()));
				c = set.get(index).getDepth() + set.get(n).getDepth();
				CalculPairBoxes cpb = new CalculPairBoxes(n, b, c, rot, pos);
				calcul[k] = cpb;
			} else {
				n++;
				k--;
			}
		}
		switchAllDimensions(index, "dh");
		set.get(index).switchDimension("dw");

		for (int k = 0; k < calcul.length; k++) {
			if (calcul[k].getSumdepth() <= depth) {
				if (max < calcul[k].getBeta()) {
					max = calcul[k].getBeta();
					index = k;
				}
			}
		}

		return calcul[index];
	}

	/**
	 * return the size of the set
	 * 
	 * @return the size of the set
	 */
	public int size() {
		return set.size();
	}

	/**
	 * Return the largest length of each box in a set of boxes
	 *
	 * @param N
	 *            = set of boxes
	 * @return = shortest length
	 */
	public int largestEdge() {
		int res;
		if (set.isEmpty()) {
			res = -1;
		} else {
			res = set.get(1).getDepth();
			for (Item i : set) {
				if (i.getHeight() < res) {
					res = i.getHeight();
				} else if (i.getWidth() < res) {
					res = i.getWidth();
				} else if (i.getDepth() < res) {
					res = i.getDepth();
				}
			}
		}

		return res;
	}

	/**
	 * Return the shortest length of each box in a set of boxes
	 *
	 * @param set
	 *            = set of boxes
	 * @return = shortest length
	 */
	public int shortestEdge() {
		int res = -1;
		if (set.isEmpty()) {
			for (Item i : set) {
				if (i.getHeight() > res) {
					res = i.getHeight();
				} else if (i.getWidth() > res) {
					res = i.getWidth();
				} else if (i.getDepth() > res) {
					res = i.getDepth();
				}
			}
		}
		return res;
	}

	public Iterator<Item> iterator() {
		return set.iterator();
	}

	/**
	 * add an Item to the array
	 * 
	 * @param element
	 */
	public void add(Item element) {

		set.add(element);
		volume += element.getVolume();
		updateAdding(element);

	}

	/**
	 * exclude a set of boxes from this set
	 * 
	 * @param set
	 *            of boxes
	 */
	public void exclude(SetBoxes b) {
		for (Item i : b) {
			if (!set.remove(i)) {
				System.out.println("###############");
				System.out.println("ERROR Cant not operate 'a.exclude(b)' , b is not include in a");
				return;
			} else {
				updateRemoving(i);
				volume -= i.getVolume();
			}
		}
	}

	/**
	 * Update the ArrayList sizeOfBoxes when we add a box
	 * 
	 * @param box
	 *            added
	 */
	private void updateAdding(Item element) {

		boolean isDepthAttributed = false;
		boolean isWidthAttributed = false;
		boolean isHeightAttributed = false;

		for (int i = 0; i < sizesOfItems.size(); i++) {

			int[] sizes = sizesOfItems.get(i);

			/**
			 * If we have not attributed the depth, either it already exists in
			 * the array and we add 1 at the number of occurrence, either it
			 * does not exist and we create it
			 * 
			 * The same thing for height and width
			 */
			if (!isDepthAttributed) {
				if (sizes[0] == element.getDepth()) {// exists
					sizes[1]++;
					isDepthAttributed = true;
				} else if (element.getDepth() < sizes[0]) {// does not exist
					int[] k = { element.getDepth(), 1 };
					sizesOfItems.add(i, k);
					isDepthAttributed = true;
				}
			}

			if (!isWidthAttributed) {
				if (sizes[0] == element.getWidth()) {// exists
					sizes[1]++;
					isWidthAttributed = true;
				} else if (element.getWidth() < sizes[0]) {// does not exist
					int[] k = { element.getWidth(), 1 };
					sizesOfItems.add(i, k);
					isWidthAttributed = true;
				}
			}

			if (!isHeightAttributed) {
				if (sizes[0] == element.getHeight()) {// exists
					sizes[1]++;
					isHeightAttributed = true;
				} else if (element.getHeight() < sizes[0]) {// does not exist
					int[] k = { element.getHeight(), 1 };
					sizesOfItems.add(i, k);
					isHeightAttributed = true;
				}
			}
		}

		/**
		 * It is possible that one of the dimension has not been added to the
		 * array because it is the bigger size of the array. Thus we add it.
		 * However it is also possible that to of them have not been added, thus
		 * we have to sort them so the array will ended sorted. Same case if all
		 * of them were not added to the array.
		 * 
		 */

		int nbOfSizes = sizesOfItems.size();

		if (!isDepthAttributed) {
			int[] k = { element.getDepth(), 1 };
			sizesOfItems.add(k);
			nbOfSizes++;
		}

		if (!isHeightAttributed) {

			int[] k = { element.getHeight(), 1 };

			if (element.getHeight() < sizesOfItems.get(nbOfSizes - 1)[0]) {
				sizesOfItems.add(nbOfSizes - 1, k);
			} else {
				sizesOfItems.add(k);
			}
			nbOfSizes++;
		}

		if (!isWidthAttributed) {

			int[] k = { element.getWidth(), 1 };

			if (element.getWidth() < sizesOfItems.get(nbOfSizes - 1)[0]) {
				if (element.getWidth() < sizesOfItems.get(nbOfSizes - 2)[0]) {
					sizesOfItems.add(nbOfSizes - 2, k);
				} else {
					sizesOfItems.add(nbOfSizes - 1, k);
				}
			} else {
				sizesOfItems.add(k);
			}
		}
	}

	/**
	 * Update the ArrayList sizeofBoxes when we remove a box from the set
	 * 
	 * @param the
	 *            Item
	 */
	private void updateRemoving(Item element) {
		boolean isDepthAttributed = false;
		boolean isWidthAttributed = false;
		boolean isHeightAttributed = false;

		int[] s;

		for (int i = 0; i < sizesOfItems.size(); i++) {
			s = sizesOfItems.get(i);

			if (!isDepthAttributed) {
				if (s[0] == element.getDepth()) {
					if (s[1] == 1) {
						sizesOfItems.remove(i);
					} else {
						s[1]--;
						sizesOfItems.set(i, s);
					}
				}
			}

			if (!isHeightAttributed) {
				if (s[0] == element.getHeight()) {
					if (s[1] == 1) {
						sizesOfItems.remove(i);
					} else {
						s[1]--;
						sizesOfItems.set(i, s);
					}
				}
			}

			if (!isWidthAttributed) {
				if (s[0] == element.getWidth()) {
					if (s[1] == 1) {
						sizesOfItems.remove(i);
					} else {
						s[1]--;
						sizesOfItems.set(i, s);
					}
				}
			}
		}
	}

	/**
	 * return the Item of index i
	 * 
	 * @param index
	 * @return the Item
	 */
	public Item get(int index) {
		return set.get(index);
	}

	/**
	 * set an item at a specific place
	 * 
	 * @param index
	 * @param element
	 */
	public void set(int index, Item element) {
		set.set(index, element);
	}

	/**
	 * Return the volume of the set of boxes
	 * 
	 * @return the volume
	 */
	public long getVolume() {
		return volume;
	}

	/**
	 * Return the sizes of Items in this set sorted
	 * 
	 * @return Sizes of items
	 */
	public ArrayList<Integer> getArrayOfPos() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int[] i : sizesOfItems) {
			res.add(i[0]);
		}
		return res;
	}

	public String toString() {
		String s = "This set of Boxes contains : ";
		for (Item p : set) {
			s = s + "\n" + p.toString();
		}
		return s;
	}
}
