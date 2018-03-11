package application;
	
import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        
        MenuP menuP = new MenuP();
        //menuP.start(primaryStage);
        
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
