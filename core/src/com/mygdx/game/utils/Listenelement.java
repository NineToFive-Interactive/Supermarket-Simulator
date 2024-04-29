package com.mygdx.game.utils;

public class Listenelement<T> {
    private T data;
    private Listenelement<T> successor;

    public Listenelement(T data) {
        this.data = data;
        this.successor = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Listenelement<T> getSuccessor() {
        return successor;
    }
    public void setSuccessor(Listenelement<T> next) {
        this.successor = next;
    }

    public String toString() {
        return data.toString();
    }
}
