package edu.umb.cs681.hw01;

public class Client {
    public static void main(String[] args) {

       StockQuoteObservable stockquoteobservable = new StockQuoteObservable();

       stockquoteobservable.addObserver(
                (Observable o, Object obj)-> { System.out.print("StockObserver.");
                });

        stockquoteobservable.setQuote("A", 100f);
        stockquoteobservable.notifyObservers(stockquoteobservable);


        DJIAQuoteObservable DJIAquoteobservable = new DJIAQuoteObservable();

        DJIAquoteobservable.addObserver(
                (Observable o, Object obj)-> { System.out.println("DJIAObserver.");});

        DJIAquoteobservable.setQuote();
        DJIAquoteobservable.notifyObservers(DJIAquoteobservable);


    }
}
