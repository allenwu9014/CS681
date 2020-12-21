package edu.umb.cs681.hw14;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 {
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();
    public ThreadSafeBankAccount2() {
    }
    public void deposit(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() +
                    " (d): current balance: " + balance);
            while(balance >= 300){
                System.out.println(Thread.currentThread().getId() +
                        " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }
            balance += amount;
            System.out.println(Thread.currentThread().getId() +
                    " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public void withdraw(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() +
                    " (w): current balance: " + balance);
            while(balance <= 0){
                System.out.println(Thread.currentThread().getId() +
                        " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getId() +
                    " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public static void main(String[] args){
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        DepositRunnable deposit = new DepositRunnable(bankAccount);
        WithdrawRunnable withdraw = new WithdrawRunnable(bankAccount);
        Thread[] threads = new Thread[5];
        Thread[] withdrawThreads = new Thread[5];
        for(int i = 0; i < 5; i++){
           threads[i] = new Thread( deposit );
           threads[i].start();
            withdrawThreads[i] = new Thread( withdraw );
            withdrawThreads[i].start();
        }

        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        deposit.setDone();
        withdraw.setDone();
        for (int i = 0; i < 5; i++) {
            threads[i].interrupt();
             try {
                threads[i].join();
                 withdrawThreads[i].interrupt();
            } catch (Exception exception) {

            }
        }
    }
}
