package models;

import enums.BeverageMenu;
import enums.Ingredient;
import java.util.Map;

//A recipe object will contain the ingredients, their quantities and time required to prepare a beverage.
public class Recipe {

  public BeverageMenu name;
  public Map<Ingredient, Integer> ingredients;
  public Long timeInSeconds;

}
