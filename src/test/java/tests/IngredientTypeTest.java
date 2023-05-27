package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
    private final String ingredientType;
    private final IngredientType expectedIngredientType;

    public IngredientTypeTest(String ingredientType, IngredientType expectedIngredientType) {
        this.ingredientType = ingredientType;
        this.expectedIngredientType = expectedIngredientType;
    }
    @Parameterized.Parameters(name="Тестовые данные: {0}, {1}")
    public static Object[][] getIngredientTypeData() {
        return new Object[][]{
                {"SAUCE",IngredientType.SAUCE},
                {"FILLING",IngredientType.FILLING},
        };
    }
    @Test
    public void ingredientTypeIncludesExpectedTypes(){
        IngredientType actualIngredientType=IngredientType.valueOf(ingredientType);
        assertEquals("Тип ингредиента не соответствует ожидаемому",actualIngredientType,expectedIngredientType);
    }
}

