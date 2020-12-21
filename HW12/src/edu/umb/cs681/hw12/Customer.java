package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

public class Customer   {
    private Address address;
    ReentrantLock lock = new ReentrantLock();


    public Customer(Address addr) {address = addr;}

    public void setAddress (Address addr) {
        lock.lock();
        try {
            this.address = addr;
            System.out.println("Set: " + this.address.toString());
        } finally {
            lock.unlock();
        }

    }
    public Address getAddress() {
        Address tempAddress = null;
        lock.lock();
        try {
            tempAddress = this.address;
            System.out.println("Get: " + tempAddress.toString());
        } finally {
            lock.unlock();
        }
        return tempAddress;
    }



    }

