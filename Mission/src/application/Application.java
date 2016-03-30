package application;

import model.Plane;
import model.PlanesFactory;
import model.SetPlanes;

public class Application {

	public static void main(String[] args) {
		
//		BoxesFactory.setContainer(230, 230, 590);
//		BoxesFactory.generateBoxes(5, 0.90, 80, 150);
//
//		BoxesFactory.exportBoxes("sauv/sauvegarde.txt");
//	
//		
//		BoxesFactory.importBoxes("sauv/sauvegarde.txt");
//		
//		Controller c = new Controller();
//		
//		c.fill_layer(230, 100, 80, 0, BoxesFactory.getSet());
//		
		
		PlanesFactory.generatePlanes(3, 2, 3000, 2000);
		
		SetPlanes set = PlanesFactory.getSet();
		
		
			System.out.println(set);
			
			PlanesFactory.exportPlanes("mesavions.csv");
		
	}

}
