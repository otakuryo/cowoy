package application;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
	Image[] img = new Image[3];
	ImageView ship1;
	Circle circle;
	int nave=1;
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
	/*
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
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
		createImg();
		//creamos el objeto
		ship1 = createObj(widthShip+5, posxShip, posyShip);
		circle = boundObj(widthShip, posyCir+(widthShip/2), posxCir+(widthShip/2)); //le añadimos la mitad del ancho para que en el inicio se coloque correctamente
		
		//añadimos un listener al puntero, para mover la nave
		scene.setOnMouseMoved((MouseEvent me) -> {
			movMouseShip(me);
			ship1.setTranslateX(me.getX()-widthShip/2);
            ship1.setTranslateY(me.getY()-widthShip/2);

            circle.setTranslateX(me.getX());
            circle.setTranslateY(me.getY());
            
        });
	}
	
	//este metodo cambia la posicion de la navve con respecto al mouse :)
	void movMouseShip(MouseEvent me) {
		if (me.getY()>(ship1.getTranslateY()+widthShip/2)) {
			ship1.setImage(img[2]);
		}else if(me.getY()<(ship1.getTranslateY()+widthShip/2)) {
			ship1.setImage(img[0]);
		}
	}
	
	//metodo para crear naves :)
	ImageView createObj(int width,double posx,double posy) {
		//uri = file.toURI().toString();
		ImageView imageview = new ImageView(img[1]);
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
	//Se selecciona la imagen para el tipo de imagen
	void createImg() {
		File[] fil = new File[3];
		
		if (nave==0) {
			fil[0] = new File("src/img/SwordfishII.png");
			fil[1] = new File("src/img/SwordfishII.png");
			fil[2] = new File("src/img/SwordfishII.png");
		}else if(nave==1) {
			fil[0] = new File("src/img/Faye0.png");
			fil[1] = new File("src/img/Faye1.png");
			fil[2] = new File("src/img/Faye2.png");
		}else if(nave==2) {
			fil[0] = new File("src/img/jet0.png");
			fil[1] = new File("src/img/jet1.png");
			fil[2] = new File("src/img/jet2.png");
		}else if(nave==3) {
			fil[0] = new File("src/img/Ein.png");
			fil[1] = new File("src/img/Ein.png");
			fil[2] = new File("src/img/Ein.png");
		}
		for (int i = 0; i < fil.length; i++) {
			img[i] = new Image(fil[i].toURI().toString());
		}
		
	}
	//cuerpo de campo de colision
	Circle boundObj(int width,double posx,double posy) {
		Circle cir = new Circle(width/2);
		cir.setFill(null);
		if (nave == 3) {
			cir.setStroke(Color.AQUA);
		}else {
			cir.setStroke(Color.AQUA);
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
			ship1.setImage(img[2]);
		}
		if (moveUP && ship1.getTranslateY()>2) {
			ship1.setTranslateY(ship1.getTranslateY()-constant);
			circle.setTranslateY(circle.getTranslateY()-constant);
			ship1.setImage(img[0]);
		}
		if (moveLEFT && ship1.getTranslateX()>2) {
			ship1.setTranslateX(ship1.getTranslateX()-constant);
			circle.setTranslateX(circle.getTranslateX()-constant);
			if (!moveDOWN && !moveUP) {
				ship1.setImage(img[1]);	 
			}
		}
		if (moveRIGHT && ship1.getTranslateX()<(WITH-widthShip)) {
			ship1.setTranslateX(ship1.getTranslateX()+constant);
			circle.setTranslateX(circle.getTranslateX()+constant);
			if (!moveDOWN && !moveUP) {
				ship1.setImage(img[1]);	 
			}
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
