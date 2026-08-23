package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AlexLionTest {
    @Mock
    private Feline mockFeline;
    private AlexLion alex;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        // Настраиваем mock для метода getFood
        when(mockFeline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        alex = new AlexLion(mockFeline);
    }

    @Test
    public void testConstructorSetsSexToMale() throws Exception {
        // Создаем нового льва для этого теста, так как мок уже настроен
        AlexLion lion = new AlexLion(mockFeline);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void testGetFriends() {
        List<String> expected = List.of("Марти", "Глория", "Мелман");
        assertEquals(expected, alex.getFriends());
        assertEquals(3, alex.getFriends().size());
        assertTrue(alex.getFriends().contains("Марти"));
    }

    @Test
    public void testGetPlaceOfLiving() {
        assertEquals("Нью-Йоркский зоопарк", alex.getPlaceOfLiving());
    }

    @Test
    public void testGetKittensOverride() {
        assertEquals(0, alex.getKittens());
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        // Проверяем, что мок возвращает ожидаемый результат
        assertEquals(expectedFood, alex.getFood());
        // Проверяем, что метод действительно вызвался
        verify(mockFeline, atLeastOnce()).getFood("Хищник");
    }
}