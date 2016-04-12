package metier;

public class Dimension {
	private float hauteur;
	private float largeur;
	private float profondeur;
	
	public Dimension(float hauteur,float largeur,float profondeur){
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.profondeur = profondeur;
	}

	public float getHauteur() {
		return hauteur;
	}

	public void setHauteur(float hauteur) {
		this.hauteur = hauteur;
	}

	public float getLargeur() {
		return largeur;
	}

	public void setLargeur(float largeur) {
		this.largeur = largeur;
	}

	public float getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(float profondeur) {
		this.profondeur = profondeur;
	}
	public float getVolume(){
		return hauteur * profondeur * largeur;
	}
	public void setDimension(float hauteur,float largeur,float profondeur){
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.profondeur = profondeur;
	}

	public String toString(){
		return hauteur +"x" + largeur + "x" + profondeur;
	}
}
