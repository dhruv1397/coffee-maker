package service;

import java.util.concurrent.atomic.AtomicInteger;
import models.Recipe;

//Beverage service takes a recipe and informs when the beverage is ready.
public class BeverageService implements Runnable {

  public static AtomicInteger availableOutlets = new AtomicInteger();
  private final Recipe recipe;

  public BeverageService(Recipe recipe) {
    this.recipe = recipe;
  }

  @Override
  public void run() {
    try {
      //Prepare the beverage for timeInSeconds
      Thread.sleep(recipe.timeInSeconds * 1000);
      //Increase the count for overall available outlets.
      availableOutlets.incrementAndGet();
      //Inform the user.
      System.out.println(recipe.name + " is prepared.");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
