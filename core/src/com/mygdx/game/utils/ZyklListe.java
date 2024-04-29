package com.mygdx.game.utils;

public class ZyklListe<T>{

    private Listenelement<T> start;
    private Listenelement<T> end;

    public Listenelement<T> getStart(){
        return start;
    }
    public void setStart(Listenelement<T> start){
        this.start = start;
    }
    public Listenelement<T> getEnd(){
        return end;
    }
    public void setEnd(Listenelement<T> end){
        this.end = end;
    }

    public void add(Listenelement<T> l) {
        if(start == null){
            setStart(l);
            setEnd(l);
            l.setSuccessor(l);
        }
        else {
            end.setSuccessor(l);
            setEnd(l);
            l.setSuccessor(start);
        }
    }
    public void remove(Listenelement<T> l) {
        Listenelement<T> predecessor = null;
        Listenelement<T> current = start;
        while(current != l){
            predecessor = current;
            current = current.getSuccessor();
        }
        if(predecessor == null){
            Listenelement<T> currSucc = current.getSuccessor();
            end.setSuccessor(currSucc);
            start = currSucc;
        }
        else{
            predecessor.setSuccessor(current.getSuccessor());
        }
    }
    public boolean find(Listenelement<T> l) {
        Listenelement<T> current = start;
        do {
            if (current.getSuccessor() == l) {
                return true;
            }
            current = current.getSuccessor();
        }while(current != start);
        return false;
    }

    public String toString(){
        String str = "";
        Listenelement<T> current = start;
        do{
            str = str + " " + current.toString();
            current = current.getSuccessor();
        }
        while(current != start);
        return str;
    }

    public void addBefore(Listenelement<T> start, Listenelement<T> tListenelement) {
    }
}
