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
import com.mygdx.game.utils.BaseActor;
import com.mygdx.game.utils.BaseGame;
import com.mygdx.game.utils.BaseScreen;


import java.util.Random;

public class SupermarketScreen extends BaseScreen {

    Random random;
    private int offeneKassen;
    private Kasse[] kassen;
    private int naechsteKasse;
    private boolean started;
    float spawnTimer;
    Label label;
    String labelText;


    @Override
    public void initialize() {
        BaseActor background = new BaseActor(0,0, mainStage);
        background.loadTexture("Background.png");
        BaseActor.setWorldBounds(background);

        random = new Random();
        naechsteKasse = 0;
        offeneKassen = random.nextInt(1,5);
        kassen = new Kasse[offeneKassen];
        started = false;
        //spawnTimer = 5;
        spawnTimer = 0;
        for(int i = 0; i<offeneKassen; i++){
            kassen[i] = new Kasse( 500, 200 * i + 20, mainStage);
        }

        label = new Label("Anzahl Waren: 0", BaseGame.labelStyle);
        uiStage.addActor(label);
    }


    @Override
    public void update(float deltaTime) {

        spawnTimer -= deltaTime;

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            started = !started;
        }
        label.setText("Anzahl Waren: " + " " + deltaTime);

        //TODO: Kundenspawn

        if(started){
            int anzahlNeuerKunden = random.nextInt(1,4);
            //int anzahlNeuerKunden = 1;

            if(spawnTimer <=0) {
                for (int i = 0; i < anzahlNeuerKunden; i++){
                    kassen[naechsteKasse].addKunde(new Kunde(30, 510, mainStage, kassen[naechsteKasse],(float)i+1));
                    setNaechsteKasse();
                }

                for(int i = 0; i < offeneKassen; i++){
                    Kasse kasse = kassen[i];
                    Kunde kunde = kasse.getFirstKunde();
                    if (kunde != null) {
                        if(kunde.removeWare()){
                            kasse.removeKunde();
                            kunde.addAction(Actions.after(Actions.removeActor()));
                        }
                    }
                }

                spawnTimer = 5;

            }
        }

        //TODO: Abarbeitung an Kassen






    }

    public int getOffeneKassen(){
        return offeneKassen;
    }
    public void setNaechsteKasse(){
        naechsteKasse = (naechsteKasse+1) % offeneKassen;
    }
}
