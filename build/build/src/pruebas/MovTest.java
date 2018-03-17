package pruebas;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MovTest extends Application{
	Circle rects;
	Circle circleCenter;
	int width = 1024,height = 768;
	double posx =0,posy=height*Math.random(),vel=7;
	double posxini =0,posyini=0;
	private AnimationTimer timer;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Group g = new Group();
		Scene scene = new Scene(g,width,height);
		rects = createObj(g);
		circleCenter = createObj2(g,width/2,height/2);
		timer();
		
		arg0.setScene(scene);
		arg0.show();
	}
	Circle createObj(Group g) {
		resetPos();
		Circle rect = new Circle(60, Color.BLUEVIOLET);
		rect.setTranslateX(posx);
		rect.setTranslateY(posy);
		g.getChildren().add(rect);
		return rect;
	}
	Circle createObj2(Group g,double posxe,double posye) {
		Circle rect = new Circle(60, Color.BLUEVIOLET);
		rect.setTranslateX(posxe);
		rect.setTranslateY(posye);
		g.getChildren().add(rect);
		return rect;
	}
	void resetPos() {
		posy = height*Math.random();
		posx = 0;
		posxini = posx;
		if (posy<height/2) {
			posyini = posy;
		}else {
			posyini = height-posy;
		}
	}
	void move() {
		if(width/2>posx && height/2>posy) {
			double h1= (height/2)-posyini;
			double w1 = width/2;
			double x = w1-posx;
			double n = (h1/w1)*w1;
			double y = ((-(h1/w1)*x)+n)+posyini;
			posx+=vel;
			posy=y;
			rects.setTranslateX(posx);
			rects.setTranslateY(posy);
		}else if(width/2>posx && height/2<posy) {
			double h1= (height/2)-posyini;
			double w1 = width/2;
			double n = (h1/w1)*w1;
			double x = w1-posx;
			double y = (((h1/w1)*x)+n)+posyini;
			posx+=vel;
			posy=y;
			rects.setTranslateX(posx);
			rects.setTranslateY(posy);
		}
	}
	void move2() {
		if(width/2>posx && height/2>posy) {
			System.out.println("1");
			double h1= (height/2)-posyini;
			double w1 = width/2;
			double x = w1-posx;
			double n = (h1/w1)*w1;
			double y = ((-(h1/w1)*x)+n)+posyini;
			posx+=vel;
			posy=y;
			rects.setTranslateX(posx);
			rects.setTranslateY(posy);
		}else if(width/2>posx && height/2<posy) {
			double h1= (height/2)-posyini;
			double w1 = width/2;
			double n = (h1/w1)*w1;
			double x = w1-posx;
			double y = (((h1/w1)*x)+n)+posyini;
			posx+=vel;
			posy=y;
			rects.setTranslateX(posx);
			rects.setTranslateY(posy);
		}
	}
	void intersect() {
		if (circleCenter.getBoundsInParent().intersects(rects.getBoundsInParent())) {
			double dx = circleCenter.getTranslateX()-rects.getTranslateX();
			double dy = circleCenter.getTranslateY()-rects.getTranslateY();
			double minDist = circleCenter.getRadius() + rects.getRadius();
			double distancexx = Math.sqrt(dx * dx + dy * dy);
			if(distancexx<minDist){
				resetPos();
			}
		}
	}
	
	boolean intersectExterbal(Circle circle) {
		boolean collision = false;
		if (circle.getBoundsInParent().intersects(rects.getBoundsInParent())) {
			double dx = circle.getTranslateX()-rects.getTranslateX();
			double dy = circle.getTranslateY()-rects.getTranslateY();
			double minDist = circle.getRadius() + rects.getRadius();
			double distancexx = Math.sqrt(dx * dx + dy * dy);
			if(distancexx<minDist){
				collision = true;
				resetPos();
			}
		}
		return collision;
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
				intersect();
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
