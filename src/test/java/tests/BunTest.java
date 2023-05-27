package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }
    @Parameterized.Parameters(name="Тестовые данные: {0}, {1}")
    public static Object[][] getBunData(){
        return new Object[][]{
                {"black bun",100},
                {"white bun", 200},
                {"red bun", 300},
        };
    }
    @Test
    public void getNameReturnExpectedName(){
        Bun bun=new Bun(name,price);
        String actualName=bun.getName();
        assertEquals("Название булочки не соответствует ожидаемому",name,actualName);
    }
    @Test
    public void getPriceReturnExpectedPrice(){
        Bun bun=new Bun(name,price);
        float actualPrice=bun.getPrice();
        assertEquals("Цена булочки не соответствует ожидаемой",price,actualPrice,0);
    }
}

