package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    @Mock
    private Feline mockFeline;
    private Cat cat;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cat = new Cat(mockFeline);
    }

    @Test
    public void testGetSound() {
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockFeline.eatMeat()).thenReturn(expectedFood);

        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
        verify(mockFeline, times(1)).eatMeat();
    }

    @Test(expected = Exception.class)
    public void testGetFoodThrowsException() throws Exception {
        when(mockFeline.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));
        cat.getFood();
    }
}
