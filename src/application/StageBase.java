package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class StageBase  extends Application {
	//parametros de la ventana
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();
    boolean pause = false; //si nos morimos, para la animacion y todo :)
	//este sera nuestro escenario principal y nuestro grupo principal
	Scene scene;
	Group root;
	
	//grupoas a añadir
	Group statusG,stageFondoG;
	
	//clases de los grupos
	StatusBar statusBar;
	StageFondo stageFondo;
	SuperShip superShip;
	RockA itemScore;
	ArrayList<RockA> rocks;
	
	//reloj
    private AnimationTimer timer;
    int frameCount=0;
    int sec=0;
    int min=0;
    
    //parametros de juego
    String lvl,character;
    int ship;
    int vel=2;
    
    public StageBase() {}
    
    public StageBase(String lvlExt, int shipExt, String charExt) {
    	lvl=lvlExt;
    	ship=shipExt;
    	character=charExt;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		pause = false;
		//Instanciamos el grupo y escena principal
		root = new Group();
		scene = new Scene(root, WITH, HEIGHT);
		
		//instanciamos la clase del statusbar
		statusBar = new StatusBar(lvl,character);
		statusG = statusBar.start(primaryStage);
		superShip = new SuperShip(scene,ship);
		timer(primaryStage);
		
		//instanciamos la clase del fondo
		stageFondo = new StageFondo();
		stageFondoG = stageFondo.start(primaryStage);
		
		//instanciamos la nave
		superShip.createShip();
		//funcion de teclas :)
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
	            	superShip.move(1);
					break;
				case DOWN:
	            	superShip.move(0);
					break;
				case LEFT:
	            	superShip.move(2);
					break;
				case RIGHT:
	            	superShip.move(3);
					break;
				default:
					break;
				}
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
	            	superShip.move(1);
					break;
				case DOWN:
	            	superShip.move(0);
					break;
				case LEFT:
	            	superShip.move(2);
					break;
				case RIGHT:
	            	superShip.move(3);
					break;
				default:
					break;
				}
			}
		});
		
		//añadimos al grupo principal los grupos hijos
		root.getChildren().add(stageFondoG);
		root.getChildren().add(statusG);
		
		root.getChildren().addAll(superShip.getShip1(),superShip.getCircle());
		
		//instanciamos el meteoro
		rocks = new ArrayList<>();
		int count=vel*2;
		for (int i = 0; i < 1; i++) {
			rocks.add(new RockA(vel*3,"src/img/rock1.png"));
			rocks.get(i).createObj();
			root.getChildren().addAll(rocks.get(i).getRocks(),rocks.get(i).getBondRock());
		}
		//instanciamos el objeto extra :)
		itemScore=new RockA(vel*3, "src/img/photo.jpg");
		itemScore.createObj();
		root.getChildren().addAll(itemScore.getRocks(),itemScore.getBondRock());
		
		//mostramos la escena con todos los grupos
		scene.setCursor(Cursor.NONE); // para ocultar el mouse
		primaryStage.setScene(scene);
	    primaryStage.show();
	}
	public static void main(String[] args) {
        launch(args);
    }
	void reset() {
		frameCount=0;
		sec=0;
		min=0;
	}
	int score = 0;
	void timer(Stage primary) {
		//reloj
	    timer = new AnimationTimer() {

			@Override
            public void handle(long l) {
            	frameCount++; //cuenta los fps
				if (!pause) {
	            	stageFondo.moveBackground(); // mueve el fondo de pantalla
	            	for (int i = 0; i < rocks.size(); i++) {
	            		rocks.get(i).move(); // mueve las rocas :)
	            		itemScore.move(); // mueve el item extra :)
	            		if(rocks.get(i).searchCollision(superShip.getCircle())) {
	            			pause = true;
	            		}
	            		if (itemScore.searchCollision(superShip.getCircle())) {
							score++;
						}
					}
	            	
	            	if(frameCount%60==0) {
	            		sec++;
		            	score+=vel;
		            	//cada x secundos aumenta la velocidad
	            		if (sec%5==0) {
	            			statusBar.setTexts(String.format("%02d-1",sec/5),1);//ponemos el nivel actual
							for (int j = 0; j < rocks.size(); j++) {
								vel+=1;
								rocks.get(j).setVel(vel);
							}
							itemScore.setVel(vel/2);
						}
	            		if (frameCount%3600==0) {
	            			sec=0;
							min++;
							
						}
	            		statusBar.setTexts(String.format("%02d:%02d",min, sec),0);
	            	}
	            	statusBar.setTexts(String.format("%05d$",score),2);
					
				}else {
					DeathScreen ds = new DeathScreen(); //crea la pantalla de la muerte
					try {ds.start(primary,score,character);} catch (Exception e) {e.printStackTrace();}
        			timer.stop(); //se para el contador
        			//root.getChildren().add(ds.getBackground());
					root.getChildren().clear(); // se limpia el escenario
        			reset(); // y se ponen los valores a 0
				}
            }
        };
        timer.start();
	}
}
