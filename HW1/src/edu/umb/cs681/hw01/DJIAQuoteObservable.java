package edu.umb.cs681.hw01;




public class DJIAQuoteObservable extends Observable{
    public void setQuote() {
        setChanged();
        System.out.println("DJIA setChanged.");
    }
}