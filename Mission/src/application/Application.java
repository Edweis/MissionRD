package application;

import controller.Controller;
import usefullFunctions.*;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImportBoxes.setContainer(230, 230, 590);
		ImportBoxes.generateBoxes(5, 0.90, 80, 150);

		ImportBoxes.exportBoxes("/media/piou/Data/Mes Documents/Developpement/Workspace/Mission/sauv/salut.txt");
	
		Controller c = new Controller();
		
		c.fill_layer(230, 100, 80, 0, ImportBoxes.getSet());
		
		
		for (int j = 0; j< args.length; j++) {
			int p= j;
		}
	}

}
