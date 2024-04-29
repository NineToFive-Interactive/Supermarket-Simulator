package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.utils.BaseActor;
import com.mygdx.game.utils.Queue;

public class Kasse extends BaseActor {

    private Queue<Kunde> queue;
    private int kundenAnzahl;

    public Kasse(float posX, float posY, Stage stage, boolean open) {
        super(posX, posY, stage);
        this.queue = new Queue<Kunde>();
        this.kundenAnzahl = 0;

        if(open) loadTexture("Kasse.png");
        else loadTexture("GeschlosseneKasse.png");
    }

    public void addKunde(Kunde k){
        queue.enqueue(k);
        kundenAnzahl++;
    }
    public void removeKunde(){
        queue.dequeue();
        kundenAnzahl--;
    }
    public Kunde getFirstKunde(){
        return (queue.getFirst()!=null) ? queue.getFirst().getData() : null;
    }

}
