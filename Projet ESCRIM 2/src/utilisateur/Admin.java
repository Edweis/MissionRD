package utilisateur;

import java.sql.*;

public class Admin {

	public void creer_utilisateur(int utilisateur_id,String nom,String prenom, String mdp,boolean droit_modifier,boolean droit_lecture,String grade,Bdd_utilisateur bdd) throws SQLException{
		String requete= "insert into utilisateur values (null,'"+nom+"','"+prenom+"','"+mdp+"',"+droit_modifier+","+droit_lecture+",'"+grade+"')";
		bdd.ecriture(requete);
	}
	public void mise_a_jour(String nom,String prenom,String mdp,boolean droit_modifier ,boolean droit_lecture,String grade,Admin admin,Bdd_utilisateur bdd) throws SQLException{
		String requete= "update utilisateur set mdp='"+mdp+"', droit_modifier="+droit_modifier+", droit_lecture="+droit_lecture+",grade='"+grade+"' where nom='"+nom+"' AND "+"prenom='"+prenom+"'";
		bdd.ecriture(requete);
	}
}