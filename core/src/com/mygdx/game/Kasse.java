package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.utils.BaseActor;
import com.mygdx.game.utils.BaseGame;
import com.mygdx.game.utils.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Kasse extends BaseActor {

    private Queue<Kunde> queue;
    public ArrayList<Kunde> kunden;
    private int kundenAnzahl;
    private static int instanz = 1;
    public Label infoLabel;
    public Kasse(float posX, float posY, Stage stage, boolean open) {
        super(posX, posY, stage);
        this.queue = new Queue<Kunde>();
        this.kunden = new ArrayList<>();
        this.kundenAnzahl = 0;


        infoLabel = new Label(String.valueOf(instanz), BaseGame.labelStyle);
        infoLabel.setPosition(posX-100, posY+35);
        infoLabel.setVisible(false);
        stage.addActor(infoLabel);

        this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                infoLabel.setVisible(!infoLabel.isVisible());
            }
        });

        if(open) loadTexture("Kasse.png");
        else loadTexture("GeschlosseneKasse.png");
        instanz++;
    }

    public void addGraphicalKunde(Kunde k){
        kunden.add(k);
        kundenAnzahl++;
     }
    public void addLogicalKunde(Kunde k){
        queue.enqueue(k);
    }

    public void removeGraphicalKunde() {
        //TODO: Hier läuft manchmal was schief
        try {
            if (kunden.getFirst() != null) kunden.removeFirst();
            kundenAnzahl--;
        }catch (NoSuchElementException e){e.getStackTrace();}
    }

    public void removeLogicalKunde() {
        queue.dequeue();
    }

    public int getPositionOf(Kunde k){
        return kunden.indexOf(k)+1;
    }

    public Kunde getFirstKunde(){
        return (queue.getFirst()!=null) ? queue.getFirst().getData() : null;
    }

    public int getKundenAnzahl() {
        return kundenAnzahl;
    }

    public int lastPosition(){
        return kundenAnzahl+1;
    }
    public void allWalkUp(){
        for (Kunde k : kunden) {
            k.walkUp();
        }
    }

    public int getInstanz(){
        return instanz;
    }
    public <T> void setLabelText(T text){
        this.infoLabel.setText(text.toString());
    }
}
