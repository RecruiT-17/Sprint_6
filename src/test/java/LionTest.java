package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {
    @Mock
    private Feline mockFeline;
    private Lion lion;

    @Before
    public void setUp() throws Exception {
        when(mockFeline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        lion = new Lion(mockFeline, "Самец");
    }

    @Test
    public void testGetKittens() throws Exception {
        when(mockFeline.getKittens()).thenReturn(1);
        assertEquals(1, lion.getKittens());
    }

    @Test
    public void testGetKittensCallsFeline() throws Exception {
        lion.getKittens();
        verify(mockFeline, times(1)).getKittens();
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, lion.getFood());
    }

    @Test
    public void testGetFoodCallsFeline() throws Exception {
        lion.getFood();
        verify(mockFeline, times(1)).getFood("Хищник");
    }

    @Test
    public void testGetFoodThrowsException() throws Exception {
        when(mockFeline.getFood("Хищник")).thenThrow(new Exception("Ошибка"));

        try {
            lion.getFood();
            fail("Expected Exception was not thrown");
        } catch (Exception e) {
            assertEquals("Ошибка", e.getMessage());
        }
    }
}