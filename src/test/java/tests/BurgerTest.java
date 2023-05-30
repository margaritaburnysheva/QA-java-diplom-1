package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.*;
import static praktikum.IngredientType.SAUCE;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    @Mock
    Ingredient secondIngredient;

    @Test
    public void setBunsIsReal() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Bun actualBun = burger.bun;
        assertEquals("Булочку невозможно выбрать", bun, actualBun);
    }

    @Test
    public void addIngredientIsReal() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        List<Ingredient> actualIngredientList = burger.ingredients;
        assertNotNull("Ингредиент невозможно добавить", actualIngredientList);
    }

    @Test
    public void removeIngredientIsReal() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        List<Ingredient> actualIngredientList = burger.ingredients;
        assertTrue("Ингредиент невозможно удалить", actualIngredientList.isEmpty());
    }

    @Test
    public void moveIngredientIsReal() {
        Burger burger = new Burger();
        Mockito.when(secondIngredient.getName()).thenReturn("chili sauce");
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);
        String expectedFirstIngredient = "chili sauce";
        String actualFirstIngredient = burger.ingredients.get(0).getName();
        assertEquals("Ингредиент невозможно переместить", expectedFirstIngredient, actualFirstIngredient);
    }

    @Test
    public void getPriceReturnExpectedPrice() {
        Burger burger = new Burger();
        Mockito.when(bun.getPrice()).thenReturn(300F);
        Mockito.when(ingredient.getPrice()).thenReturn(200F);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float expectedBurgerPrice = 800;
        float actualBurgerPrice = burger.getPrice();
        assertEquals("Стоимость бургера посчитана неверно", expectedBurgerPrice, actualBurgerPrice, 0);
    }

    @Test
    public void getReceiptReturnExpectedReceipt() {
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(300F);
        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getPrice()).thenReturn(200F);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                ingredient.getName()));
        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));
        String expectedReceipt = receipt.toString();
        String actualReceipt = burger.getReceipt();
        assertEquals("Некорректный формат рецепта", expectedReceipt, actualReceipt);
    }
}
