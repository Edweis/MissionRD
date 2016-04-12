package metier;

import java.util.Stack;

public class Article {
	private String nom;
	private Stack<Caracteristique> stocks;
	private String type;

	public Article(String nom, String type) {
		this.nom = nom;
		this.type = type;
	}
	public int getStockTotal() {
		int total =0;
		for(Caracteristique stock : stocks){
			total += stock.getQuantite();
		}
		return total;
	}
	public void addStock(int quantite,String date) {
		this.stocks.add(new Caracteristique(quantite, date));
	}
	public Caracteristique getStock(int index) {
		return stocks.get(index);
	}
	public Stack<Caracteristique> getStock(){
		return stocks;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
