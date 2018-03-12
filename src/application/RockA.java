package application;

import java.io.File;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RockA extends Application{
	//parametros de la ventana
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();
	ImageView rocks;
	Circle bondRock;
	int positionX=0,positionY=0;
	int wImg = 180,rImg=wImg/2;
	private String uri;
	private AnimationTimer timer;
	private int vel=6;
	public static void main(String[] args) {
		launch(args);
	}
	
	public ImageView getRocks() {
		return rocks;
	}
	public Circle getBondRock() {
		return bondRock;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
        final Group root = new Group();
		// TODO Auto-generated method stub
        createObj();
		timer();
		root.getChildren().addAll(rocks,bondRock);

        final Scene scene = new Scene(root, WITH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	void createObj() {
		int positionY = (int) (Math.random() * 600) + 1;
		//int widthRock = (int) (Math.random() * 200) + 100;
		rocks=createObj(new File("src/img/rock1.png"), wImg, WITH, positionY);
		bondRock=boundObj(wImg, WITH, positionY);
	}
	
	ImageView createObj(File file,int width,double posx,double posy) {
		uri = file.toURI().toString();
		ImageView imageview = new ImageView(uri);
		imageview.setFitWidth(width);
		imageview.setTranslateX(posx);
		imageview.setTranslateY(posy);
		imageview.setPreserveRatio(true);
		return imageview;
	}
	
	Circle boundObj(int width,double posx,double posy) {
		Circle cir = new Circle(width/2);
		cir.setFill(null);
		cir.setStroke(Color.BLUE);
		cir.setTranslateX(posx+rImg);
		cir.setTranslateY(posy+rImg);
		return cir;
	}
	
	void setPositionObj() {
		positionY = (int) (Math.random() * HEIGHT) + 1;
		positionX = (int) (Math.random() * WITH/2) + WITH;
		rocks.setTranslateX(positionX);
		rocks.setTranslateY(positionY);
		bondRock.setTranslateX(positionX+rImg);
		bondRock.setTranslateY(positionY+rImg);
	}
	
	void move() {
		positionX-=vel;
		rocks.setTranslateX(positionX);
		bondRock.setTranslateX(positionX+rImg);
		if (positionX<-300) {
			setPositionObj();
		}
	}
	
	void timer() {
		//reloj
	    timer = new AnimationTimer() {
			private int frameCount=0;
			private int sec;
			private int min;

			@Override
            public void handle(long l) {
				frameCount++;
				move();
            	if(frameCount%60==0) {
            		sec++;
            		if (frameCount%3600==0) {
            			sec=0;
						min++;
					}
            	}
            }
        };
        timer.start();
	}

}
