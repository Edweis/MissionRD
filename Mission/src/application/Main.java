package application;

import java.util.ArrayList;

import controller.Controller;
import excel.ObjetExcel;
import excel.ReadExcel;
import javafx.stage.Stage;
import model.Item;
import model.SetBoxes;
import model.Factories.BoxesFactory;
import vue.Displayer;

public class Main {

	public static void main(String[] args) {
		FrancoisTests();
		//CharlieTests(); 
		//PierreTests();
	}
	
	private static void FrancoisTests(){
		Item i = new Item(100, 70, 30);
		Item j = new Item(150, 880, 900);
		
		SetBoxes s = new SetBoxes();
		s.add(i);
		s.add(j);
		
		s.rotateBoxesMaxDepth(200);
		System.out.println(s);
		
		//Displayer d = new Displayer();
		//d.start(new Stage());
	}

	private static void PierreTests(){
		
		//Cr�ation des bo�tes
		ReadExcel re= new ReadExcel();
		ArrayList<ObjetExcel> liste=re.read();
		BoxesFactory bf=new BoxesFactory();
		SetBoxes sb=bf.createBoxes(liste);
		
		//Cr�ation des avions
		
		
		
	}

	private static void CharlieTests(){
	Item container=new Item(2000,1800,3000);
	
	SetBoxes boites = BoxesFactory.generateBoxes(container, 20, 0.9, 120, 250);
	
	BoxesFactory.exportBoxes(boites, "fichier.txt");
	//Controller fdp = new Controller();
	//fdp.fill_single_strip(, stripW, stripD, boites, "height");
	
}

}
