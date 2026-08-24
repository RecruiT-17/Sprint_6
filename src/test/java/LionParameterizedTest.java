package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LionParameterizedTest {
    private Feline feline;
    private String sex;
    private boolean expectedHasMane;
    private boolean shouldThrowException;

    public LionParameterizedTest(String sex, boolean expectedHasMane, boolean shouldThrowException) {
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
}


