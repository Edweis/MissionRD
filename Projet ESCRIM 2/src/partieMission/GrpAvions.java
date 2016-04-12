package partieMission;

import java.util.ArrayList;

import java.util.Iterator;

public class GrpAvions implements Iterable<Avion> {
	private ArrayList<Avion> ensAvion;
	private Chargement chargement;

	public GrpAvions() {
		ensAvion = new ArrayList<Avion>();
	}

	/**
	 * Ajoute le chargement au groupe d'avion. Cette méthode est appellée par
	 * Chargement dans son constructeur
	 * 
	 * @param le
	 *            chargement
	 */
	public void addChargement(Chargement chargement) {
		this.chargement = chargement;
	}

	
	
	
	
	
	//Fonction sur ArrayList
	public void add(Avion avion) {
		ensAvion.add(avion);
	}

	public void remove(Avion avion){
		ensAvion.remove(avion);
	}
	public Avion get(int index) {
		return ensAvion.get(index);
	}

	public Iterator<Avion> iterator() {
		return ensAvion.iterator();
	}

}
