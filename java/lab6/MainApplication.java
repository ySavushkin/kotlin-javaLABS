package com.example.lab6;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Скидає полотно в початковий стан і малює смайлик.
     */
    private void reset(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKBLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //малюємо смайлик
        drawSmile(gc);
    }

    private void drawSmile(GraphicsContext gc) {
        //тут малюється злий смайлик в окулярах з ірокезом

        gc.setFill(Color.YELLOW);
        gc.fillOval(150, 150, 200, 200);

        gc.setFill(Color.BLACK);
        gc.fillRect(230, 80, 40, 100);

        //очі
        gc.setFill(Color.BLACK);
        gc.fillOval(190, 200, 20, 20);
        gc.fillOval(290, 200, 20, 20);

        //окуляри
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeRect(180, 190, 40, 40);
        gc.strokeRect(280, 190, 40, 40);
        gc.strokeLine(220, 210, 280, 210);

        //рот
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.strokeArc(200, 270, 100, 50, 0, 180, ArcType.ROUND);

        gc.setFill(Color.RED);
        gc.fillArc(200, 270, 100, 50, 0, 180, ArcType.ROUND);
    }



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PaintApp");
        Group root = new Group();

        Canvas canvas = new Canvas(500, 500);
        reset(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            gc.setFill(Color.DARKBLUE);
            gc.fillRect(e.getX() - 1, e.getY() - 1, 10, 10);
        });

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getClickCount() > 2) { //на три кліка
                reset(canvas);
            }
        });

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
