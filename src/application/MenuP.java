package application;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuP extends Application{
	// Buscamos la imagen y lo convertimos en URI
    private static File fileImg= new File("src/img/b0.png");
	private static final String MEDIA_URL_IMG = fileImg.toURI().toString();
    ImageView background;
    
  //parametros de la ventana
  	StackPane root;
  	Scene scene;
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		//creamos la pantalla
        root = new StackPane();

        //definimos el tamaño
        scene = new Scene(root, 1024, 768);
        
        //Creamos una imageview y lo ajustamos a lo alto de la pantalla     
		background=new ImageView(MEDIA_URL_IMG);
		background.setFitHeight(778); // no se porque se come 10 puntos al realizar set resizable :(
		background.setPreserveRatio(true);
		root.getChildren().add(background);
		
		//asignamos la escena a primaryStage
		primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
