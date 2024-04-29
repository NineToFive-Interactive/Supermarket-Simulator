package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.utils.BaseActor;
import com.mygdx.game.utils.Queue;

import java.util.ArrayList;

public class Kasse extends BaseActor {

    private Queue<Kunde> queue;
    public ArrayList<Kunde> kunden;
    private int kundenAnzahl;

    public Kasse(float posX, float posY, Stage stage, boolean open) {
        super(posX, posY, stage);
        this.queue = new Queue<Kunde>();
        this.kunden = new ArrayList<>();
        this.kundenAnzahl = 0;


        if(open) loadTexture("Kasse.png");
        else loadTexture("GeschlosseneKasse.png");
    }

    public void addGraphicalKunde(Kunde k){
        kunden.add(k);
        kundenAnzahl++;
     }
    public void addLogicalKunde(Kunde k){
        queue.enqueue(k);
    }
    public void removeGraphicalKunde() {
        kunden.removeFirst();
        kundenAnzahl--;
        for (Kunde k : kunden) {
            if(!k.getNeu()) k.walkUp();
        }
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
}
