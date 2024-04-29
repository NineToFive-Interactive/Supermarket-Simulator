package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.utils.BaseActor;
import com.mygdx.game.utils.Queue;

import java.util.ArrayList;

public class Kasse extends BaseActor {

    private Queue<Kunde> queue;
    private ArrayList<Kunde> kunden;
    private int kundenAnzahl;

    public Kasse(float posX, float posY, Stage stage, boolean open) {
        super(posX, posY, stage);
        this.queue = new Queue<Kunde>();
        this.kunden = new ArrayList<>();
        this.kundenAnzahl = 0;


        if(open) loadTexture("Kasse.png");
        else loadTexture("GeschlosseneKasse.png");
    }

    public void addKunde(Kunde k){
        queue.enqueue(k);
        kunden.add(k);
        kundenAnzahl++;
    }
    public void removeKunde() {
        queue.dequeue();
        kunden.removeFirst();
        kundenAnzahl--;
        for (Kunde k : kunden) {
            k.walkUp();
        }
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
}
