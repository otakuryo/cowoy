package pruebas;

import java.io.File;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ImageInCircleExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        final Pane root = new Pane();
        final ImageView imageView = new ImageView(new String(new File("src/img/rock1.png").toURI().toString()));
        final Circle clip = new Circle(120, 120, 120);
        imageView.setClip(clip);
        clip.setStroke(Color.BLUE);
        root.getChildren().add(imageView);

        final Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    /*
      // enable dragging:
        final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>();
        imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseAnchor.set(new Point2D(event.getSceneX(), event.getSceneY()));
            }
        });
        imageView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double deltaX = event.getSceneX() - mouseAnchor.get().getX();
                double deltaY = event.getSceneY() - mouseAnchor.get().getY();
                imageView.setLayoutX(imageView.getLayoutX() + deltaX);
                imageView.setLayoutY(imageView.getLayoutY() + deltaY);
                clip.setCenterX(clip.getCenterX() - deltaX);
                clip.setCenterY(clip.getCenterY() - deltaY);
                mouseAnchor.set(new Point2D(event.getSceneX(), event.getSceneY()));
            }
        });
     */
}