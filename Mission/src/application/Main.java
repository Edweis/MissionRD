package application;

import excel.ReadExcel;
import javafx.stage.Stage;
import model.Item;
import model.SetBoxes;
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
		re.read();
		
	}

	private static void CharlieTests(){
	
}

}
