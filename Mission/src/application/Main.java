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
		PierreTests();
	}

	private static void FrancoisTests(){
		Displayer d = new Displayer();
		d.start(new Stage());
	}

	private static void PierreTests(){
		ReadExcel re= new ReadExcel();
		ArrayList<ObjetExcel> liste=re.read();
		BoxesFactory bf=new BoxesFactory();
		bf.createBoxes(liste);
		
	}

	private static void CharlieTests(){
	
}

}
