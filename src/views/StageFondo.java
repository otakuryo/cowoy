package views;

import java.io.File;

import config.Pref;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StageFondo{
	//parametros de la ventana
		int WITH = Pref.getWITH();
		int HEIGHT = Pref.getHEIGHT();
		File file;
		String uri;
		private int posb0=0,posb1=2048; //interesante :o
		private int vel=3;
		ImageView background, background1;
		private int nave;
	public StageFondo(int nave) {
		this.nave=nave;
	}
	
	public Group start(Stage primaryStage) throws Exception {
		//obtenemos el uri de el file, y lo ponemos en pantalla
		if (nave==3) {
			file = new File("src/img/backgroundb.png");
		}else {
			file = new File("src/img/background.png");
		}
		uri = file.toURI().toString();
		background=new ImageView(uri);
		background1=new ImageView(uri);
		
		background.setX(0);
		Group root = new Group(background,background1);
		return root;
	}
	
	//este es un metodo que actualiza el fondo al compaz de los fps
	public void moveBackground() {
    	posb0-=vel;
    	posb1-=vel;
    	background.setTranslateX(posb0);
    	background1.setTranslateX(posb1);
    	//System.out.println(background.getTranslateX()+" -> "+posb0 + " - "+background1.getTranslateX()+" -> "+posb1);
    	if (background.getTranslateX() < -2036) {posb0=2046;}
    	if (background1.getTranslateX() < -2036) {posb1=2046;}
	}

}
