package scorePackUDP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.Pref;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import scorePackUDP.ScorePlayer;

//Cuidado con iniciar primero esto... necesita el servidor o se cuelga el proceso :(
public class ServerScore extends Application{
	Group group;
	Scene scene;
	int WITH = 400;
	int HEIGHT = Pref.getHEIGHT();
	Rectangle background,b1;
	ArrayList<Text> textScore = new ArrayList<>();
	ArrayList<ScorePlayer> scoreStr = new ArrayList<>();
	private AnimationTimer timer;
	ClienteUDP cUDP = new ClienteUDP();
	
	//Se configura el puerto e ip de donde recogera los datos, por defecto lo dejamos asi :)
    static int puertoServidor = 6789;
	static String ip ="127.0.0.1";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//iniciamos un temporizador, igual al de todas las clases (preferencias personales)
		timer();
		
		//se crea los grupos y el escenario
		group = new Group();
		scene = new Scene(group,WITH,HEIGHT);
		
		//añadimos los objetos al grupo
		background = new Rectangle(WITH,HEIGHT,Color.BLACK);
		group.getChildren().add(background);
		group.getChildren().add(addText1());
		
		//creamos los puestos de las primeras 10 personas
		for (int i = 1; i < 11; i++) {
			textScore.add(addFieldScore("AAA", 9999,i*52));
			group.getChildren().add(textScore.get(i-1));
		}
		
		//se abre el telon ;)
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//este primer texto es el tutulo SCORE
	Text addText1() {
		Text text = new Text();
		text.setText("Score");
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(Color.GREY);
		text.setStyle("-fx-font: 62 arial;");
		text.setTranslateX(0);
		//con este parametro tomara el ancho maximo de la pantalla, para que se centre el texto correctamente :)
		text.setWrappingWidth(WITH); 
		text.setTranslateY((HEIGHT/2)-250);
		return text;
	}
	
	//este es la lista de datos :) (solo seran 10)
	Text addFieldScore(String name,int score,int sumPosY) {
		Text text = new Text();
		text.setText(String.format("%05d$ %-5s",score,name));
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font: 52 arial;");
		text.setTranslateX(0);
		//con este parametro tomara el ancho maximo de la pantalla, para que se centre el texto correctamente :)
		text.setWrappingWidth(WITH);
		text.setTranslateY((HEIGHT/2)-250+sumPosY);
		return text;
	}
	
	//con este metodo pediremos la informacion al servidor, pasaremos por la clase clienteUdp :)
	//que nos entregara los datos en un array
	void retriveData() throws ClassNotFoundException {
		System.out.println("now: "+scoreStr.size());
		scoreStr.addAll(cUDP.retriveData());
		orderData();
	}
	
	//ahora procederemos a ordenar los datos y posteriormente, a dejar solo los primero 10
	void orderData() {
		//creamos y guardamos una arraylist
		ArrayList<ScorePlayer> scoreTemp = new ArrayList<>();
		scoreTemp.addAll(scoreStr);
		scoreStr.clear(); //limpiamos el arraylist principal
		List<Integer> tempList = new ArrayList<>(); //creamos una lista donde ira el score
		
		//añadimos el score de cada usuario
		for (int i = 0; i < scoreTemp.size(); i++) {
			tempList.add(scoreTemp.get(i).getScore());
		}
		//ordenamos descendientemente 
		Collections.sort(tempList,Collections.reverseOrder());
		
		//lo añadimos al arraylist principal, al mismo tiempo lo mostramos en pantalla y eliminamos la de la
		//lista segundaria
		for(int i=0; i<tempList.size() && i<10;i++ )
        {
			for (int j = 0; j < scoreTemp.size(); j++) {
				if (tempList.get(i)==scoreTemp.get(j).getScore()) {
					scoreStr.add(scoreTemp.get(j));
					scoreTemp.remove(j);
					j--;
				}
			}
			textScore.get(i).setText(String.format("%05d$ %-5s",scoreStr.get(i).getScore(),scoreStr.get(i).getName().toUpperCase()));
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
					try {
						retriveData();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
	    };
	    timer.start();
	}
}
