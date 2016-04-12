package metier;

public class Caracteristique {
	
	private String date = new String();
	private int quantite;
	
	public Caracteristique(int quantite, String date) {
		this.date = date;
		this.quantite = quantite;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
}
