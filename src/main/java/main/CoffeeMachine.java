package main;

import enums.BeverageMenu;
import enums.Ingredient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import models.Recipe;
import service.BeverageService;


public class CoffeeMachine {

  private static ExecutorService executor;
  private final int totalOutlets;
  private final Map<BeverageMenu, Recipe> recipeBook;
  private final Map<Ingredient, Integer> ingredients;
  private final Map<Ingredient, Integer> minLevel;
  private final Map<Ingredient, Integer> maxLevel;

  //Program the machine, providing the max outlets and the recipe-book, The recipe-book contains which
  // beverages it can make, their compositions and time to prepare.
  public CoffeeMachine(Map<BeverageMenu, Recipe> recipeBook, int outlets) {
    this.totalOutlets = outlets;
    this.recipeBook = recipeBook;
    minLevel = new HashMap<>();
    maxLevel = new HashMap<>();
    ingredients = new HashMap<>();
    BeverageService.availableOutlets.set(outlets);
    executor = Executors.newFixedThreadPool(outlets);
  }

  public void prepareBeverage(BeverageMenu choice) {
    //Check is any outlet is available. If not let the user know and return.
    if (BeverageService.availableOutlets.get() == 0) {
      System.out.println(choice.name() + " please wait for your turn.");
      return;
    }
    //Check is the recipe is supported by the machine.
    if (recipeBook.containsKey(choice)) {
      Recipe recipe = recipeBook.get(choice);
      //Check is all the required components are there.
      List<Ingredient> missingIngredients = checkMissingIngredients(recipe);
      if (missingIngredients.isEmpty()) {
        //Update all the components which will  be used to make the beverage.
        getIngredients(recipe);
        //Make one of the outlets unavailable.
        BeverageService.availableOutlets.decrementAndGet();
        BeverageService beverageService = new BeverageService(recipe);
        //Submit the task to run. Once it is finished, it will return the outlet to available state
        // and let the user know.
        executor.submit(beverageService);
      } else {
        StringBuilder errorString = new StringBuilder();
        for (Ingredient i : missingIngredients) {
          errorString.append(i.name());
          errorString.append(", ");
        }
        System.out.println(
            "Beverage " + choice.name() + " cannot be prepared because " + errorString
                + "is not available");
      }
    } else {
      System.out.println("Beverage " + choice.name() + " not added to the machine!");
    }
  }

  //Program the machine to accept the given ingredient,set its min and max holding values.
  // Min value will be used to alert for refill.
  public void addIngredient(Ingredient ingredient, Integer minValue, Integer maxValue) {
    minLevel.put(ingredient, minValue);
    maxLevel.put(ingredient, maxValue);
    ingredients.put(ingredient, 0);
  }

  //Given an ingredient, check if the machine is programmed to hold that ingredient and refill
  // as much as possible.If entered value is higher than the holding capacity for that ingredient,
  // refill upto the holding capacity.
  public Boolean refillIngredient(Ingredient ingredient, Integer value) {
    if (ingredients.containsKey(ingredient)) {
      ingredients
          .put(ingredient, Math.min(maxLevel.get(ingredient), ingredients.get(ingredient) + value));
      System.out
          .println(value + " units of ingredient " + ingredient.name() + " added to the machine!");
      return true;
    } else {
      System.out.println("Ingredient " + ingredient.name() + " not added to the machine!");
      return false;
    }
  }

  //Check all ingredients to and return names of those whose value is less than the respective min value.
  public List<Ingredient> checkLowIngredients() {
    List<Ingredient> missingIngredients = new ArrayList<>();
    minLevel.forEach((key, value) -> {
      if (ingredients.getOrDefault(key, 0) < value) {
        missingIngredients.add(key);
      }
    });
    return missingIngredients;
  }

  //Given a recipe, check that all ingredients are available in required amounts.
  // If not, return their names.
  private List<Ingredient> checkMissingIngredients(Recipe recipe) {
    List<Ingredient> missingIngredients = new ArrayList<>();
    recipe.ingredients.forEach((key, value) -> {
      if (ingredients.getOrDefault(key, 0) < value) {
        missingIngredients.add(key);
      }
    });
    return missingIngredients;
  }

  //Check if any outlet is still busy. If any beverage is being made,its service will not have
  // updated the availableOutlets.
  public boolean isMachineWorking() {
    return BeverageService.availableOutlets.get() < totalOutlets;
  }

  //Given a recipe, update the total ingredients' values after using what is required by this service.
  private void getIngredients(Recipe recipe) {
    recipe.ingredients.forEach((key, value) -> {
      ingredients.put(key, ingredients.get(key) - value);
    });
  }
}
