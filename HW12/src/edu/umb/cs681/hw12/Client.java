package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

public class Client implements Runnable{

    private Address initAddress = new Address("AAA Street", "M City", "X State", 12345);

    Customer customer = new Customer(initAddress);
    public void run() {
        System.out.println("Get address:");
        customer.getAddress();
        System.out.println("Set address1 to address2:");
        customer.setAddress(new Address("BBB Street", "N City", "Y State", 67890));
        System.out.println("Change address: ");
        customer.setAddress(customer.getAddress()
                                    .change("XXX Street", "XXX City", "XXX State", 11111));
    }
    public static void main(String[] args) {


        Thread t1 = new Thread(new Client());
        Thread t2 = new Thread(new  Client());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
