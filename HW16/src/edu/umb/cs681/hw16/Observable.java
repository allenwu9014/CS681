package edu.umb.cs681.hw16;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
   private LinkedList<Observer> observers;
   private AtomicBoolean changed = new AtomicBoolean(false); // changed
    private ReentrantLock lock = new ReentrantLock();

    public Observable() {
        observers = new LinkedList<Observer>();
    }
    public void addObserver(Observer o) {
        lock.lock();  // changed
        observers.add(o);
        lock.unlock();
    }
    public void deleteObserver(Observer o) {
        lock.lock();// changed
        observers.remove(o);
        lock.unlock();
    }

    protected void setChanged() {
     changed.getAndSet(true); // changed
    }
    protected void clearChanged() {

        changed.getAndSet(false); // changed
    }
    public boolean hasChanged() {
        if (changed.get()) // changed
            return true;
        return false;
    }

    public void notifyObservers(Object obj) {
        LinkedList<Observer> tempObservers = new LinkedList<>();// changed
        lock.lock();// changed
        tempObservers = new LinkedList<Observer>(observers);// changed
        lock.unlock();// changed
       if (hasChanged()) {
           for (Observer obs: tempObservers) {// changed
               obs.update(this, obj);
           }
           clearChanged();
           System.out.println("Notify complete.");
       }
    }
}
