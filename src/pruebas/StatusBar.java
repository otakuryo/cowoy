package pruebas;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
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

public class StatusBar  extends Application {
	//parametros de la ventana
	int WITH = 1024;
	int HEIGHT = 768;
	Scene scene;
	Group root;
	ImageView background;
	
	//parametros de la barra
	ArrayList<Text> text;
	
	//reloj
    private AnimationTimer timer;
    int frameCount=0;
    int sec=0;
    int min=0;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group group = new Group();
		
		//creamos los cuadros de status
		ArrayList<Rectangle> rect = new ArrayList<>();
		ArrayList<Double> pos = new ArrayList<>();
		int rectW = 80;
		int rectH = 40;
		int marginTop = 20;
		//margin = marginLeft + rectW + marginRect -> 20 + 80 + 10 -> 110
		rect.add(new Rectangle(20,marginTop,rectW,rectH));
		pos.add((double) 20);
		rect.add(new Rectangle(110,marginTop,rectW,rectH));
		pos.add((double) 110);
		rect.add(new Rectangle(200,marginTop,rectW,rectH));
		pos.add((double) 200);
		rect.add(new Rectangle(290,marginTop,rectW,rectH));
		pos.add((double) 290);

		// creamos el texto de los cuadros
		text = new ArrayList<>();
		text.add(new Text("00:00"));
		text.add(new Text("1-10"));
		text.add(new Text("00000$"));
		text.add(new Text("Spike"));
		
		
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
		
		//reloj
	    timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
            	frameCount++;
            	if(frameCount%60==0) {
            		sec++;
            		if (frameCount%3600==0) {
            			sec=0;
						min++;
					}
            		setTexts(String.format("%02d:%02d",min, sec),0);
            	}
        		setTexts(String.format("%05d$",frameCount/10),2);
            }
        };
        timer.start();
	    Scene scene = new Scene(group, WITH, HEIGHT);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	public static void main(String[] args) {
        launch(args);
    }
	public void setTexts(String textString, int position) {
		text.get(position).setText(textString);
	}
}
