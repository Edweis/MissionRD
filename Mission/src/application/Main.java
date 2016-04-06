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
		
		//Cr�ation des bo�tes
		ReadExcel re= new ReadExcel();
		ArrayList<ObjetExcel> liste=re.read();
		BoxesFactory bf=new BoxesFactory();
		SetBoxes sb=bf.createBoxes(liste);
		
		//Cr�ation des avions
		
		
		
	}

	private static void CharlieTests(){
	Item container=new Item(2000,1800,3000);
	
	SetBoxes boites = BoxesFactory.generateBoxes(container, 20, 0.9, 580, 650);
	
	BoxesFactory.exportBoxes(boites, "fichier.txt");
	Controller fdp = new Controller();
	SetBoxes boitesfin=fdp.fill_single_strip(2000, 650, 650, boites, "height");
	BoxesFactory.exportBoxes(boitesfin, "fin.txt");
}

}
