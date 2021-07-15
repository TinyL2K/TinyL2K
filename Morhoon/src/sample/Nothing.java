package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Nothing extends Group {

    private double maxWidth, maxHeight;
    static Text nameofgame;
    private ImageView background;

    public Nothing(int w, int h, String pozadie) { //kretívne pomenovaná classa, ktorá je v podstate len pozadie a meno
        maxWidth = w; maxHeight = h;
        Image bg = new Image(pozadie, w, h, false, false);
        background = new ImageView(bg);
        nameofgame = new Text("Among the Morhoons");
        nameofgame.setFill(Color.WHITE);
        nameofgame.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 60));
        nameofgame.setX(250);
        nameofgame.setY(150);
        getChildren().add(background);
        getChildren().add(nameofgame);
    }
}
