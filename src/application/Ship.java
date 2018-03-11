package application;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
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
		private int vel=3;
		ImageView ship1,rock1;
		Circle circle,circle2;
		
	//parametros del animatortimer
		private AnimationTimer timer;
        private int frameCount;
		private int sec;
		private int min;
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		int widthShip = 200;
		int posxShip = 0;
		int posyShip = 300;
		int posyCir = 0;
		int posxCir =300;
		
		//creamos el objeto con su respectivo campo de colision
		ship1 = createObj(new File("src/img/SwordfishII.png"), widthShip, posxShip, posyShip);
		circle = boundObj(widthShip, posyCir+(widthShip/2), posxCir+(widthShip/2)); //le añadimos la mitad del ancho para que en el inicio se coloque correctamente
		
		//rock
		rock1 = createObj(new File("src/img/rock1.png"), 200, 400, 400);
		circle2=boundObj(200, 400+100, 400+100);
		
		//rock move
		
		
		Group root = new Group();
		Scene scene = new Scene(root,WITH,HEIGHT);
		
		scene.setOnMouseMoved((MouseEvent me) -> {
            //System.out.println("Mouse moved, x: " + me.getX() + ", y: " + me.getY());
            ship1.setTranslateX(me.getX()-widthShip/2);
            ship1.setTranslateY(me.getY()-widthShip/2);

            circle.setTranslateX(me.getX());
            circle.setTranslateY(me.getY());
            
            testCollision(circle,circle2);
        });
		root.getChildren().addAll(ship1,rock1,circle,circle2);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	ImageView createObj(File file,int width,double posx,double posy) {
		uri = file.toURI().toString();
		ImageView imageview = new ImageView(uri);
		width = 200;
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
	void transition() {
		
	}
	
	void testCollision(Circle cira, Circle cirb) {
		
		boolean collision = false;
		if (cira.getBoundsInParent().intersects(cirb.getBoundsInParent())) {
			double dx = cira.getTranslateX()-cirb.getTranslateX();
			double dy = cira.getTranslateY()-cirb.getTranslateY();
			double minDist = cira.getRadius() + cirb.getRadius();
			double distance = Math.sqrt(dx * dx + dy * dy);
			if(distance<minDist){
				collision = true;
			}
		}
		if (collision) {
		    System.out.println("colision");
		  }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	void timer() {
		//reloj
	    timer = new AnimationTimer() {

			@Override
            public void handle(long l) {
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
