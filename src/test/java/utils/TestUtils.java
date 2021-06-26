package utils;

import enums.BeverageMenu;
import enums.Ingredient;
import java.util.HashMap;
import java.util.Map;
import main.CoffeeMachine;
import models.Recipe;

public class TestUtils {

  //Setting the recipe for coffee.
  public static Recipe getCoffeeRecipe() {
    Recipe recipe = new Recipe();
    recipe.name = BeverageMenu.COFFEE;
    Map<Ingredient, Integer> map = new HashMap<>();
    map.put(Ingredient.WATER, 150);
    map.put(Ingredient.MILK, 50);
    map.put(Ingredient.COFFEE_POWDER, 20);
    recipe.timeInSeconds = 3L;
    recipe.ingredients = map;
    return recipe;
  }

  //Setting the recipe for hot milk.
  public static Recipe getHotMilkRecipe() {
    Recipe recipe = new Recipe();
    recipe.name = BeverageMenu.HOT_MILK;
    Map<Ingredient, Integer> map = new HashMap<>();
    map.put(Ingredient.MILK, 200);
    map.put(Ingredient.SUGAR_SYRUP, 20);
    recipe.timeInSeconds = 3L;
    recipe.ingredients = map;
    return recipe;
  }

  //Setting the recipe for green tea.
  public static Recipe getGreenTeaRecipe() {
    Recipe recipe = new Recipe();
    recipe.name = BeverageMenu.GREEN_TEA;
    Map<Ingredient, Integer> map = new HashMap<>();
    map.put(Ingredient.GREEN_MIXTURE, 20);
    map.put(Ingredient.WATER, 200);
    recipe.timeInSeconds = 3L;
    recipe.ingredients = map;
    return recipe;
  }

  //Setting the recipe for hot water.
  public static Recipe getHotWaterRecipe() {
    Recipe recipe = new Recipe();
    recipe.name = BeverageMenu.HOT_WATER;
    Map<Ingredient, Integer> map = new HashMap<>();
    map.put(Ingredient.WATER, 200);
    recipe.timeInSeconds = 3L;
    recipe.ingredients = map;
    return recipe;
  }

  //Setting the recipe for green tea.
  public static Recipe getGingerTeaRecipe() {
    Recipe recipe = new Recipe();
    recipe.name = BeverageMenu.GINGER_TEA;
    Map<Ingredient, Integer> map = new HashMap<>();
    map.put(Ingredient.GINGER_SYRUP, 20);
    map.put(Ingredient.WATER, 100);
    map.put(Ingredient.MILK, 100);
    map.put(Ingredient.SUGAR_SYRUP, 20);
    map.put(Ingredient.TEA_LEAVES_SYRUP, 20);
    recipe.timeInSeconds = 3L;
    recipe.ingredients = map;
    return recipe;
  }

  //Setting the recipe for black tea.
  public static Recipe getBlackTeaRecipe() {
    Recipe recipe = new Recipe();
    recipe.name = BeverageMenu.BLACK_TEA;
    Map<Ingredient, Integer> map = new HashMap<>();
    map.put(Ingredient.WATER, 200);
    map.put(Ingredient.TEA_LEAVES_SYRUP, 20);
    recipe.timeInSeconds = 3L;
    recipe.ingredients = map;
    return recipe;
  }

  public static void addIngredients(CoffeeMachine coffeeMachine) {
    coffeeMachine.addIngredient(Ingredient.TEA_LEAVES_SYRUP, 10, 50);
    coffeeMachine.addIngredient(Ingredient.COFFEE_POWDER, 10, 50);
    coffeeMachine.addIngredient(Ingredient.WATER, 10, 500);
    coffeeMachine.addIngredient(Ingredient.MILK, 10, 500);
    coffeeMachine.addIngredient(Ingredient.GINGER_SYRUP, 10, 50);
    coffeeMachine.addIngredient(Ingredient.SUGAR_SYRUP, 10, 50);
    coffeeMachine.addIngredient(Ingredient.GREEN_MIXTURE, 10, 50);
  }

  public static void refillIngredients(CoffeeMachine coffeeMachine) {
    coffeeMachine.refillIngredient(Ingredient.TEA_LEAVES_SYRUP, 40);
    coffeeMachine.refillIngredient(Ingredient.COFFEE_POWDER, 40);
    coffeeMachine.refillIngredient(Ingredient.WATER, 400);
    coffeeMachine.refillIngredient(Ingredient.MILK, 400);
    coffeeMachine.refillIngredient(Ingredient.GINGER_SYRUP, 10);
    coffeeMachine.refillIngredient(Ingredient.SUGAR_SYRUP, 5);
    coffeeMachine.refillIngredient(Ingredient.GREEN_MIXTURE, 40);
  }

}
