package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.utils.BaseActor;

import java.util.Random;
public class Kunde extends BaseActor {

    private final Random random;
    private int anzahlWaren;
    private Kasse targetKasse;
    private float sleeper;
    private boolean neu;


    public Kunde(float posX, float posY, Stage stage, Kasse targetKasse, float sleep, int position) {
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

        addAction(walkUpTo(position, sleep));
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
        float speed = random.nextFloat(1.5f,2.5f);
        addAction(Actions.moveBy(100, 0,speed));
    }

    public Action walkUpTo(int x, float sleep) {

        SequenceAction spawn = new SequenceAction();
                spawn.addAction(Actions.delay(sleep));
                spawn.addAction(Actions.moveBy(1850-x*100, 0,4.75f-sleep));

        return spawn;
    }

    public void goHome() {

        SequenceAction leave = new SequenceAction();
        leave.addAction(Actions.moveBy(180,0,1f));
        leave.addAction(Actions.after(Actions.rotateBy(-90f,1f)));
        leave.addAction(Actions.after(Actions.moveBy(0,-1200f,2.75f)));
        leave.addAction(Actions.after(Actions.removeActor()));

        addAction(leave);
    }

}
