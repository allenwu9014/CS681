package edu.umb.cs681.hw01;

import java.util.LinkedList;

public abstract class Observable {
   private LinkedList<Observer> observers;
   private boolean changed = false;
    public Observable() {
        observers = new LinkedList<Observer>();
    }
    public void addObserver(Observer o) {
          observers.add(o);
    }
    public void deleteObserver(Observer o) {

        observers.remove(o);
    }

    protected void setChanged() {
     changed = true;
    }
    protected void clearChanged() {
        changed = false;
    }
    public boolean hasChanged() {
        return changed;
    }

    public void notifyObservers(Object obj) {
       if (hasChanged()) {
           for (Observer obs: observers) {
               obs.update(this, obj);
           }
           clearChanged();
           System.out.println("Notify complete.");
       }
    }
}
