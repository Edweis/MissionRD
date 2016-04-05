package excel;

public class ObjetExcel {

	private String affectataire=null;
	private String module=null;
	private String nom_opt=null;
	private String secteur=null;
	private String nature=null;
	private double numero=0;
	private String designation =null;
	private String article =null;
	private String dimension=null;
	private long volume=0;
	private double poids =0;
	
	public ObjetExcel(String a, String m, String no,String sec,String nat, double num,String des,String art,String dim,long vol,double p){
		affectataire=a;
		module=m;
		nom_opt=no;
		secteur=sec;
		nature=nat;
		numero=num;
		designation=des;
		article=art;
		dimension=dim;
		volume=vol;
		poids=p;
	}
	
	public void affichage(){
		System.out.println(affectataire+" "+module+" "+nom_opt+" "+secteur+" "+nature+" "+numero+" "+designation+" "+article+" "+dimension+" "+volume+" "+poids);
	}

	/**
	 * @return the affectataire
	 */
	public String getAffectataire() {
		return affectataire;
	}

	/**
	 * @param affectataire the affectataire to set
	 */
	public void setAffectataire(String affectataire) {
		this.affectataire = affectataire;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the nom_opt
	 */
	public String getNom_opt() {
		return nom_opt;
	}

	/**
	 * @param nom_opt the nom_opt to set
	 */
	public void setNom_opt(String nom_opt) {
		this.nom_opt = nom_opt;
	}

	/**
	 * @return the secteur
	 */
	public String getSecteur() {
		return secteur;
	}

	/**
	 * @param secteur the secteur to set
	 */
	public void setSecteur(String secteur) {
		this.secteur = secteur;
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
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the article
	 */
	public String getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(String article) {
		this.article = article;
	}

	/**
	 * @return the dimension
	 */
	public String getDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the volume
	 */
	public long getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(long volume) {
		this.volume = volume;
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
}
