package application;

import java.util.ArrayList;

import excel.ObjetExcel;
import excel.ReadExcel;
import javafx.stage.Stage;
import model.Item;
import model.SetBoxes;
import model.Factories.BoxesFactory;
import vue.Displayer;

public class Main {

	public static void main(String[] args) {
System.out.print("ee");
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
	
}

}
