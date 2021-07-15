package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class Sliepka extends ImageView {
    private Image[] sprites;
    private Image killed;
    int cas = 0;
    private Image exploded;
    double x, y, maxWidth, maxHeight, rychlost;
    int actImage = 0;
    int stav = 0;
    int silvo = 0;

    public Sliepka(String nazovSpritu, int pocetSpritov, double w, double h, double maxw, double maxh) { //nepremenovaná classa asteroidu
        super();
        maxWidth = maxw; maxHeight = maxh;
        sprites = new Image[pocetSpritov];
        for(int i = 0; i < pocetSpritov; i++) {
        sprites[i] = new Image(nazovSpritu+i+".png", w, h, false, false);
        }
        killed = new Image("explosion.png", w, h, false, false);
        setImage(sprites[0]);
        exploded = new Image("chunks.png", w, h, false, false);
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

    private void vykresli() { //nastavovanie obrázkov pre rôzne stavy
        nextImage();
        if (stav == 0) setImage(sprites[actImage]);
        if (stav == 1) setImage(killed);
        if (stav == 3) setImage(exploded);
        if (stav == 2) setImage(null);
    }

    private void onClick() { //reakcia na klikanie
        if(Game.naboje>0){
            stav = 1;
            Game.zvys();
            AudioClip audioClip = new AudioClip(getClass().getResource("/asteroid.wav").toString());
            audioClip.play(50);
        }
    }

    public void Zmena(double deltaTime) { //pohyb a zase lenivo vyriešená zmena obrázku z výbuchu po zbytky asteroidu
        if (stav == 0){
            setLayoutX(getLayoutX()+rychlost*deltaTime);
            setRotate(getRotate()+1);}
        if (stav == 1){
            setLayoutX(getLayoutX()+rychlost*deltaTime);
            silvo++;
            if (silvo == 10){
                stav = 3;}}
        if (stav == 3){
            setRotate(getRotate()+1);
            setLayoutY(getLayoutY()+3);
            setLayoutX(getLayoutX()+rychlost*deltaTime);}
        if ((getLayoutX()<0) || (getLayoutX()>maxWidth) || (getLayoutY()<0) || (getLayoutY()>maxHeight)) stav = 2;
        vykresli();
    }
}
