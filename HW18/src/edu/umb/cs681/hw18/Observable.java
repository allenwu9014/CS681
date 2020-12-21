package edu.umb.cs681.hw18;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {

    private ConcurrentLinkedQueue<Observer> observers;

   //private LinkedList<Observer> observers;
   private AtomicBoolean changed;
    //private ReentrantLock lock = new ReentrantLock();

    public Observable() {
        observers = new ConcurrentLinkedQueue<Observer>();
        changed = new AtomicBoolean();
    }
    public void addObserver(Observer o) {

        observers.add(o);

    }
    public void deleteObserver(Observer o) {

        observers.remove(o);

    }

    protected void setChanged() {
     changed.getAndSet(true);

        changed.getAndSet(false);
    }
    public boolean hasChanged() {
        if (changed.get())
            return true;
        return false;
    }

    public void notifyObservers(Object obj) {


       if (hasChanged()) {
          observers.forEach((ob) -> {
              ob.update(this, obj);
          });
           System.out.println("Notify complete.");
       }
    }
}
