package utilisateur;

import java.sql.*;

public class Bdd_utilisateur {

	String pilote = "com.mysql.jdbc.Driver";
	Connection connexion;
	
	public void connecter (String login,String motdepasse)throws SQLException{
		
		try {
			Class.forName(pilote);
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/escrim","root","");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
		
	}
	  public void deconnecter() throws SQLException{
	        connexion.close();
	        
	    }
	  public ResultSet lecture(String requete) throws SQLException{
	        Statement stmt=connexion.createStatement();
	       ResultSet resultat=stmt.executeQuery(requete);
	        return resultat;
	    }
	    public void ecriture(String requete) throws SQLException{
	        Statement stmt= connexion.createStatement();
	        int resultat = stmt.executeUpdate(requete);
	    }
    }
	

