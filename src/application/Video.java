package application;

import java.io.File;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Video {
	
    //Buscamos el VIDEO y AUDIO
	private static String MEDIA_URL;
	private static String MEDIA_URL_SOUND;
	
	//parametros de la ventana
	StackPane root;
	Scene scene;
    
    public Video(String video, String sound,StackPane root,Scene scene) {
    	MEDIA_URL = video;
    	MEDIA_URL_SOUND = sound;
		this.root = root;
		this.scene = scene;
	}

    public void start(Stage primaryStage){
        //creamos el reproductor del video
        MediaPlayer playerSound = new MediaPlayer(new Media(MEDIA_URL_SOUND));
        MediaView mediaViewSound = new MediaView(playerSound);
        playerSound.setCycleCount(10);
        playerSound.cycleCountProperty();
        
        //creamos el reproductor del video
        MediaPlayer player = new MediaPlayer(new Media(MEDIA_URL));
        player.setMute(true);
        MediaView mediaView = new MediaView(player);
        mediaView.autosize();
        
        //lo añadimos a la ventana
        root.getChildren().add(mediaView);
        root.getChildren().add(mediaViewSound);
        
        //ajustamos el video al tamaño de nuestra ventana
        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);
        
        //cuando termine el video mostrar el menu :)
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
               MenuP menuP = new MenuP();
               try {menuP.start(primaryStage);} catch (Exception e) {e.printStackTrace();}
            }
        });
        
        //lo reproducimos
        player.play();
        playerSound.play();
    }

}