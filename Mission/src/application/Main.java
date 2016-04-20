package application;

import java.util.ArrayList;

import controller.Controller;
import excel.ObjetExcel;
import excel.ReadExcel;
import model.Item;
import model.Plane;
import model.SetBoxes;
import model.SetPlanes;
import model.Factories.BoxesFactory;
import model.Factories.PlanesFactory;

public class Main {

	public static int niveau = 0;

	public static void main(String[] args) throws Throwable {
		//FrancoisTests();
		// CharlieTests();
		 PierreTests();
	}

	private static void FrancoisTests() throws Throwable {
		Item container = new Item(600, 300, 1000);
//		SetBoxes myBoxes = BoxesFactory.generateBoxes(container, 3, 0.9, 200, 300);
//		BoxesFactory.exportBoxes(myBoxes, "mesboites.txt");
		SetBoxes myBoxes = BoxesFactory.importBoxes("mesboites.txt");

		Controller c = new Controller();

		// System.out.println(myBoxes);
		// System.out.println(myBoxes.largestEdge());

		c.fill_layer(600, 300, 280, 0, myBoxes);

		// Displayer d = new Displayer();
		// d.start(new Stage());
	}

	private static void PierreTests() throws Throwable {

		// Creation des boites
		/*ReadExcel re = new ReadExcel();
		ArrayList<ObjetExcel> liste = re.read();*/
		
		SetBoxes sb = BoxesFactory.importBoxes("mesboites.txt");
		System.out.println("Nb boîte en entrée :"+sb.size());
		System.out.println("Volume des boîtes en entrée :"+sb.getVolume());

		// Creation des avions
		SetPlanes sp=PlanesFactory.importPlanes("Planes Data Storage Of The Sky.csv");
		Controller c = new Controller();
		System.out.println("Volume du Container :"+sp.get(0).getSpaces().get(0).getVolume());
		
		int d=sp.get(0).getSpaces().get(0).getDepth();
		long v =(long) sp.get(0).getSpaces().get(0).getVolume();
		SetBoxes B=c.chooseDepth(d,v , sb, sp);
		System.out.println("Nb boîte en sortie :"+B.size());
		System.out.println("Volume des boites :"+B.getVolume());

	}

	private static void CharlieTests() {
		Item container = new Item(2000, 1800, 3000);

		SetBoxes boites = BoxesFactory.generateBoxes(container, 20, 0.9, 580, 650);

		BoxesFactory.exportBoxes(boites, "fichier.txt");
		Controller fdp = new Controller();
		SetBoxes boitesfin = fdp.fill_single_strip(2000, 650, 650, boites, "height");
		BoxesFactory.exportBoxes(boitesfin, "fin.txt");
	}
}
