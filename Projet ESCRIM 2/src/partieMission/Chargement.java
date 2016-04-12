package partieMission;

public class Chargement {
	private GrpAvions ensAvions;
	private GrpColis ensColis;
	//variable associant les boites aux avions à  une position précise
	
	public Chargement(GrpAvions avions, GrpColis colis) {
		ensAvions = avions;
		ensColis = colis;
		avions.addChargement(this);
	}
	
}
