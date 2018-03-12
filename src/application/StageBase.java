package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageBase  extends Application {
	//parametros de la ventana
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();
	//este sera nuestro escenario principal y nuestro grupo principal
	Scene scene;
	Group root;
	
	//grupoas a añadir
	Group statusG,stageFondoG;
	
	//clases de los grupos
	StatusBar statusBar;
	StageFondo stageFondo;
	SuperShip superShip;
	RockA rockA,rockB;
	ArrayList<RockA> rocks;
	
	//reloj
    private AnimationTimer timer;
    int frameCount=0;
    int sec=0;
    int min=0;
    
    //parametros de juego
    String lvl,character;
    int ship;
    int vel=1;
    
    public StageBase() {}
    
    public StageBase(String lvlExt, int shipExt, String charExt) {
    	lvl=lvlExt;
    	ship=shipExt;
    	character=charExt;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Instanciamos el grupo y escena principal
		root = new Group();
		scene = new Scene(root, WITH, HEIGHT);
		
		//instanciamos la clase del statusbar
		statusBar = new StatusBar(lvl,character);
		statusG = statusBar.start(primaryStage);
		superShip = new SuperShip(scene);
		timer();
		
		//instanciamos la clase del fondo
		stageFondo = new StageFondo();
		stageFondoG = stageFondo.start(primaryStage);
		
		//instanciamos la nave
		superShip.createShip();
		
		//añadimos al grupo principal los grupos hijos
		root.getChildren().add(stageFondoG);
		root.getChildren().add(statusG);
		
		root.getChildren().addAll(superShip.getShip1(),superShip.getCircle());
		
		//instanciamos el meteoro
		rocks = new ArrayList<>();
		int count=vel*3;
		for (int i = 0; i < count; i++) {
			rocks.add(new RockA(6));
			rocks.get(i).createObj();
			root.getChildren().addAll(rocks.get(i).getRocks(),rocks.get(i).getBondRock());
		}
		
		//mostramos la escena con todos los grupos
		scene.setCursor(Cursor.NONE); // para ocultar el mouse
		primaryStage.setScene(scene);
	    primaryStage.show();
	}
	public static void main(String[] args) {
        launch(args);
    }
	
	void timer() {
		//reloj
	    timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
            	frameCount++;
            	
            	stageFondo.moveBackground();
            	for (int i = 0; i < rocks.size(); i++) {
            		rocks.get(i).move();
            		rocks.get(i).searchCollision(superShip.getCircle());
				}
            	
            	if(frameCount%60==0) {
            		sec++;
            		if (frameCount%3600==0) {
            			sec=0;
						min++;
					}
            		statusBar.setTexts(String.format("%02d:%02d",min, sec),0);
            	}
            	statusBar.setTexts(String.format("%05d$",frameCount/10),2);
            }
        };
        timer.start();
	}
}
