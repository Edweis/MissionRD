package model;

public class CalculPairBoxes {

	private int indexitem;
	private double beta;
	private double sumdepth;
	private String rot;
	private String pos;

	public CalculPairBoxes(){
		
	}
	
	public CalculPairBoxes(int a, double b, double c, String d, String e) {
		indexitem = a;
		beta = b;
		sumdepth = c;
		rot = d;
		pos = e;
	}

	/**
	 * Display method
	 */
	public void affichage() {
		System.out.println(getIndexitem()+" "+getBeta()+" "+getSumdepth()+" "+getRot()+ " "+getPos());
	}

	/**
	 * @return the indexitem
	 */
	public int getIndexitem() {
		return indexitem;
	}

	/**
	 * @param indexitem
	 *            the indexitem to set
	 */
	public void setIndexitem(int indexitem) {
		this.indexitem = indexitem;
	}

	/**
	 * @return the beta
	 */
	public double getBeta() {
		return beta;
	}

	/**
	 * @param beta
	 *            the beta to set
	 */
	public void setBeta(int beta) {
		this.beta = beta;
	}

	/**
	 * @return the sumdepth
	 */
	public double getSumdepth() {
		return sumdepth;
	}

	/**
	 * @param sumdepth
	 *            the sumdepth to set
	 */
	public void setSumdepth(int sumdepth) {
		this.sumdepth = sumdepth;
	}

	/**
	 * @return the rot
	 */
	public String getRot() {
		return rot;
	}

	/**
	 * @param rot
	 *            the rot to set
	 */
	public void setRot(String rot) {
		this.rot = rot;
	}

	/**
	 * @return the pos
	 */
	public String getPos() {
		return pos;
	}

	/**
	 * @param pos
	 *            the pos to set
	 */
	public void setPos(String pos) {
		this.pos = pos;
	}
}
