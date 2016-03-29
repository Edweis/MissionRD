package application;

import controller.Controller;
import usefullFunctions.*;

public class Application {

	public static void main(String[] args) {
		ImportBoxes.setContainer(230, 230, 590);
		ImportBoxes.generateBoxes(5, 0.90, 80, 150);

		ImportBoxes.exportBoxes("sauv/sauvegarde.txt");
	
		
		ImportBoxes.importBoxes("sauv/sauvegarde.txt");
		
		Controller c = new Controller();
		
		c.fill_layer(230, 100, 80, 0, ImportBoxes.getSet());
		
	}

}
