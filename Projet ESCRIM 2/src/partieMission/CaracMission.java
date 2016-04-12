package partieMission;

import java.util.Date;

public class CaracMission {
	Date dateDebut;
	Date dateFin;
	String lieu;
	String nomMission;
	String configClimat;
	String configMatos;
	String descritpion;

	public CaracMission(Date dateDebut, Date dateFin, String lieu, String nomMission, String configClimat,
			String configMatos, String descritpion) {
		
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.lieu = lieu;
		this.nomMission = nomMission;
		this.configClimat = configClimat;
		this.configMatos = configMatos;
		this.descritpion = descritpion;
	}
}
