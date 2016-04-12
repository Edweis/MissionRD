package metier;

import java.util.Stack;

import view.Groupement;

public class Colis implements Groupement {
	private Dimension dim;
	private Stack<Article> articles;
	private int numeroColis;
	public float poids;
	public String observations;
	public String secteur; // Creer une classe ?

	/**
	 * Ajoutés par François
	 */
	public String Affectataire; // Creer une classe ?
	public String Secteur;
	public String Designation; // Creer une classe ?
	public String precision;
	public String volume;

	public Colis(int numerosColis, Dimension dim) {
		this.numeroColis = numerosColis;
		this.dim = dim;
		this.articles = new Stack<Article>();
	}

	public Colis(int numerosColis, Dimension dim, Stack<Article> articles) {
		this.numeroColis = numerosColis;
		this.dim = dim;
		this.articles = articles;
	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}

	public Stack<Article> getArticles() {
		return articles;
	}

	public void setArticles(Stack<Article> articles) {
		this.articles = articles;
	}

	public int getNumerosColis() {
		return numeroColis;
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	
	
	
	
	//A SUPPRIMER DANS LES FUTUR ! c'est juste pour jouer
	
	public void setInfos(String Affectaire, String Designation, String precision){
		this.Affectataire = Affectaire;
		this.Designation = Designation;
		this.precision = precision;
	}

	
	
	
	
	//Implemented methods
	
	public String details() {
		String res = "<html>";
		
		res = res + 
				"blabalbalablalbal <br>" + 
				"blabalbalbal <br>" + 
				"blabalbalablapùmlbal <br>";
		
		res = res + "</html>";
		
		return res;
	}

	public String[] getInfos() {
		String[] res = { Affectataire, Integer.toString(numeroColis), Designation, precision, dim.toString() };
		return res;
	}
	
}
