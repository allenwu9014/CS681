package edu.umb.cs681.hw11;

import java.util.concurrent.atomic.AtomicReference;

public class RunnableConcurrentSingleton implements Runnable {


    public void run() {
        AtomicReference<ConcurrentSingleton> currentInstance = ConcurrentSingleton.getInstance();
        System.out.println("currnent instance: " + currentInstance);
    }


    public static void main(String[] args) {

        RunnableConcurrentSingleton cs1 = new RunnableConcurrentSingleton();
        RunnableConcurrentSingleton cs2 = new RunnableConcurrentSingleton();
        RunnableConcurrentSingleton cs3 = new RunnableConcurrentSingleton();

        Thread  thread1 = new Thread(cs1);
        Thread  thread2 = new Thread(cs2);
        Thread  thread3 = new Thread(cs3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
