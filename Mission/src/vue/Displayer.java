package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Displayer {

	public void start(Stage primaryStage) {

		Group root = new Group();
		Scene scene = new Scene(root, 800, 600, Color.LIGHTBLUE);
		primaryStage.setScene(scene);
		Circle cercle = new Circle();
		cercle.setCenterX(300);// réglage de la position, de la taille et de la

		cercle.setCenterY(200);
		cercle.setRadius(100);
		cercle.setFill(Color.YELLOW);
		cercle.setStroke(Color.ORANGE);// réglage de la couleur de la bordure et
										// de son épaisseur
		cercle.setStrokeWidth(5);
		root.getChildren().add(cercle);// on ajoute le cercle au groupe root

		primaryStage.show();
	}

}