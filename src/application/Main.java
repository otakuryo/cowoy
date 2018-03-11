package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {
		
	//parametros de la ventana	
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();
	StackPane root;
	Scene scene;
 
    @Override
    public void start(Stage primaryStage) throws Exception {
    	//creamos la pantalla
        root = new StackPane();

        //definimos el tamaño (para muestra practica, se pasara una uri :) )
        scene = new Scene(root, WITH, HEIGHT);
        Video video = new Video( root, scene);
        video.start(primaryStage);
        
        //asignamos la escena a primaryStage
        primaryStage.setTitle("Cowoy Bebop by Ryo :)");
		primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}
