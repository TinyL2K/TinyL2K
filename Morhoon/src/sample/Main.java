package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{ //pár tlačítok a pozadie robenené s pomocou inej classy
        Group root = new Group();
        Button b1 = new Button("Start");
        Button b2 = new Button("How to play");
        Button b3 = new Button("Exit");
        Nothing n = new Nothing(1280, 720, "pozadie1.jpg");
        b1.setStyle("-fx-border-color: #696969;-fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-border-width: 5px;-fx-background-color: #696969;");
        b1.setTranslateX(20);
        b1.setTranslateY(300);
        b2.setStyle("-fx-border-color: #696969;-fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-border-width: 5px;-fx-background-color: #696969;");
        b2.setTranslateX(20);
        b2.setTranslateY(400);
        b3.setStyle("-fx-border-color: #696969;-fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-border-width: 5px;-fx-background-color: #696969;");
        b3.setTranslateX(20);
        b3.setTranslateY(500);
        root.getChildren().add(n);
        b3.setOnAction(evt -> {
            Platform.exit();
        });
        b2.setOnAction(evt -> {
            root.getChildren().clear();
            Menu m = new Menu(1280, 720, "pozadie1.jpg");
            root.getChildren().add(m);
            root.getChildren().addAll(b1,b2,b3);
        });
        b1.setOnAction(evt -> {
            b1.setVisible(false);
            int randy = (int) (1 + (Math.random() * 4));
            Game g = new Game(1280, 720, "pozadie"+randy+".jpg"); //hra, vyberá po spustení jednu zo 4 máp
            root.getChildren().add(g);
            MyTimer t = new MyTimer(g);
            t.start();
        });
        Scene scene = new Scene(root, 1280, 720);
        root.getChildren().addAll(b1,b2,b3);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
