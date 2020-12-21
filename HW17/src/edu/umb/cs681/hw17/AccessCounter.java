package edu.umb.cs681.hw17;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    ConcurrentHashMap<Path, AtomicInteger> map = new ConcurrentHashMap <>();    //changed
  //  private ReentrantLock nStaticLock = new ReentrantLock();
    private static ReentrantLock staticLock = new ReentrantLock();
    private static AccessCounter instance = null;

    public static AccessCounter getInstance(){
        staticLock.lock();
        try {
            if(instance==null)
            {
                instance = new AccessCounter();
            }
        } finally {
            staticLock.unlock();
        }
        return instance;
    }

    public void increment(Path path){
       map.compute(path,(Path p, AtomicInteger i) -> {      //changed
           if (i != null) {
               System.out.println("Increase " + path + " to " + i.get()+1);
               return new AtomicInteger(i.incrementAndGet());
           } else {
               System.out.println("Add " + path );
               return new AtomicInteger(1);

           }
       });

    }
    public int getCount(Path path) {
        int tmpCount = 0;
        tmpCount = map.compute(path, (Path p, AtomicInteger i) -> {     //changed
            if (i != null) {
                System.out.println(path + " count is " + i.get());
                return i;
            } else {
                System.out.println(path + " count is " + 0);
                return new AtomicInteger(0);
            }

        }).get();
    return  tmpCount;
    }
}
