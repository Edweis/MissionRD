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
		CharlieTests(); 
		//PierreTests();
	}

	private static void FrancoisTests(){
		Displayer d = new Displayer();
		d.start(new Stage());
	}

	private static void PierreTests(){
		
		//Création des boîtes
		ReadExcel re= new ReadExcel();
		ArrayList<ObjetExcel> liste=re.read();
		BoxesFactory bf=new BoxesFactory();
		SetBoxes sb=bf.createBoxes(liste);
		
		//Création des avions
		
		
		
	}

	private static void CharlieTests(){
	Item container=new Item(2000,1800,3000);
	
	SetBoxes boites = BoxesFactory.generateBoxes(container, 20, 0.9, 120, 250);
	
	BoxesFactory.exportBoxes(boites, "fichier.txt");
	//Controller fdp = new Controller();
	//fdp.fill_single_strip(, stripW, stripD, boites, "height");
	
}

}
