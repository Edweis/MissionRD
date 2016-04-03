package application;

import javafx.stage.Stage;
import model.Item;
import model.SetBoxes;
import vue.Displayer;

public class Main {

	public static void main(String[] args) {
		FrancoisTests();
	}

	private static void FrancoisTests(){
		Displayer d = new Displayer();
		d.start(new Stage());
	}

	private static void PierreTests(){

		// Test BoxesFactory

		Item i1 = new Item(10, 20, 30);
		Item i2 = new Item(1, 2, 3);
		SetBoxes s = new SetBoxes();
		s.add(i1);
		s.add(i2);
		s.rotatePairBoxes(20, 1);
		
	}

	private static void CharlieTests(){
	
}

}
