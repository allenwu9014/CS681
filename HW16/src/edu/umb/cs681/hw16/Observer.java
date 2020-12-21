package edu.umb.cs681.hw16;

@FunctionalInterface
public interface Observer {
  public abstract void update(Observable obs, Object obj);
}
