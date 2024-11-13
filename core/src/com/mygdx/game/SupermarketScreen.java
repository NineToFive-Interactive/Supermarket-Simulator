package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    Label DebugLabel;
    Label timeLabel;
    Label statusLabel;
    long startTime;
    long elapsedTime;
    float roundTimer;
    private int counter;


    @Override
    public void initialize() {

        random = new Random();

        startTime = TimeUtils.millis();
        started = false;
        roundTimer = 1;

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

        DebugLabel = new Label("Anzahl Waren: 0", BaseGame.labelStyle);

        timeLabel = new Label("Laufzeit (in s): 0", BaseGame.labelStyle);
        timeLabel.setPosition(1,40);

        statusLabel = new Label("Status: Not Running!", BaseGame.labelStyle);
        statusLabel.setPosition(1,940);

        uiStage.addActor(DebugLabel);
        uiStage.addActor(timeLabel);
        uiStage.addActor(statusLabel);
    }


    @Override
    public void update(float deltaTime) {
        counter++;
        elapsedTime = TimeUtils.timeSinceMillis(startTime);
        roundTimer -= deltaTime;

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            started = !started;
        }

        DebugLabel.setText("Anzahl Updates: " + " " + counter);
        timeLabel.setText("Laufzeit (in s): " + " " + (int) elapsedTime/1000);

        String status;
        if(started) status = "Running!";
        else status = "Not Running!";;
        statusLabel.setText("Status: " + status);



        if(started){
            int anzahlNeuerKunden = random.nextInt(1,4);
            //int anzahlNeuerKunden = 1;

            if(roundTimer <=0) {

                //Abarbeitung an Kassen (logisch)
                for(int i = 0; i < offeneKassen; i++){
                    Kasse kasse = kassen[i];
                    Kunde kunde = kasse.getFirstKunde();
                    if (kunde != null && !kunde.getNeu()) {
                        if(kunde.removeWare()){
                            kasse.removeLogicalKunde();
                            kasse.removeGraphicalKunde();

                            kunde.goHome();
                            kasse.allWalkUp();
                        }
                    }
                }

                //Abarbeitung an Kassen (grafisch)
                for(int i = 0; i < offeneKassen; i++){
                    Kasse kasse = kassen[i];
                    for(Kunde k : kasse.kunden){
                        if(k.getNeu()) {
                            k.notNeu();
                            kasse.addLogicalKunde(k);
                        }
                    }
                }

                for (int i = 0; i < anzahlNeuerKunden; i++) {
                    kassen[naechsteKasse].addGraphicalKunde(new Kunde(-100, kassen[naechsteKasse].getY()+60, mainStage, kassen[naechsteKasse],(float)i,kassen[naechsteKasse].lastPosition()));
                    setNaechsteKasse();
                }

                roundTimer = 5;
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
