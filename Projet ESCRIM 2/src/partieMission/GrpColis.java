package partieMission;

import java.util.ArrayList;
import java.util.Iterator;

import metier.Colis;

public class GrpColis implements Iterable<Colis>{
	ArrayList<Colis> ensColis;
	
	public GrpColis(){
		ensColis = new ArrayList<Colis>();
	}
	
	

	
	
	
	//Fonction sur ArrayList
	
	public void add(Colis colis) {
		ensColis.add(colis);
	}

	public void remove(Colis Colis){
		ensColis.remove(Colis);
	}
	
	public Colis get(int index) {
		return ensColis.get(index);
	}

	public Iterator<Colis> iterator() {
		return ensColis.iterator();
	}
}
