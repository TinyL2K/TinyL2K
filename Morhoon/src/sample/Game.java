package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.*;
import java.util.LinkedList;

public class Game extends Group {
    final int SPRITESIZE;
    final int MAXDUCK;
    final int MAXCREW;
    final int MAXAMMO;
    Timeline t;
    Timeline c;
    static int highscore = 0;
    private ImageView background;
    private LinkedList<Sliepka> zoznam;
    private LinkedList<Crew> zoz;
    private LinkedList<Ammo> z;
    private double maxWidth, maxHeight;
    static int score;
    static Text skore;
    static int cas;
    static Text time;
    static int naboje;
    public Game(int w, int h, String pozadie) {
        maxWidth = w; maxHeight = h;
        Image bg = new Image(pozadie, w, h, false, false);
        background = new ImageView(bg);
        AudioClip audioClip = new AudioClip(getClass().getResource("/ambience.wav").toString()); //pesnička, ktorá hrá v pozadí
        audioClip.play(2);
        SPRITESIZE = 40;
        MAXDUCK = 30;
        MAXCREW = 10;
        MAXAMMO = 4;
        score = 0;
        cas = 20;
        naboje = 6;
        skore = new Text("Score: "+score);
        skore.setFill(Color.WHITE);
        skore.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        skore.setX(280);
        skore.setY(30);
        time = new Text("Time left: "+cas);
        time.setFill(Color.WHITE);
        time.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        time.setX(100);
        time.setY(30);
        getChildren().add(background);
        getChildren().add(skore);
        getChildren().add(time);
        setCursor(Cursor.CROSSHAIR);
        zoznam = new LinkedList<>();
        zoz = new LinkedList<>();
        z = new LinkedList<>();
        setOnMousePressed(evt->onClick());
        t = new Timeline(new KeyFrame(Duration.millis(3000), e->nabijaj()));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
        c = new Timeline(new KeyFrame(Duration.millis(1000), e->cas()));
        c.setCycleCount(Animation.INDEFINITE);
        c.play();

    }


    public void update(double deltaTime) { //updater
        energyshower();
        VyrobSliepku();
        VyrobCrewmatea();
        VyrobAmmo();
        UrobPohyb(deltaTime/1000000000);
        Pohyb(deltaTime/1000000000);
        PAmmo(deltaTime/1000000000);
        PremazSliepky();
        PremazCrewmatea();
        PremazAmmo();
    }
    private void onClick() { //reakcia na klikanie - uberanie nábojov a vypustenie zvuku
        if(naboje > 0){
            AudioClip audioClip = new AudioClip(getClass().getResource("/space.wav").toString());
            audioClip.play(50);
            naboje--;
      }

    }
    public void HighScore(){ //zapisovanie najvyššieho dosiahnutého skóra

        try {
            BufferedReader reader = new BufferedReader(new FileReader("skora.txt"));
            String riadok = reader.readLine();
            while (riadok != null)
            {
                try {
                    highscore = Integer.parseInt(riadok.trim());
                    if (score > highscore)
                    {
                        highscore = score;
                    }
                } catch (NumberFormatException e1) {
                }
                riadok = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            System.err.println("Nastal problém s načítavaním skóra");
        }
        try {
            BufferedWriter vypis = new BufferedWriter(new FileWriter("skora.txt", true));
            vypis.newLine();
            vypis.append("" + highscore);
            vypis.close();

        } catch (IOException ex1) {
            System.out.printf("Nepodarilo sa zapísať skóre", ex1);
        }
    }

public void nabijaj(){ //ľahký reloader, ktorý nabíja jednu energiu každé 3 sekundy
        if(naboje < 6){
            naboje++;
        }
}

    private void energyshower() { //zobrazovač energie
        if(cas>0){
            EnergyBar eng = new EnergyBar("energy", 8,50,150, maxWidth, maxHeight);
            getChildren().add(eng);
                }
        }

    private void VyrobSliepku() { //nepremenovaná metóda na tvorbu asteroidu
        if(cas>0){
            if (zoznam.size() < MAXDUCK)
                if (Math.random() < 0.3) {
                    int ferrandomizer = (int)(Math.random() * 2); //vyberá náhodne jeden z 3 obrázkov pre asteroidy
                    Sliepka moja = new Sliepka("asteroid"+ferrandomizer, 1,SPRITESIZE,SPRITESIZE, maxWidth, maxHeight);
                    zoznam.add(moja);
                    getChildren().add(moja);
                }
        }
    }
    private void VyrobCrewmatea() { //metóda na vytváranie crewmateov
        if(cas>0){
            if (zoz.size() < MAXCREW)
                if (Math.random() < 0.3) {
                    int ferrandomizer = (int)(Math.random() * 14); //vyberá náhodne jeden zo 14 obrázkov pre kozmonautov
                    Crew moj = new Crew("crew"+ferrandomizer, 1,30,30, maxWidth, maxHeight);
                    zoz.add(moj);
                    getChildren().add(moj);
                }
        }

    }
    private void VyrobAmmo() {//metóda na vytváranie ammoboxov
        if(cas>0){
            if (z.size() < MAXAMMO)
                if (Math.random() < 0.3) {
                    Ammo moje = new Ammo("ammobox", 1,30,30, maxWidth, maxHeight);
                    z.add(moje);
                    getChildren().add(moje);
                }
        }

    }
    public static void zvys(){ //pripočítavanie skóra
        score=score+10;
        skore.setText("Score: "+score);
    }
    public static void zniz(){//odčítavanie skóra
        score=score-10;
        skore.setText("Score: "+score);
    }
    private void UrobPohyb(double delta) { //pohyby pre asteroidy, ammoboxy a crewmateov
        for(int i = 0;i < zoznam.size(); i++){
            Sliepka prvok = (zoznam.get(i));
            prvok.Zmena(delta);
        }
    }
    private void Pohyb(double delta) {
        for(int i = 0;i < zoz.size(); i++){
            Crew prvok = (zoz.get(i));
            prvok.Zmena(delta);
        }
    }
    private void PAmmo(double delta) {
        for(int i = 0;i < z.size(); i++){
            Ammo prvok = (z.get(i));
            prvok.Zmena(delta);
        }
    }

    private void PremazSliepky() { //premazávanie pre asteroidy, ammoboxy a crewmateov
        for(int i = 0;i < zoznam.size(); i++){
            Sliepka prvok = zoznam.get(i);
            if (prvok.getStav() == 2) {
                zoznam.remove(i);
                getChildren().remove(prvok);
            }
        }
    }
    private void PremazCrewmatea() {
        for(int i = 0;i < zoz.size(); i++){
            Crew prvok = zoz.get(i);
            if (prvok.getStav() == 2) {
                zoz.remove(i);
                getChildren().remove(prvok);
            }
        }
    }
    private void PremazAmmo() {
        for(int i = 0;i < z.size(); i++){
            Ammo prvok = z.get(i);
            if (prvok.getStav() == 1) {
                z.remove(i);
                getChildren().remove(prvok);
            }
        }
    }
public void cas(){ //stopky na 80 sekúnd
        cas--;
        time.setText("Time left: "+cas);
        if(cas<0){ //ak čas skončil stopne sa časovač a zobrazí dosiahnuté skóre + zapíše sa aj navjvyššie skóre
            t.stop();
            c.stop();
            HighScore();
            getChildren().clear();
            HighScore h = new HighScore(1280, 720, "pozadie1.jpg");
            getChildren().add(h);
        }
        }
}
