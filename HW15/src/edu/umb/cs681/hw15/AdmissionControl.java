package edu.umb.cs681.hw15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionControl {
    private int currentVisitors = 0;
    private ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void enter() {
        lock.lock();
        while (currentVisitors >= 5) {
            try {
                System.out.println("Too many visitors. Please wait for a while!");
                condition.await();
            }
            catch (InterruptedException e) {
                return;
            }
        }
        currentVisitors++;
        lock.unlock();
    }

    public void exit() {
        lock.lock();
        currentVisitors--;
        condition.signalAll();
        lock.unlock();
    }

    public int countCurrentVisitors() {
        lock.lock();
        try {
            return currentVisitors;
        }
        finally {
            lock.unlock();
        }
    }
}
