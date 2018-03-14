package application;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
	
	int constant = 10; //este es la velocidad de la nave
	
	//movimiento de la nave con keys
	boolean moveUP;
	boolean moveDOWN;
	boolean moveLEFT;
	boolean moveRIGHT;
		
	//escenario
	Scene scene;
	public SuperShip(Scene scene,int nave) {
		this.scene = scene;
		this.nave = nave;
		switch (nave) {
		case 0:pathFile="src/img/SwordfishII.png";break;
		case 1:pathFile="src/img/Faye.png";break;
		case 2:pathFile="src/img/Jet.png";break;
		case 3:pathFile="src/img/Ein.png";break;

		default:pathFile="src/img/SwordfishII.png";break;}
		posMoveKey();
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
		circle = boundObj(widthShip, posyCir+(widthShip/2), posxCir+(widthShip/2)); //le añadimos la mitad del ancho para que en el inicio se coloque correctamente
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
	//cuerpo de campo de colision
	Circle boundObj(int width,double posx,double posy) {
		Circle cir = new Circle(width/2);
		cir.setFill(null);
		if (nave == 3) {
			cir.setStroke(Color.AQUA);
		}else {
			cir.setStroke(Color.TRANSPARENT);
		}
		cir.setTranslateX(posx);
		cir.setTranslateY(posy);
		return cir;
	}
	
	//movmiento de la nave :)
	void move() {
		if (moveDOWN && ship1.getTranslateY()<(HEIGHT-widthShip)) {
			ship1.setTranslateY(ship1.getTranslateY()+constant);
			circle.setTranslateY(circle.getTranslateY()+constant);
		}
		if (moveUP && ship1.getTranslateY()>2) {
			ship1.setTranslateY(ship1.getTranslateY()-constant);
			circle.setTranslateY(circle.getTranslateY()-constant);
		}
		if (moveLEFT && ship1.getTranslateX()>2) {
			ship1.setTranslateX(ship1.getTranslateX()-constant);
			circle.setTranslateX(circle.getTranslateX()-constant);
		}
		if (moveRIGHT && ship1.getTranslateX()<(WITH-widthShip)) {
			ship1.setTranslateX(ship1.getTranslateX()+constant);
			circle.setTranslateX(circle.getTranslateX()+constant);
		}
	}
	//funcion de teclas :)
	void posMoveKey() {
		//buscar otro metodo mas eficaz :)
		//Cuando presionas
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:moveUP=true;break;
				case DOWN:moveDOWN=true;break;
				case LEFT:moveLEFT=true;break;
				case RIGHT:moveRIGHT=true;break;
				
				default:break;
				}
			}
		});
		//cuando sueltas 
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:moveUP=false;break;
				case DOWN:moveDOWN=false;break;
				case LEFT:moveLEFT=false;break;
				case RIGHT:moveRIGHT=false;break;
				
				default:break;
				}
			}
		});
	}
}
