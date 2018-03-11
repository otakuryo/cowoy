package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageBase  extends Application {
	//parametros de la ventana
	int WITH = 1024;
	int HEIGHT = 768;
	//este sera nuestro escenario principal y nuestro grupo principal
	Scene scene;
	Group root;
	
	//grupoas a añadir
	Group statusG;
	
	//clases de los grupos
	StatusBar statusBar;
	
	//reloj
    private AnimationTimer timer;
    int frameCount=0;
    int sec=0;
    int min=0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Instanciamos el grupo y escena principal
		root = new Group();
		scene = new Scene(root, WITH, HEIGHT);
		
		//instanciamos la clase
		statusBar = new StatusBar("1-1","Jet");
		statusG = statusBar.start(primaryStage);
		timer();
		
		//añadimos al grupo principal los grupos hijos
		root.getChildren().add(statusG);
		
		//mostramos la escena con todos los grupos
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
