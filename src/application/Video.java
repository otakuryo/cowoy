package application;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Video {

	//Buscamos el VIDEO y lo convertimos en URI
	private static File file = new File("src/video/video.mp4");
	private static String MEDIA_URL = file.toURI().toString();
	
	//Buscamos el AUDIO y lo convertimos en URI
	private static ArrayList<File> fileSounds= new ArrayList<>();
	private static ArrayList<String> MEDIA_URL_SOUNDS= new ArrayList<>();
	
	//parametros de la ventana
	StackPane root;
	Scene scene;
	
	//creamos el reprductor de musica
	static MediaPlayer playerSound;
	MediaView mediaViewSound;
	boolean showvid;
	    
    public Video(StackPane root,Scene scene,boolean showVideo) {
		this.root = root;
		this.scene = scene;
		showvid=showVideo;
		//si el video no se reproduce...
		if (!showVideo) {
			file = new File("src/video/videoB.mp4");
			MEDIA_URL = file.toURI().toString();
		}
		
	}

	public void start(Stage primaryStage){
		
		
    	//agregamos la musica
    	fileSounds.add(new File("src/sounds/sound.m4a"));
    	fileSounds.add(new File("src/sounds/catsMars.mp3"));
    	fileSounds.add(new File("src/sounds/sound.m4a"));
    	fileSounds.add(new File("src/sounds/catsMars.mp3"));
    	for (int i = 0; i < fileSounds.size(); i++) {
    		MEDIA_URL_SOUNDS.add(fileSounds.get(i).toURI().toString());
		}
    	
        //creamos el reproductor del video
        playerSound = new MediaPlayer(new Media(MEDIA_URL_SOUNDS.get(0)));
        mediaViewSound = new MediaView(playerSound);
        playerSound.setCycleCount(Animation.INDEFINITE);
        playerSound.cycleCountProperty();
        
        //creamos el reproductor del video
        MediaPlayer player = new MediaPlayer(new Media(MEDIA_URL));
        if (showvid) {
            player.setMute(true);
		}
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
                if (!showvid) {
                    playerSound.play();			
        		}
            	player.stop();
            	MenuP menuP = new MenuP();
            	try {menuP.start(primaryStage);} catch (Exception e) {e.printStackTrace();}
            }
        });
        
        //lo reproducimos
        player.play();
        if (showvid) {
            playerSound.play();			
		}
    }
    
    public static void setPlayerSound(int sound) {
        Video.playerSound.stop();
    	MediaPlayer playerSounds = new MediaPlayer(new Media(MEDIA_URL_SOUNDS.get(sound)));
		playerSound = playerSounds;
        playerSound.play();
	}

}