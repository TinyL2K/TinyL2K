package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnergyBar extends ImageView {
    private Image[] sprites;
    double x, y, maxWidth, maxHeight;
    int actImage = 0;
    int stav = 0;
    public EnergyBar(String nazovSpritu, int pocetSpritov, double w, double h, double maxw, double maxh) { //konštruktor pre zobrazovač energie (=nábojov)
        super();
        maxWidth = maxw; maxHeight = maxh;
        sprites = new Image[pocetSpritov];
        for(int i = 0; i < pocetSpritov; i++) {
            sprites[i] = new Image(nazovSpritu+i+".png", w, h, false, false);
        }
        setLayoutX(1220);
        setLayoutY(560);
        vykresli();
    }

    public double getStav() {
        return stav;
    }

    private void vykresli() { //"animácia" pre míňanie/získavanie nábojov

        if (Game.naboje == 0) setImage(sprites[actImage]);
        if (Game.naboje == 1) setImage(sprites[actImage+1]);
        if (Game.naboje == 2) setImage(sprites[actImage+2]);
        if (Game.naboje == 3) setImage(sprites[actImage+3]);
        if (Game.naboje == 4) setImage(sprites[actImage+4]);
        if (Game.naboje == 5) setImage(sprites[actImage+5]);
        if (Game.naboje == 6) setImage(sprites[actImage+6]);

    }


}