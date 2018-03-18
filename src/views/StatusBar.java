package views;

import java.io.File;
import java.util.ArrayList;

import config.Pref;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StatusBar{
	//parametros de la ventana	
	int WITH = Pref.getWITH();
	int HEIGHT = Pref.getHEIGHT();
	Scene scene;
	Group root;
	
	String lvl="1";
	String character="ok";
	
	public StatusBar(String lvlExt, String charExt) {
		lvl = lvlExt;
		character = charExt;
	}
	
	 //parametros de la barra
	ArrayList<Text> text;
	
	public Group start(Stage primaryStage) throws Exception {
		Group group = new Group();
		
		//creamos el background de la bara de status
		File file = new File("src/img/statusBack.png");
		ImageView bg = new ImageView(new String(file.toURI().toString()));
		bg.setTranslateX(0);
		bg.setTranslateY(HEIGHT-100);
		group.getChildren().add(bg);
		
		//creamos los cuadros de status
		ArrayList<Rectangle> rect = new ArrayList<>();
		ArrayList<Double> pos = new ArrayList<>();
		int rectW = 80;
		int rectH = 40;
		int marginTop = HEIGHT-60;
		int marginLeft= 160;
		//margin = marginLeft + rectW + marginRect -> 20 + 80 + 10 -> 110
		rect.add(new Rectangle(20,marginTop,rectW,rectH));
		pos.add((double) marginLeft);
		rect.add(new Rectangle(110,marginTop,rectW,rectH));
		pos.add((double) marginLeft+90);
		rect.add(new Rectangle(200,marginTop,rectW,rectH));
		pos.add((double) marginLeft+180);
		rect.add(new Rectangle(290,marginTop,rectW,rectH));
		pos.add((double) marginLeft+270);

		// creamos el texto de los cuadros
		text = new ArrayList<>();
		text.add(new Text("00:00"));
		text.add(new Text(lvl));
		text.add(new Text("00000$"));
		text.add(new Text(character));
		
		
		for (int i = 0; i < rect.size(); i++) {
			final StackPane stack = new StackPane();
			rect.get(i).setArcHeight(15);
			rect.get(i).setArcWidth(15);
			rect.get(i).setOpacity(0.5);
			rect.get(i).setFill(Color.CORNFLOWERBLUE);
			rect.get(i).setStroke(Color.BLUE);
			text.get(i).setFont(Font.font ("Cheltenham-normal", 22));
			
	        stack.setLayoutX(pos.get(i));
	        stack.setLayoutY(marginTop);
	        stack.getChildren().addAll(rect.get(i), text.get(i));
	        
		    group.getChildren().add(stack);
		}
		
	    Scene scene = new Scene(group, WITH, HEIGHT);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    return group;
	}
	
	public void setTexts(String textString, int position) {
		text.get(position).setText(textString);
	}
}
