package pruebas;

import javafx.animation.PathTransition;
import static javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
 
/**
 * A sample in which a node moves along a path from end to end over a given time.
 */
public class PathTransitionApp extends Application {
 
    private PathTransition transition;
 
    public Parent createContent() {
        final Pane root = new Pane();
        root.setPrefSize(280, 190);
        root.setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        root.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
 
        Rectangle rect = new Rectangle(0, 0, 40, 40);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setFill(Color.ORANGE);
        root.getChildren().add(rect);
        Path path = new Path(new MoveTo(20, 20),new LineTo(40,20));
        path.setStroke(Color.DODGERBLUE);
        path.getStrokeDashArray().setAll(5d, 5d);
        root.getChildren().add(path);
 
        transition = new PathTransition(Duration.seconds(1), path, rect);
        transition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(false);
 
        return root;
    }
 
    public void play() {
        transition.play();
    }
 
    @Override
    public void stop() {
        transition.stop();
    }
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        play();
    }
 
    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}