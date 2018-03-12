package application;

import java.io.File;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Ship extends Application{
	//parametros de la ventana
		int WITH = Pref.getWITH();
		int HEIGHT = Pref.getHEIGHT();
		File file,file1;
		String uri,uri1;
		private int vel=8;
		ImageView ship1;
		Circle circle;
		
	//parametros del animatortimer
		private AnimationTimer timer;
        private int frameCount;
		private int sec;
		private int min;
		
		ArrayList<ImageView> rocks = new ArrayList<>();
		ArrayList<Circle> bondRock = new ArrayList<>();
		ArrayList<Integer> positionX = new ArrayList<>();
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		int widthShip = 120;
		int posxShip = 0;
		int posyShip = 300;
		int posyCir = 0;
		int posxCir =300;
		
		//creamos el objeto con su respectivo campo de colision
		ship1 = createObj(new File("src/img/SwordfishII.png"), widthShip+5, posxShip, posyShip);
		circle = boundObj(widthShip, posyCir+(widthShip/2), posxCir+(widthShip/2)); //le añadimos la mitad del ancho para que en el inicio se coloque correctamente
		
		test();
		test();
		//rock move
		timer();
		
		Group root = new Group();
		Scene scene = new Scene(root,WITH,HEIGHT);
		
		
		scene.setOnMouseMoved((MouseEvent me) -> {
            //System.out.println("Mouse moved, x: " + me.getX() + ", y: " + me.getY());
            ship1.setTranslateX(me.getX()-widthShip/2);
            ship1.setTranslateY(me.getY()-widthShip/2);

            circle.setTranslateX(me.getX());
            circle.setTranslateY(me.getY());
            
        });
		for (int i = 0; i < rocks.size(); i++) {
			root.getChildren().addAll(rocks.get(i),bondRock.get(i));
		}
		
		root.getChildren().addAll(ship1,circle);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	void test() {
		int positionY = (int) (Math.random() * 600) + 1;
		//int widthRock = (int) (Math.random() * 200) + 100;
		rocks.add(createObj(new File("src/img/rock1.png"), 180, WITH, positionY));
		bondRock.add(boundObj(180, WITH+90, positionY+90));
		positionX.add(WITH);
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
		cir.setTranslateX(posx);
		cir.setTranslateY(posy);
		return cir;
	}
	
	void testCollision() {
		for (int i = 0; i < bondRock.size(); i++) {
			boolean collision = false;
			if (circle.getBoundsInParent().intersects(bondRock.get(i).getBoundsInParent())) {
				double dx = circle.getTranslateX()-bondRock.get(i).getTranslateX();
				double dy = circle.getTranslateY()-bondRock.get(i).getTranslateY();
				double minDist = circle.getRadius() + bondRock.get(i).getRadius();
				double distancexx = Math.sqrt(dx * dx + dy * dy);
				if(distancexx<minDist){
					collision = true;
				}
			}
			if (collision) {System.out.println("colision");}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	int posXs = WITH;
	void move() {
		posXs-=vel;
		for (int i = 0; i < rocks.size(); i++) {
			positionX.set(i, positionX.get(i)-vel);
			rocks.get(i).setTranslateX(positionX.get(i));
			bondRock.get(i).setTranslateX(positionX.get(i)+100);
			if (positionX.get(i)<-200) {
				positionX.set(i, WITH);
				int positionY = (int) (Math.random() * 600) + 1;
				rocks.get(i).setTranslateY(positionY);
				bondRock.get(i).setTranslateY(positionY+100);
			}
		}
		//bondRock.get(0).setTranslateX(posXs+100);
	}
	void timer() {
		//reloj
	    timer = new AnimationTimer() {
			@Override
            public void handle(long l) {
				move();
	            testCollision();
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
