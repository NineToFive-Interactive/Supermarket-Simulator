package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.mygdx.game.utils.BaseActor;

import java.util.Random;
public class Kunde extends BaseActor {

    private final Random random;
    private int anzahlWaren;
    private Kasse targetKasse;
    private float sleeper;
    private boolean neu;


    public Kunde(float posX, float posY, Stage stage, Kasse targetKasse, float sleep) {
        super(posX, posY, stage);
        random = new Random();
        this.anzahlWaren = random.nextInt(1, 51);
        this.targetKasse = targetKasse;
        this.sleeper = sleep;
        neu = true;

        String[] kunden = new String[3];
        kunden[0] = "Kunde_1.png";
        kunden[1] = "Kunde_2.png";
        kunden[2] = "Kunde_3.png";
        loadTexture(kunden[random.nextInt(0,3)]);

        Action spawn = Actions.sequence(
                Actions.delay(sleeper),
                Actions.moveTo(targetKasse.getX()-100, targetKasse.getY()+60,1.5f) );


        addAction(spawn);
        addAction(walkUpTo(1));
    }

    public boolean removeWare(){
        anzahlWaren = anzahlWaren - 5;
        return (anzahlWaren <= 0);
    }

    public int getAnzahlWaren() {
        return anzahlWaren;
    }

    public void act(float deltaTime) {
        super.act(deltaTime);
    }

    public boolean getNeu() {
        return neu;
    }

    public void notNeu() {
        neu = false;
    }

    public void walkUp() {
        addAction(Actions.moveBy(100, 0,4f));
    }

    public Action walkUpTo(int x) {
        return Actions.after(Actions.moveBy(1100-x*110, 0,2f));
    }
}
