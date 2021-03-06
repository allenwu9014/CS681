package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class DepositRunnable implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
    private ThreadSafeBankAccount2 bankAccount = null;
    private boolean done = false;


    public DepositRunnable(ThreadSafeBankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
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
            lock.lock();
            try{
                if (done) {
                   break;
                }
            } finally {
                lock.unlock();
            }
            bankAccount.deposit(300);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exception) {

                continue;
            }
        }
    }

}