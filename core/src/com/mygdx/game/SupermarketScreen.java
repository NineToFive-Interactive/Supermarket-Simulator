package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.utils.BaseActor;
import com.mygdx.game.utils.BaseGame;
import com.mygdx.game.utils.BaseScreen;


import java.util.Random;

public class SupermarketScreen extends BaseScreen {

    private Kasse[] kassen;
    private int offeneKassen;
    private int naechsteKasse;
    private boolean started;
    Random random;
    Label warenLabel;
    Label timeLabel;
    Label statusLabel;
    long startTime;
    long elapsedTime;
    float spawnTimer;


    @Override
    public void initialize() {

        random = new Random();

        startTime = TimeUtils.millis();
        started = false;
        spawnTimer = 1;

        //offeneKassen = random.nextInt(1,5);
        offeneKassen = 3;
        kassen = new Kasse[offeneKassen];
        naechsteKasse = 0;

        BaseActor background = new BaseActor(0,0, mainStage);
        background.loadTexture("Background.png");
        BaseActor.setWorldBounds(background);

        for(int i = 0; i<5; i++){
            if(i<offeneKassen) {
                kassen[i] = new Kasse( 750, 200 * i + 20, mainStage, true);
            } else {
                 new Kasse( 750, 200 * i + 20, mainStage, false);
            }
        }

        warenLabel = new Label("Anzahl Waren: 0", BaseGame.labelStyle);

        timeLabel = new Label("Laufzeit (in s): 0", BaseGame.labelStyle);
        timeLabel.setPosition(1,40);

        statusLabel = new Label("Status: Not Running!", BaseGame.labelStyle);
        statusLabel.setPosition(1,940);

        uiStage.addActor(warenLabel);
        uiStage.addActor(timeLabel);
        uiStage.addActor(statusLabel);
    }


    @Override
    public void update(float deltaTime) {

        elapsedTime = TimeUtils.timeSinceMillis(startTime);
        spawnTimer -= deltaTime;

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            started = !started;
        }

        warenLabel.setText("Anzahl Waren: " + " " + deltaTime);
        timeLabel.setText("Laufzeit (in s): " + " " + (int) elapsedTime/1000);

        String status;
        if(started) status = "Running!";
        else status = "Not Running!";;
        statusLabel.setText("Status: " + status);

        if(started){
            int anzahlNeuerKunden = random.nextInt(1,4);
            //int anzahlNeuerKunden = 1;

            if(spawnTimer <=0) {

                for (int i = 0; i < anzahlNeuerKunden; i++) {
                    kassen[naechsteKasse].addKunde(new Kunde(30, 510, mainStage, kassen[naechsteKasse],(float)i+1));
                    setNaechsteKasse();
                }

                spawnTimer = 5;
            }

            //TODO: Abarbeitung an Kassen

            for(int i = 0; i < offeneKassen; i++){
                    Kasse kasse = kassen[i];
                    Kunde kunde = kasse.getFirstKunde();
                    if (kunde != null && !kunde.getNeu()) {
                        if(kunde.removeWare()){
                            kasse.removeKunde();
                            kunde.addAction(Actions.after(Actions.removeActor()));
                        } else {
                            //TODO:Länge der Queue ermitteln für kunde.walkUp(); oder coole Lösung überlegen.
                        }
                    } else if (kunde != null && kunde.getNeu()) {
                        kunde.notNeu();
                        //TODO:Länge der Queue ermitteln für kunde.walkUp(); oder coole Lösung überlegen.
                    }
            }


        }
    }

    public int getOffeneKassen(){
        return offeneKassen;
    }
    public void setNaechsteKasse(){
        naechsteKasse = (naechsteKasse+1) % offeneKassen;
    }
}
