import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import enums.BeverageMenu;
import enums.Ingredient;
import java.util.HashMap;
import java.util.Map;
import main.CoffeeMachine;
import models.Recipe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestUtils;

public class CoffeeMachineTest {

  private static Map<BeverageMenu, Recipe> recipeBook;
  private static Integer totalOutlets;

  @BeforeAll
  static void setup() {
    //Compiling the recipe book.
    recipeBook = new HashMap<>();
    recipeBook.put(BeverageMenu.COFFEE, TestUtils.getCoffeeRecipe());
    recipeBook.put(BeverageMenu.HOT_WATER, TestUtils.getHotWaterRecipe());
    recipeBook.put(BeverageMenu.HOT_MILK, TestUtils.getHotMilkRecipe());
    recipeBook.put(BeverageMenu.GREEN_TEA, TestUtils.getGreenTeaRecipe());
    recipeBook.put(BeverageMenu.GINGER_TEA, TestUtils.getGingerTeaRecipe());
    recipeBook.put(BeverageMenu.BLACK_TEA, TestUtils.getBlackTeaRecipe());
    //Setting max outlets.
    totalOutlets = 3;
  }

  @DisplayName("Trying to refill ingredient without adding it to the machine menu.")
  @Test
  public void test_RefillIngredientWithoutSetting() {
    CoffeeMachine coffeeMachine = new CoffeeMachine(recipeBook, totalOutlets);
    assertFalse(coffeeMachine.refillIngredient(Ingredient.ELAICHI_SYRUP, 40));
  }

  @DisplayName("Trying to refill ingredient after adding it to the machine menu.")
  @Test
  public void test_RefillIngredientWithSetting() {
    CoffeeMachine coffeeMachine = new CoffeeMachine(recipeBook, totalOutlets);
    coffeeMachine.addIngredient(Ingredient.TEA_LEAVES_SYRUP, 10, 50);
    assertTrue(coffeeMachine.refillIngredient(Ingredient.TEA_LEAVES_SYRUP, 40));
  }

  @DisplayName("Adding all components & making coffee, green tea, hot milk and ginger tea. Not enough components for ginger tea. Black tea will have to wait")
  @Test
  public void test_MakeAllDrinks() {
    CoffeeMachine coffeeMachine = new CoffeeMachine(recipeBook, totalOutlets);
    TestUtils.addIngredients(coffeeMachine);
    TestUtils.refillIngredients(coffeeMachine);
    coffeeMachine.prepareBeverage(BeverageMenu.COFFEE);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_MILK);
    coffeeMachine.prepareBeverage(BeverageMenu.GINGER_TEA);
    coffeeMachine.prepareBeverage(BeverageMenu.BLACK_TEA);
    while (coffeeMachine.isMachineWorking()) {
// checking all drinks have been served.
    }
  }

  @DisplayName("Trying to serve hot water thrice. Will serve twice and fail on last try.")
  @Test
  public void test_MakeHotWaterMultipleTimes() {
    CoffeeMachine coffeeMachine = new CoffeeMachine(recipeBook, totalOutlets);
    TestUtils.addIngredients(coffeeMachine);
    TestUtils.refillIngredients(coffeeMachine);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_WATER);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_WATER);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_WATER);
    while (coffeeMachine.isMachineWorking()) {
// checking all drinks have been served.
    }
  }

  @DisplayName("Using all hot water, refilling and using again.")
  @Test
  public void test_UseRefillAndUseAgain() {
    CoffeeMachine coffeeMachine = new CoffeeMachine(recipeBook, totalOutlets);
    TestUtils.addIngredients(coffeeMachine);
    TestUtils.refillIngredients(coffeeMachine);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_WATER);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_WATER);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_WATER);
    while (coffeeMachine.isMachineWorking()) {
// checking all drinks have been served.
    }
    coffeeMachine.refillIngredient(Ingredient.WATER, 500);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_WATER);
    while (coffeeMachine.isMachineWorking()) {
// checking all drinks have been served.
    }
  }

  @DisplayName("Multiple ingredients are low for ginger tea")
  @Test
  public void test_multipleIngredientsNeeded() {
    CoffeeMachine coffeeMachine = new CoffeeMachine(recipeBook, totalOutlets);
    TestUtils.addIngredients(coffeeMachine);
    TestUtils.refillIngredients(coffeeMachine);
    coffeeMachine.prepareBeverage(BeverageMenu.GINGER_TEA);
    coffeeMachine.prepareBeverage(BeverageMenu.BLACK_TEA);
    coffeeMachine.prepareBeverage(BeverageMenu.GREEN_TEA);
    while (coffeeMachine.isMachineWorking()) {
// checking all drinks have been served.
    }
  }

  @DisplayName("Check low ingredients -> without filling any ingredients.")
  @Test
  public void test_checkLowIngredients() {
    CoffeeMachine coffeeMachine = new CoffeeMachine(recipeBook, totalOutlets);
    TestUtils.addIngredients(coffeeMachine);
    assertEquals(7, coffeeMachine.checkLowIngredients().size());
  }

  @DisplayName("Ordering a drink which is not on the menu.")
  @Test
  public void test_orderInvalidDrink() {
    CoffeeMachine coffeeMachine = new CoffeeMachine(recipeBook, totalOutlets);
    TestUtils.addIngredients(coffeeMachine);
    TestUtils.refillIngredients(coffeeMachine);
    coffeeMachine.prepareBeverage(BeverageMenu.ELAICHI_TEA);
  }

  @DisplayName("Making all beverages.")
  @Test
  public void test_makeAll() {
    CoffeeMachine coffeeMachine = new CoffeeMachine(recipeBook, totalOutlets);
    TestUtils.addIngredients(coffeeMachine);
    TestUtils.refillIngredients(coffeeMachine);
    coffeeMachine.refillIngredient(Ingredient.WATER, 500);
    coffeeMachine.refillIngredient(Ingredient.SUGAR_SYRUP, 50);
    coffeeMachine.refillIngredient(Ingredient.GINGER_SYRUP, 50);
    coffeeMachine.prepareBeverage(BeverageMenu.COFFEE);
    coffeeMachine.prepareBeverage(BeverageMenu.BLACK_TEA);
    coffeeMachine.prepareBeverage(BeverageMenu.GINGER_TEA);
    while (coffeeMachine.isMachineWorking()) {
// checking all drinks have been served.
    }
    coffeeMachine.refillIngredient(Ingredient.WATER, 500);
    coffeeMachine.prepareBeverage(BeverageMenu.GREEN_TEA);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_MILK);
    coffeeMachine.prepareBeverage(BeverageMenu.HOT_WATER);
    while (coffeeMachine.isMachineWorking()) {
// checking all drinks have been served.
    }
  }

}
