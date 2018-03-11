package application;

import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StageFondo extends Application {
	//parametros de la ventana
		int WITH = Pref.getWITH();
		int HEIGHT = Pref.getHEIGHT();
		
	ImageView background;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
