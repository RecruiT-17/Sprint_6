package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AnimalTest {
    private Animal animal;
    private String animalKind;
    private List<String> expectedFood;
    private boolean shouldThrowException;

    public AnimalTest(String animalKind, List<String> expectedFood, boolean shouldThrowException) {
        this.animalKind = animalKind;
        this.expectedFood = expectedFood;
        this.shouldThrowException = shouldThrowException;
    }

    @Parameterized.Parameters
    public static Object[][] getFoodData() {
        return new Object[][]{
                {"Травоядное", List.of("Трава", "Различные растения"), false},
                {"Хищник", List.of("Животные", "Птицы", "Рыба"), false},
                {"Неизвестное", null, true},
                {"", null, true}
        };
    }

    @Before
    public void setUp() {
        animal = new Animal();
    }

    @Test
    public void testGetFamily() {
        String family = animal.getFamily();
        assertNotNull(family);
        assertTrue(family.contains("кошачьи"));
    }

    @Test
    public void testGetFood() {
        if (shouldThrowException) {
            try {
                animal.getFood(animalKind);
                fail("Expected Exception was not thrown");
            } catch (Exception e) {
                assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
            }
        } else {
            try {
                List<String> food = animal.getFood(animalKind);
                assertEquals(expectedFood, food);
            } catch (Exception e) {
                fail("Unexpected exception: " + e.getMessage());
            }
        }
    }
}
