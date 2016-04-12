package partieMission;

public class Mission {
	CaracMission caracteristiques;
	GrpAvions ensAvions;
	GrpColis ensColis;
	
	public Mission(CaracMission carac, GrpAvions avions, GrpColis colis){
		caracteristiques = carac;
		ensAvions = avions;
		ensColis = colis;
	}
}
