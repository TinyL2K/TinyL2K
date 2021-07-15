package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class Crew extends ImageView {
    private Image[] sprites;
    private Image zabity;
    double x, y, maxWidth, maxHeight, rychlost;
    int actImage = 0;
    int stav = 0;
    int silvo = 0;

    public Crew(String nazovSpritu, int pocetSpritov, double w, double h, double maxw, double maxh) { //konštruktor crewmateov
        super();
        maxWidth = maxw; maxHeight = maxh;
        sprites = new Image[pocetSpritov];
        for(int i = 0; i < pocetSpritov; i++) {
            sprites[i] = new Image(nazovSpritu+i+".png", w, h, false, false);
        }
        zabity = new Image("explosion.png", w, h, false, false);
        setImage(sprites[0]);

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
        if (stav == 0) setImage(sprites[actImage]);
        if (stav == 1) setImage(zabity);
    }

    private void onClick() {  //rekacia na kliknutie
        if(Game.naboje>0){ //podmienka kedy sa dá strieľať
            stav = 1;
            Game.zniz();
            AudioClip audioClip = new AudioClip(getClass().getResource("/crewmatedeath.wav").toString()); //zvuk, ktorý sa vydá pri zasiahnutí
            audioClip.play(50);
        }
    }
    public void Zmena(double deltaTime) { //pohyb
        if (stav == 0){
            setLayoutX(getLayoutX()+rychlost*deltaTime);
            setRotate(getRotate()-1);
        }

        if (stav == 1){ //lenivo vyriešený časovač na zmenu spritov z výbuchu po "smrť"
            setLayoutX(getLayoutX()+rychlost*deltaTime);
            silvo++;
            if (silvo == 10){
                stav = 2;
            }
        }

        if ((getLayoutX()<0) || (getLayoutX()>maxWidth) || (getLayoutY()<0) || (getLayoutY()>maxHeight)) stav = 2;
        vykresli();
    }

}
