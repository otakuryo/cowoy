package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {
	
	//Buscamos el VIDEO y lo convertimos en URI
	private static File file = new File("src/video.mp4");
	private static final String MEDIA_URL = file.toURI().toString();
	
	//Buscamos el AUDIO y lo convertimos en URI
	private static File fileSound= new File("src/sound.m4a");
	private static final String MEDIA_URL_SOUND = fileSound.toURI().toString();
	
	//parametros de la ventana
	int WITH = 1024;
	int HEIGHT = 768;
	StackPane root;
	Scene scene;
 
    @Override
    public void start(Stage primaryStage) throws Exception {
    	//creamos la pantalla
        root = new StackPane();

        //definimos el tamaño (para muestra practica, se pasara una uri :) )
        scene = new Scene(root, WITH, HEIGHT);
        Video video = new Video(MEDIA_URL, MEDIA_URL_SOUND, root, scene);
        video.start(primaryStage);
        
        //asignamos la escena a primaryStage
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
