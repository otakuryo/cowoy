package views;

import application.Main;
import application.Video;
import config.Pref;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SelectionMain extends Application {

	//parametros de la ventana
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();


	GridPane grid;
	Scene scene;
	@Override
	public void start(Stage primaryStage) throws Exception {

		
        primaryStage.setTitle("Cowoy Bebop by Ryo :)");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	void addForm(Stage primaryStage) {
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
