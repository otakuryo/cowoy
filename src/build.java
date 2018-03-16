
import application.Main;
import javafx.application.Application;
import javafx.stage.Stage;

public class build extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main main = new Main();
		main.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
