package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineTest {
    private Feline feline;
    private String animalKind;
    private List<String> expectedFood;
    private boolean shouldThrowException;

    public FelineTest(String animalKind, List<String> expectedFood, boolean shouldThrowException) {
        this.animalKind = animalKind;
        this.expectedFood = expectedFood;
        this.shouldThrowException = shouldThrowException;
    }

    @Parameterized.Parameters
    public static Object[][] getFoodData() {
        return new Object[][]{
                {"Травоядное", List.of("Трава", "Различные растения"), false},
                {"Хищник", List.of("Животные", "Птицы", "Рыба"), false},
                {"Неизвестное", null, true}
        };
    }

    @Before
    public void setUp() {
        feline = new Feline();
    }

    @Test
    public void testGetFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittensWithoutParameter() {
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void testGetKittensWithParameter() {
        assertEquals(5, feline.getKittens(5));
        assertEquals(0, feline.getKittens(0));
        assertEquals(-3, feline.getKittens(-3));
    }

    @Test
    public void testEatMeat() throws Exception {
        List<String> meat = feline.eatMeat();
        assertEquals(List.of("Животные", "Птицы", "Рыба"), meat);
    }

    @Test
    public void testGetFood() throws Exception {
        if (shouldThrowException) {
            try {
                feline.getFood(animalKind);
                fail("Expected Exception was not thrown");
            } catch (Exception e) {
                assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
            }
        } else {
            List<String> food = feline.getFood(animalKind);
            assertEquals(expectedFood, food);
        }
    }
}