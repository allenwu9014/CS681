package edu.umb.cs681.hw15;

import java.util.concurrent.locks.ReentrantLock;

public class ExitHandler implements Runnable {
    private AdmissionControl control;
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public ExitHandler(AdmissionControl control) {
            this.control = control;
        }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

        public void run() {
            while (true) {
                if(done) {
                    System.out.println("Stop exit.");
                    break;
                }
                control.exit();
            }
        }

    }