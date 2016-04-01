package application;

import model.Item;
import model.PlanesFactory;
import model.SetBoxes;
import model.SetPlanes;
import usefullFunctions.BoxesFactory;

public class Application {

	public static void main(String[] args) {

		// Test BoxesFactory

		Item i1 = new Item(10, 20, 30);
		Item i2 = new Item(1,2,3);
		SetBoxes s = new SetBoxes();
		s.add(i1);
		s.add(i2);
		s.rotatePairBoxes(20, 1);
	}

}
