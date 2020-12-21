package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable{
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;


    public void setDone()
    {
        lock.lock();
        try {
            done = true;

        } finally {
            lock.unlock();
        }

    }
    public void run() {
        Random random = new Random();
        int ranNum = random.nextInt(5);
        while(true) {
            lock.lock();
            try {
                if(done) {
                   break;
                }
            } finally {
                lock.unlock();
            }
           Path path = Paths.get("f" + ranNum % 5 + ".html");


            AccessCounter.getInstance().increment(path);
            AccessCounter.getInstance().getCount(path);
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " " + e);
                continue;
            }
        }

    }
    public static void main(String[] args) {

        Thread[] threads = new Thread[20];
        RequestHandler[] requestHandlers = new RequestHandler[20];


        for (int i = 0; i < 20; i++) {
            requestHandlers[i] = new RequestHandler();
            threads[i] = new Thread(requestHandlers[i]);
            threads[i].start();
        }


        for(int i = 0; i < 20; i++) {
            requestHandlers[i].setDone();
            threads[i].interrupt();
            System.out.println("Thread " + i + " interrupted.");
            try {
                threads[i].join();
            } catch (Exception err) {
                System.out.println(err);
            }
        }
    }
}
