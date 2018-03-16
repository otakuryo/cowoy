package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    int ship=1;
    int vel=2;
	int score = 0;
    
    public StageBase() {}
    
    public StageBase(String lvlExt, int shipExt, String charExt) {
    	lvl=lvlExt;
    	ship=shipExt;
    	character=charExt;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//El orden en que se coloca en el esceneario es muy importante Xo
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
		stageFondo = new StageFondo(ship);
		stageFondoG = stageFondo.start(primaryStage);
		
		//añadimos al grupo principal los grupos hijos
		root.getChildren().add(stageFondoG);
		root.getChildren().add(statusG);
		
		
		//instanciamos el objeto extra :)
		itemScore=new RockA(vel*3,3);
		itemScore.createObj();
		root.getChildren().addAll(itemScore.getRocks(),itemScore.getBondRock());

		//instanciamos la nave
		superShip.createShip();
		root.getChildren().addAll(superShip.getShip1(),superShip.getCircle());

		//instanciamos el meteoro
		rocks = new ArrayList<>();
		int count=vel*2;
		for (int i = 0; i < count; i++) {
			rocks.add(new RockA(vel*3,ship)); //añadimos una roca al arraylist
			rocks.get(i).createObj(); //iniciamos el metodo de configuracion
			root.getChildren().addAll(rocks.get(i).getRocks(),rocks.get(i).getBondRock()); //se añade al escenario
		}
				
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
	void movExit() {
		FadeTransition ft = new FadeTransition(Duration.millis(2000),root);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}
	
	//todo el movimiento se encuentra aqui, solo hay 2 timers para todo el proyecto :)
	void timer(Stage primary) {
		//reloj
	    timer = new AnimationTimer() {

			@Override
            public void handle(long l) {
            	frameCount++; //cuenta los fps
            	superShip.move();
				if (!pause) {
	            	stageFondo.moveBackground(); // mueve el fondo de pantalla
	            	for (int i = 0; i < rocks.size(); i++) {
	            		rocks.get(i).move(); // mueve las rocas :)
	            		itemScore.move(); // mueve el item extra :)
	            		if(rocks.get(i).searchCollision(superShip.getCircle())) {
	            			pause = true; //pausamos todos los metodos del stage
	            			movExit(); //creamos un tipo de fade out
		        			reset(); // y se ponen los valores a 0
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
					if (frameCount>60) {
						DeathScreen ds = new DeathScreen(); //crea la pantalla de la muerte					
						try {ds.start(primary,score,character);} catch (Exception e) {e.printStackTrace();}
	        			timer.stop(); //se para el contador
						root.getChildren().clear(); // se limpia el escenario
					}
				}
            }
        };
        timer.start();
	}
}
