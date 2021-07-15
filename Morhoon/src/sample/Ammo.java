package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ammo extends ImageView {
    private Image[] sprites;
    double x, y, maxWidth, maxHeight, rychlost;
    int actImage = 0;
    int stav = 0;

    public Ammo(String nazovSpritu, int pocetSpritov, double w, double h, double maxw, double maxh) { //konštruktor ammo-boxov
        super();
        maxWidth = maxw; maxHeight = maxh;
        sprites = new Image[pocetSpritov];
        for(int i = 0; i < pocetSpritov; i++) {
            sprites[i] = new Image(nazovSpritu+i+".png", w, h, false, false);
        }

        do {
            rychlost = (int)(-5 + Math.random() * 11) * 30;
        } while (rychlost == 0);

        if (rychlost<0)
            setLayoutX(maxWidth);
        else
            setLayoutX(1);

        setLayoutY(50 + (int) (Math.random()* maxHeight-10));

        setOnMousePressed(evt->onClick());
    }

    public double getStav() {
        return stav;
    }

    private void nextImage(){
        actImage = 0;
    }

    private void vykresli() {
        nextImage();

        if (stav == 0) setImage(sprites[actImage]);  //boxy majú len 2 stavy
        if (stav == 1) setImage(null);
    }

    private void onClick() {
        if(Game.naboje>0 && Game.naboje<5){
            stav = 1;
           Game.naboje = Game.naboje +2;
        }
        if(Game.naboje==5){
            stav = 1;
            Game.naboje = Game.naboje +2;
        }
        if(Game.naboje==6){
            stav = 1;
            Game.naboje = Game.naboje +1;
        }
    }
    public void Zmena(double deltaTime) {    //rotácia a pohyb boxov
        if (stav == 0){
            setLayoutX(getLayoutX()+rychlost*deltaTime);
            setRotate(getRotate()-1);
        }


        if ((getLayoutX()<0) || (getLayoutX()>maxWidth) || (getLayoutY()<0) || (getLayoutY()>maxHeight)) stav = 1; //premazávanie ak zájdu za okraj
        vykresli();
    }

}
