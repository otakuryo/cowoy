package application;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DeathScreen extends Application {
	//parametros de la ventana	
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();

	private ImageView background;
	private String MEDIA_URL_IMG,MEDIA_FILE = "src/img/death.png";

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		File file = new File(MEDIA_FILE);
		MEDIA_URL_IMG = file.toURI().toString();
		background=new ImageView(MEDIA_URL_IMG);
		background.setFitHeight(HEIGHT);
		background.setPreserveRatio(true);
		
		Group root = new Group();
		Scene scene = new Scene(root,WITH,HEIGHT);
		
		root.getChildren().add(background);
		arg0.setScene(scene);
		arg0.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
