package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    Map<Path, Integer> map = new HashMap<Path, Integer>();
    private ReentrantLock nStaticLock = new ReentrantLock();
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
        nStaticLock.lock();
        try {
            if(map.containsKey(path)) {
                 map.put(path, map.get(path)+1);
                System.out.println("Increase " + path + " to " + map.get(path));
            } else {
                System.out.println("Add " + path );
                map.put(path, 1);
            }
        }finally {
            nStaticLock.unlock();
        }
    }
    public int getCount(Path path) {
        int tmpCount = 0;
        nStaticLock.lock();
        try {
            if(map.containsKey(path)) {
                tmpCount = map.get(path);
                System.out.println(path + " count is " + tmpCount);
            } else {
                System.out.println(path + " count is " + 0);
            }
        } finally {
            nStaticLock.unlock();
        }
        return tmpCount;
    }
}
