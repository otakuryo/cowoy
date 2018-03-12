package application;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DeathScreen{
	//parametros de la ventana	
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();

	private ImageView background;
	private String MEDIA_URL_IMG,MEDIA_FILE = "src/img/death.png";
	private AnimationTimer timer;

	public ImageView getBackground() {
		return background;
	}
	public boolean start(Stage primaryStage) throws Exception {
		//timer();
		backSet();
		Group root = new Group();
		Scene scene = new Scene(root,WITH,HEIGHT);
		root.getChildren().add(background);
		primaryStage.setScene(scene);
		primaryStage.show();
		return false;
		
	}
	public void backSet() {
		// TODO Auto-generated method stub

		File file = new File(MEDIA_FILE);
		MEDIA_URL_IMG = file.toURI().toString();
		background=new ImageView(MEDIA_URL_IMG);
		background.setFitHeight(HEIGHT);
		background.setPreserveRatio(true);
	}

	static boolean pause = false;
	boolean timer() {
		//reloj
	    timer = new AnimationTimer() {
			private int frameCount=0;
			private int sec;
			private int min;

			@Override
            public void handle(long l) {
				frameCount++;
            	if(frameCount%60==0) {
            		sec++;
            		if (sec > 3) {
						pause = true;
					}
            	}
            }
        };
        timer.start();
		return pause;
	}
}
