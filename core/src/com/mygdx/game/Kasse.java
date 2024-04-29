package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.utils.BaseActor;
import com.mygdx.game.utils.Queue;

public class Kasse extends BaseActor {

    private Queue<Kunde> queue;
    public Kasse(float posX, float posY, Stage stage) {
        super(posX, posY, stage);
        this.queue = new Queue<Kunde>();

        loadTexture("Kasse.png");
    }

    public void addKunde(Kunde k){
        queue.enqueue(k);
    }
    public void removeKunde(){
        queue.dequeue();
    }

    public Kunde getFirstKunde(){
        return (queue.getFirst()!=null) ? queue.getFirst().getData() : null;
    }

}