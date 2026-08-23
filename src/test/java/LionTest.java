package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LionTest {
    private Feline feline;
    private String sex;
    private boolean expectedHasMane;
    private boolean shouldThrowException;

    public LionTest(String sex, boolean expectedHasMane, boolean shouldThrowException) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
        this.shouldThrowException = shouldThrowException;
    }

    @Parameterized.Parameters
    public static Object[][] getSexData() {
        return new Object[][]{
                {"Самец", true, false},
                {"Самка", false, false},
                {"Неизвестно", false, true},
                {"самец", false, true},  // проверка регистра
                {"", false, true}
        };
    }

    @Before
    public void setUp() {
        feline = new Feline();
    }

    @Test
    public void testConstructorAndDoesHaveMane() {
        if (shouldThrowException) {
            try {
                new Lion(feline, sex);
                fail("Expected Exception was not thrown");
            } catch (Exception e) {
                assertEquals("Используйте допустимые значения пола животного - Самец или Самка", e.getMessage());
            }
        } else {
            try {
                Lion lion = new Lion(feline, sex);
                assertEquals(expectedHasMane, lion.doesHaveMane());
            } catch (Exception e) {
                fail("Unexpected exception: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetKittens() throws Exception {
        Lion lion = new Lion(feline, "Самец");
        assertEquals(1, lion.getKittens());
    }

    @Test
    public void testGetFood() throws Exception {
        Lion lion = new Lion(feline, "Самец");
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, lion.getFood());
    }

    @Test
    public void testGetFoodWithMock() throws Exception {
        Feline mockFeline = mock(Feline.class);
        List<String> mockFood = List.of("Mock", "Food");
        when(mockFeline.getFood("Хищник")).thenReturn(mockFood);

        Lion lion = new Lion(mockFeline, "Самец");
        assertEquals(mockFood, lion.getFood());
        verify(mockFeline, times(1)).getFood("Хищник");
    }

    @Test(expected = Exception.class)
    public void testGetFoodThrowsException() throws Exception {
        Feline mockFeline = mock(Feline.class);
        when(mockFeline.getFood("Хищник")).thenThrow(new Exception("Ошибка"));

        Lion lion = new Lion(mockFeline, "Самец");
        lion.getFood();
    }
}