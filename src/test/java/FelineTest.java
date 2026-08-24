package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FelineSimpleTest {
    private Feline feline;

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
    public void testGetKittensWithFive() {
        assertEquals(5, feline.getKittens(5));
    }

    @Test
    public void testGetKittensWithZero() {
        assertEquals(0, feline.getKittens(0));
    }

    @Test
    public void testGetKittensWithNegative() {
        assertEquals(-3, feline.getKittens(-3));
    }

    @Test
    public void testEatMeat() throws Exception {
        List<String> meat = feline.eatMeat();
        assertEquals(List.of("Животные", "Птицы", "Рыба"), meat);
    }
}