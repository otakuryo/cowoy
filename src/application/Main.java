package application;

import config.Pref;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {
		
	//parametros de la ventana	
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();
	StackPane root;
	Scene scene;
	private GridPane grid;
	
	Boolean showVideo;
 
    @Override
    public void start(Stage primaryStage) throws Exception {
    	//creamos la pantalla
        root = new StackPane();

        //creamos el formulario
        setform(primaryStage);
        
        //instanciamos la clase video
        scene = new Scene(grid, WITH, HEIGHT);
        
        //asignamos la escena a primaryStage
        primaryStage.setTitle("Cowoy Bebop by Ryo :)");
		primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //se da forma al formulario para añadir los datos del usuario
    void setform(Stage primaryStage) {
    	grid = new GridPane();
		//añadimos a la pantalla 2 campos de texto y un boton
			TextField tfNom = new TextField ();
			tfNom.setPromptText("Nombre/Apodo (max 5)");

			TextField tfIp = new TextField ();
			tfIp.setPromptText("Ip del servidor..");
			
			CheckBox cb = new CheckBox();
			cb.setText("Mostrar intro/video");
			cb.setSelected(true);

			Button enviar = new Button("Empezar");
			
			//los introducimos en un gridpanel, y añadimos los objetos
			//como de un grupo tratase
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(5); //margenes verticales
			grid.setHgap(5); //margenes horizontales
			grid.setAlignment(Pos.CENTER);
			
			//objeto, posicion H, posicion V
			GridPane.setConstraints(tfNom, 0, 0);
			GridPane.setConstraints(tfIp, 0, 1);
			GridPane.setConstraints(cb, 0, 2);
			GridPane.setConstraints(enviar, 0, 3);
			GridPane.setHalignment(enviar, HPos.CENTER);
			grid.getChildren().addAll(tfNom, tfIp,cb,enviar);
			
			//añadirmos una funciona al boton, este comprobara que los datos no esten vacios :)
			enviar.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (!tfNom.getText().isEmpty() && !tfIp.getText().isEmpty()) {
						Pref.setUser(tfNom.getText());
						Pref.setIP(tfIp.getText());
						showVideo=cb.isSelected();
						scene = new Scene(root,WITH,HEIGHT);
				        Video video = new Video(root, scene, showVideo);
				        video.start(primaryStage);

						primaryStage.setScene(scene);
						primaryStage.show();
						
					}
				}
			});
    }
    public static void main(String[] args) {
        launch(args);
    }
}
