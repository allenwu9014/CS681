package edu.umb.cs681.hw16;

import java.util.HashMap;
import java.util.Map;

public class StockQuoteObservable extends Observable {
  private Map<String, Float> tickerquotemap = new HashMap<>();


  public void setQuote(String t, float q) {

      tickerquotemap.put(t, q);
      System.out.println("Stock, "+ "set Ticker = " + t + ", Quote = " + q);
  }


}
