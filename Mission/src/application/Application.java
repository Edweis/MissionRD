package application;

import model.Item;
import model.PlanesFactory;
import model.SetBoxes;
import model.SetPlanes;
import usefullFunctions.BoxesFactory;

public class Application {

	public static void main(String[] args) {

		// Test BoxesFactory

		Item container = new Item(2000, 1200, 1600);

		SetBoxes myBoxes = BoxesFactory.generateBoxes(container, 6, 0.90, 300, 600);
			System.out.println(myBoxes);
		BoxesFactory.exportBoxes(myBoxes, "Test boxes 1.csv");
		SetBoxes myBoxes2 = BoxesFactory.importBoxes("Test boxes 1.csv");
			System.out.println(myBoxes2);
			
		//Test PlanesFactory
			
		SetPlanes myPlanes = PlanesFactory.generatePlanes(3, 2, 2500, 1200);
			System.out.println(myPlanes);
		PlanesFactory.exportPlanes(myPlanes, "Test planes 1.csv");
		SetPlanes myPlanes2 = PlanesFactory.importPlanes("Test planes 1.csv");
			System.out.println(myPlanes2);

	}

}
