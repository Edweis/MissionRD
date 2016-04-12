package utilisateur;

public class Utilisateur {

	String prenom;
	String nom;
	String mdp;
	Boolean droit_modifier;
	Boolean droit_lecture;
	String grade;
	
	public Utilisateur(String nom,String prenom, String mdp,Boolean droit_modifier,Boolean droit_lecture,String grade){
		this.nom=nom;
		this.prenom=prenom;
		this.mdp=mdp;
		this.droit_lecture=droit_lecture;
		this.droit_modifier=droit_modifier;
		this.grade=grade;
	
	}
	
	
}
