package com.mygdx.game.utils;

import com.mygdx.game.Kunde;

public class Queue<T> {
    private ZyklListe<T> list = new ZyklListe<>();

    public boolean isEmpty(){
        return (list.getStart()==null);
    }

    public void enqueue(T e){
        list.add(new Listenelement<>(e));
    }

    public T dequeue(){
        Listenelement<T> e = list.getStart();
        if(e!=null){
            list.remove(e);
            return e.getData();
        }
        return null;
    }

    public Listenelement<T> getFirst(){
        return list.getStart();
    }
}
