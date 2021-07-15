package sample;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HighScore extends Group {

    private double maxWidth, maxHeight;
    static Text nameofgame;
    static Text info1;
    static Text info2;
    private ImageView background;

    public HighScore(int w, int h, String pozadie) { //konštruktor pre text, pozadie a tlačítko po skončení hry
        getChildren().clear();
        maxWidth = w; maxHeight = h;
        Image bg = new Image(pozadie, w, h, false, false);
        background = new ImageView(bg);
        nameofgame = new Text("Among the Morhoons");
        nameofgame.setFill(Color.WHITE);
        nameofgame.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 60));
        nameofgame.setX(280);
        nameofgame.setY(150);
        Button b1 = new Button("Exit");
        b1.setStyle("-fx-border-color: #696969;-fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-border-width: 5px;-fx-background-color: #696969;");
        b1.setTranslateX(500);
        b1.setTranslateY(500);
        info1 = new Text("You have managed to get score of: "+Game.score+" points,"+" your HighScore is "+Game.highscore+" points");
        info1.setFill(Color.WHITE);
        info1.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        info1.setX(280);
        info1.setY(300);
        info2 = new Text("There are few other maps, if you would like to try");
        info2.setFill(Color.WHITE);
        info2.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        info2.setX(280);
        info2.setY(350);
        b1.setOnAction(evt -> {
        Platform.exit();
        });
        getChildren().add(background);
        getChildren().addAll(nameofgame,info1,info2,b1);
    }
}
