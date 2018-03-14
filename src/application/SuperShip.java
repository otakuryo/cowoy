package application;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SuperShip{
	//parametros de la ventana
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();
	
	//parametros nave
	File file;
	String uri,pathFile="src/img/SwordfishII.png";
	ImageView ship1;
	Circle circle;
	int nave;
	int widthShip = 120;
	int posxShip = 0;
	int posyShip = 300;
	int posyCir = 0;
	int posxCir =300;
	
	//escenario
	Scene scene;
	public SuperShip(Scene scene,int nave) {
		this.scene = scene;
		this.nave = nave;
		switch (nave) {
		case 0:
			pathFile="src/img/SwordfishII.png";
			break;
		case 1:
			pathFile="src/img/Faye.png";
			break;
		case 2:
			pathFile="src/img/Jet.png";
			break;
		case 3:
			pathFile="src/img/Ein.png";
			break;

		default:
			pathFile="src/img/SwordfishII.png";
			break;
		}
	}
	
	public ImageView getShip1() {
		return ship1;
	}
	public Circle getCircle() {
		return circle;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public Scene getScene() {
		return scene;
	}
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	/*
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new Group();
		scene = new Scene(root,WITH,HEIGHT);
		createShip();
		
		root.getChildren().addAll(ship1,circle);	
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	*/
	void createShip() {
		//creamos el objeto
		ship1 = createObj(new File(pathFile), widthShip+5, posxShip, posyShip);
		circle = boundObj(widthShip, posyCir+(widthShip/2), posxCir+(widthShip/2)); //le a�adimos la mitad del ancho para que en el inicio se coloque correctamente
		scene.setOnMouseMoved((MouseEvent me) -> {
            ship1.setTranslateX(me.getX()-widthShip/2);
            ship1.setTranslateY(me.getY()-widthShip/2);

            circle.setTranslateX(me.getX());
            circle.setTranslateY(me.getY());
            
        });
	}
	
	ImageView createObj(File file,int width,double posx,double posy) {
		uri = file.toURI().toString();
		ImageView imageview = new ImageView(uri);
		if (nave==0 || nave == 3) {
			imageview.setFitWidth(width);
		}else {
			imageview.setFitHeight(width);
		}
		imageview.setTranslateX(posx);
		imageview.setTranslateY(posy);
		imageview.setPreserveRatio(true);
		return imageview;
	}
	
	Circle boundObj(int width,double posx,double posy) {
		Circle cir = new Circle(width/2);
		cir.setFill(null);
		cir.setStroke(Color.RED);
		cir.setTranslateX(posx);
		cir.setTranslateY(posy);
		return cir;
	}
	int constant = 10;
	void move(int pos) {
		if (pos==0) {
			ship1.setTranslateY(ship1.getTranslateY()+constant);
			circle.setTranslateY(circle.getTranslateY()+constant);
		}
		if (pos==1) {
			ship1.setTranslateY(ship1.getTranslateY()-constant);
			circle.setTranslateY(circle.getTranslateY()-constant);
		}
		if (pos==2) {
			ship1.setTranslateX(ship1.getTranslateX()-constant);
			circle.setTranslateX(circle.getTranslateX()-constant);
		}
		if (pos==3) {
			ship1.setTranslateX(ship1.getTranslateX()+constant);
			circle.setTranslateX(circle.getTranslateX()+constant);
		}
	}
}
