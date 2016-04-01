package model;

public class Item {

	private int height, width, depth, leftPos, frontPos, upPos;

	

	private boolean turn;

	private long volume;

	public Item(int h, int w, int d) {
		height = h;
		width = w;
		depth = d;

		volume = h * w * d;

		leftPos = frontPos = upPos = -1;
	}

	public void switchDimension(String s) {
		int p;
		if (s != null) {
			switch (s) {
			case "wh":
				p = height;
				height = width;
				width = p;
				break;
			case "hd":
				p = height;
				height = depth;
				depth = p;
				break;
			case "dw":
				p = depth;
				depth = width;
				width = p;
				break;

			case "hw":
				p = height;
				height = width;
				width = p;
				break;
			case "dh":
				p = height;
				height = depth;
				depth = p;
				break;
			case "wd":
				p = depth;
				depth = width;
				width = p;
				break;

			}
		}
	}

	/**
	 * return the shortest edge of the item
	 *
	 * @return the int
	 */
	public int minEdge() {
		int res = height;
		if (width < res) {
			res = width;
		}
		if (depth < res) {
			res = depth;
		}
		return res;
	}

	/**
	 * Return the longest edge of the item
	 *
	 * @return the int
	 */
	public int maxEdge() {
		int res = height;
		if (width > res) {
			res = width;
		}
		if (depth > res) {
			res = depth;
		}
		return res;
	}

	public String toString() {
		return "h=" + height + " * w=" + width + " * d=" + depth;
	}

	/**
	 * Rotate a box such that its depth dj is largest possible satisfying dj<=d.
	 * Calculate the ratio a=dj/d.
	 * 
	 * @param d
	 *            = given depth
	 * @param i
	 *            = given Item
	 * @return a = dj/d
	 */
	public double rotateBoxMaxDepth(int d, Item i) {

		int max = i.maxEdge();
		int dj = 0;
		String rot = null;
		double a = 0;

		// Search the largest dimension of a box less or equal than d and rotate
		// the box
		if (i.depth <= d) {
			dj = i.depth;
		}
		if (i.height <= d && dj < i.height) {
			dj = i.height;
			rot = "hd";
		}
		if (i.width <= d && dj < i.width) {
			dj = i.width;
			rot = "wd";
		}

		System.out.println(rot);
		i.switchDimension(rot);

		// Calculates the ratio dj/d
		a = (double) (i.depth / d);
		return a;

	}

	
	
	
	
	
	
	// Getters and setters
	
	public long getVolume() {
		return volume;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setPosition(int frontPosition, int leftPosition, int upPosition){
		frontPos = frontPosition;
		leftPos = leftPosition;
		upPos = upPosition;
	}
	
	public int getLeftPos() {
		return leftPos;
	}

	public int getFrontPos() {
		return frontPos;
	}

	public int getUpPos() {
		return upPos;
	}
}