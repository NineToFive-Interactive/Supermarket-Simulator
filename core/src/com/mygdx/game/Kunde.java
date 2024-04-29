package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.utils.BaseActor;

import java.util.Random;
public class Kunde extends BaseActor {

    private final Random random;
    private int anzahlWaren;
    private Kasse targetKasse;
    float sleeper;


    public Kunde(float posX, float posY, Stage stage, Kasse targetKasse, float sleep) {
        super(posX, posY, stage);
        random = new Random();
        this.anzahlWaren = random.nextInt(1, 51);
        this.targetKasse = targetKasse;
        this.sleeper = sleep;

        String[] kunden = new String[3];
        kunden[0] = "Kunde_1.png";
        kunden[1] = "Kunde_2.png";
        kunden[2] = "Kunde_3.png";
        loadTexture(kunden[random.nextInt(0,3)]);
    }

    public boolean removeWare(){
        anzahlWaren = anzahlWaren - 25;
        return (anzahlWaren <= 0);
    }

    public int getAnzahlWaren() {
        return anzahlWaren;
    }

    public void act(float deltaTime) {
        super.act(deltaTime);

        Action go = Actions.sequence(
                Actions.delay(sleeper),
                Actions.moveTo(targetKasse.getX(), targetKasse.getY(),2f) );
        addAction(go);

    }
}
