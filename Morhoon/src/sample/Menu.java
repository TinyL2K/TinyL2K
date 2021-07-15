package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Menu extends Group {

    private double maxWidth, maxHeight;
    static Text nameofgame;
    static Text info1;
    static Text info2;
    static Text info3;
    static Text info4;
    private ImageView background;

    public Menu(int w, int h, String pozadie) { //konštruktor pre classu, ku ktorej sa dostanete pomocou tlačidla "how to play"
        maxWidth = w; maxHeight = h;
        Image bg = new Image(pozadie, w, h, false, false);
        background = new ImageView(bg);
        nameofgame = new Text("Among the Morhoons");
        nameofgame.setFill(Color.WHITE);
        nameofgame.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 60));
        nameofgame.setX(250);
        nameofgame.setY(150);
        info1 = new Text("1. You have 80 seconds to shoot as many asteroids as possible");
        info1.setFill(Color.WHITE);
        info1.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        info1.setX(250);
        info1.setY(300);
        info2 = new Text("2. Asteroids grant 10 points but beware, shooting crewmates takes 10 points");
        info2.setFill(Color.WHITE);
        info2.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        info2.setX(250);
        info2.setY(350);
        info3 = new Text("3. If you shoot the boxes you will gain ammo, ammo is shown in the down-right corner");
        info3.setFill(Color.WHITE);
        info3.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        info3.setX(250);
        info3.setY(400);
        info4 = new Text("4. When you are ready to start, press the start button");
        info4.setFill(Color.WHITE);
        info4.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        info4.setX(250);
        info4.setY(450);
        getChildren().add(background);
        getChildren().addAll(nameofgame,info1,info2,info3,info4);
    }
}
