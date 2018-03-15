package application;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DeathScreen{
	//parametros de la ventana	
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();
	
	Group root;
	Scene scene;
	
	private ImageView background;
	private Text scoreTxt;
	private String MEDIA_URL_IMG,MEDIA_FILE = "src/img/death.png";
	private AnimationTimer timer;

	public ImageView getBackground() {
		return background;
	}
	public boolean start(Stage primaryStage,int score,String pilot) throws Exception {
		timer(primaryStage);
		backSet();
		setScore(score,pilot);
		root = new Group();
		scene = new Scene(root,WITH,HEIGHT);
		root.getChildren().add(background);
		root.getChildren().add(scoreTxt);
		primaryStage.setScene(scene);
		//primaryStage.show();
		return false;
		
	}
	public void backSet() {
		File file = new File(MEDIA_FILE);
		MEDIA_URL_IMG = file.toURI().toString();
		background=new ImageView(MEDIA_URL_IMG);
		background.setFitHeight(HEIGHT);
		background.setPreserveRatio(true);
	}

	void setScore(int score,String pilot) {
		scoreTxt = new Text();
		scoreTxt.setText(pilot+": "+String.format("%05d$",score));
		scoreTxt.setTextAlignment(TextAlignment.CENTER);
		scoreTxt.setFill(Color.WHITE);
		scoreTxt.setStyle("-fx-font: 62 arial;");
		scoreTxt.setTranslateX(0);
		scoreTxt.setWrappingWidth(WITH);
		scoreTxt.setTranslateY((HEIGHT/2)-150);
	}
	static boolean pause = false;
	boolean timer(Stage primaryStage) {
		//reloj
	    timer = new AnimationTimer() {
			private int frameCount=0;
			private int sec;

			@Override
            public void handle(long l) {
				frameCount++;
            	if(frameCount%60==0) {
            		sec++;
            		if (sec > 5) {
            			timer.stop();
						pause = true;
						MenuP menuP = new MenuP();
						try {
							menuP.start(primaryStage);
						} catch (Exception e) {
							e.printStackTrace();
						}
						//System.out.println("perfecto :)");
					}
            	}
            }
        };
        timer.start();
		return pause;
	}
}
