package application;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StageFondo extends Application {
	//parametros de la ventana
		int WITH = Pref.getWITH();
		int HEIGHT = Pref.getHEIGHT();
		File file;
		String uri;
		
	ImageView background, background1,background2;
	private TranslateTransition translate;

	@Override
	public void start(Stage primaryStage) throws Exception {
		file = new File("src/img/background.png");
		uri = file.toURI().toString();
		background=new ImageView(uri);
		background.setX(0);
		background1=new ImageView(uri);
		//background1.setX(2048);
		timer();
		Group root = new Group(background,background1);
		Scene scene = new Scene(root,WITH,HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	void move() {
		background.setTranslateX(2);
		background.setTranslateY(2);
		
		translate = new TranslateTransition(Duration.seconds(10),background);
		translate.setFromX(-1000);
		translate.setToX(1280);
        translate.setCycleCount(Timeline.INDEFINITE);
        translate.setAutoReverse(false);
        translate.play();
	}
    private int posb0=0,posb1=2048,vel=3; //interesante :o
	
	void timer() {
		//reloj
	    AnimationTimer timer = new AnimationTimer() {
			@Override
            public void handle(long l) {
            	posb0-=vel;
            	posb1-=vel;
            	background.setTranslateX(posb0);
            	background1.setTranslateX(posb1);
            	//System.out.println(background.getTranslateX()+" -> "+posb0 + " - "+background1.getTranslateX()+" -> "+posb1);
            	if (background.getTranslateX() < -2036) {
            		posb0=2046;
				}
            	if (background1.getTranslateX() < -2036) {
            		posb1=2046;
				}
            }
        };
        timer.start();
	}

}
