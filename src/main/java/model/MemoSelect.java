package model;
import model.Memento;


import java.util.ArrayDeque;
import java.util.Queue;

public class MemoSelect {
    public Queue<Memento> mementoList = new ArrayDeque();

    public MemoSelect() {
    }

    public void push(Memento state) {
        this.mementoList.add(state);
    }

    public Memento poll() {
        return (Memento)this.mementoList.poll();
    }
}