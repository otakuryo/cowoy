package application;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import scorePackUDP.ScorePlayer;
import scorePackUDP.ServidorUDP;

public class ServerScore extends Application{
	Group group;
	Scene scene;
	int WITH = 400;
	int HEIGHT = Pref.getHEIGHT();
	Rectangle background,b1;
	ArrayList<Text> textScore = new ArrayList<>();
	ArrayList<ScorePlayer> scoreStr = new ArrayList<>();
	private AnimationTimer timer;
	private ServidorUDP servidorUDP;
	
    static int puertoServidor = 6789;
	static String ip ="127.0.0.1";
	
	//realizar un hashmap<score, Text>
	//pasarlo a un list los keys
	//ordenar con un collections.list
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		timer();
		group = new Group();
		scene = new Scene(group,WITH,HEIGHT);

		background = new Rectangle(WITH,HEIGHT,Color.BLACK);
		group.getChildren().add(background);
		group.getChildren().add(addText1());
		//ServidorUDP.start();
				
		/*for (int i = 1; i < scoreStr.size(); i++) {
			textScore.add(addText2(scoreStr.get(i).getName(), scoreStr.get(i).getScore(),i*52));
			group.getChildren().add(textScore.get(i-1));
		}*/
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	Text addText1() {
		Text text = new Text();
		text.setText("Score");
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(Color.GREY);
		text.setStyle("-fx-font: 62 arial;");
		text.setTranslateX(0);
		text.setWrappingWidth(WITH);
		text.setTranslateY((HEIGHT/2)-250);
		return text;
	}
	Text addText2(String name,int score,int sumPosY) {
		Text text = new Text();
		text.setText(String.format("%05d$ %5s",score,name));
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font: 52 arial;");
		text.setTranslateX(0);
		text.setWrappingWidth(WITH);
		text.setTranslateY((HEIGHT/2)-250+sumPosY);
		return text;
	}
	void retriveData() {
		scoreStr.clear();
		scoreStr.addAll(ServidorUDP.getScoreStr());
		//scoreStr = ServidorUDP.getScoreStr();
		System.out.println(scoreStr.size());

		for (int i = 1; i < scoreStr.size(); i++) {
			textScore.add(addText2(scoreStr.get(i).getName(), scoreStr.get(i).getScore(),i*52));
			group.getChildren().add(textScore.get(i-1));
		}
	}

	private int fragment=0;
	void timer() {
		//reloj
	    timer = new AnimationTimer() {

			@Override
            public void handle(long l) {
				fragment++;
				if (fragment%300==0) {
					
					System.out.println("buscando...");
					retriveData();
				}
			}
	    };
	    timer.start();
	}
}
