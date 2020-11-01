package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        }finally {
            lock.unlock();
        }
    }


    public void generatePrimeFactors() {
        long divisor = 2;
        while( dividend != 1 && divisor <= to ){
            lock.lock();
            try {

                if(done) {
                    System.out.println("Stopped generating prime factors.");
                    this.factors.clear();
                    break;
                }
                if(dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                }else {
                    if(divisor==2){ divisor++; }
                    else{ divisor += 2; }

                }
            }finally {
                lock.unlock();
            }
            try {

                Thread.sleep(2000);
            } catch(InterruptedException e) {
                System.out.println("Interrupted.");
                System.out.println(e.toString());
                continue;
            }

        }
    }

    public static void main(String[] args) {

        RunnableCancellableInterruptiblePrimeFactorizer gen = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, 36);
        Thread thread = new Thread(gen);
        thread.start();
        try {
            System.out.println("sleep.");
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println(e.toString() + ".");
        }
       gen.setDone();

        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Result: " + gen.getPrimeFactors() + "\n");
    }
}
