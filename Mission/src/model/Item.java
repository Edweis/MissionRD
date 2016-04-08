package model;

public class Item {

	private int height, width, depth, leftPos, frontPos, upPos;
	private double numero, poids;
	private long volume;
	private String nature;
	private boolean turn;


	public Item(int h, int w, int d) {
		height = h;
		width = w;
		depth = d;

		volume = (long)h * (long) w *(long) d;

		leftPos = frontPos = upPos = -1;
	}

	public Item(String nat, double num, int h, int w, int d, long v, double p) {
		nature = nat;
		numero = num;
		height = h;
		width = w;
		depth = d;
		volume = v;
		poids = p;

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
 * Writes the content of an Item separated by ";"
 */
	public String toCSV(Item i) {
		return i.getNature()+";"+i.getNumero()+";"+i.getHeight() + ";" + i.getWidth() + ";" + i.getDepth()+";"+i.getVolume()+";";
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
		//return "nature=" + nature +" * numero="+numero+ " * h=" + height + " * w=" + width + " * d=" + depth+" * vol="+volume+" * poids="+poids																																																																																																																																																																																																																																																																																														;
		return "h=" + height + " * w=" + width + " * d="+depth;
	}


	
	
	
	
	
	// Getters and setters

	public double getVolume() {
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

	public void setPosition(int frontPosition, int leftPosition, int upPosition) {
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

	/**
	 * @return the numero
	 */
	public double getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(double numero) {
		this.numero = numero;
	}

	/**
	 * @return the poids
	 */
	public double getPoids() {
		return poids;
	}

	/**
	 * @param poids the poids to set
	 */
	public void setPoids(double poids) {
		this.poids = poids;
	}

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * @param leftPos the leftPos to set
	 */
	public void setLeftPos(int leftPos) {
		this.leftPos = leftPos;
	}

	/**
	 * @param frontPos the frontPos to set
	 */
	public void setFrontPos(int frontPos) {
		this.frontPos = frontPos;
	}

	/**
	 * @param upPos the upPos to set
	 */
	public void setUpPos(int upPos) {
		this.upPos = upPos;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(long volume) {
		this.volume = volume;
	}
}