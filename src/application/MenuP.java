package application;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuP extends Application{
	// Buscamos la imagen y lo convertimos en URI
    private static ArrayList<File> fileImgs= new ArrayList<>();
    private static ArrayList<String> MEDIA_URL_IMG= new ArrayList<>();
	
    ImageView background;
    ImageView spike;
    ImageView jet;
    ImageView faye;
    ImageView francoise;
    
    //personaje seleccionado
    int COWBOY=0;
    public int getCOWBOY() {
		return COWBOY;
	}
    
  //parametros de la ventana
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Buscamos la imagen y lo convertimos en URI
	    fileImgs.add(new File("src/img/b0.png"));
	    fileImgs.add(new File("src/img/Select_spike.png"));
	    fileImgs.add(new File("src/img/Select_Faye.png"));
	    fileImgs.add(new File("src/img/Select_Jet.png"));
	    fileImgs.add(new File("src/img/Select_Francoise.png"));
	    for (int i = 0; i < fileImgs.size(); i++) {
		    MEDIA_URL_IMG.add(fileImgs.get(i).toURI().toString());
		}
        
        //Creamos una imageview y lo ajustamos a lo alto de la pantalla   
		background=new ImageView(MEDIA_URL_IMG.get(0));
		background.setFitHeight(778); // no se porque se come 10 puntos al realizar set resizable :(
		background.setPreserveRatio(true);
		   
		spike=createImage(MEDIA_URL_IMG.get(1),166,61,253,0);
		faye=createImage(MEDIA_URL_IMG.get(2),491,0,313,1);
		jet=createImage(MEDIA_URL_IMG.get(3),262,461,316,2);
		francoise=createImage(MEDIA_URL_IMG.get(4),553,461,253,3);
		
		//creamos la pantalla y definimos el tamaño	
        Group rootg = new Group(background,spike,faye,jet,francoise);
        Scene sceneg = new Scene(rootg, 1024, 768);
		
		//asignamos la escena a primaryStage
		primaryStage.setResizable(false);
        //primaryStage.setScene(scene);
        primaryStage.setScene(sceneg);
        primaryStage.show();
	}
	void selectItem(ImageView image,int selection) {
		//creamos un observable que oculte la imagen
		image.hoverProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					image.setOpacity(1);
				} else {
					image.setOpacity(0);
				}
			}
		});
		//creamos un onclick
		image.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				System.out.println("Seleccionaste a "+selection);
				COWBOY = selection;
			}
		});
	}
	
	//propiedades de la imagen
	ImageView createImage(String URL,double x,double y,double height,int selection) {
		ImageView img =new ImageView(URL);
		img.setX(x);
		img.setY(y);
		img.setFitHeight(height);
		img.setOpacity(0);
		img.setPreserveRatio(true);
		selectItem(img,selection);
		return img;
	}
}
