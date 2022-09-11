package com.signature;

import java.util.Arrays;

import static org.junit.Assert.*;

public class UtilitiesTest {

    private Utilities utilities;

    @org.junit.Before
    public void setUp() throws Exception {
        utilities = new Utilities();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void everyNthChar() {
        char[] letters = {'h','e','l','l','o'};
        char[] result = {'e','l'};
        assertEquals(Arrays.toString(result), Arrays.toString(utilities.everyNthChar(letters, 2)));
        assertArrayEquals(new char[] {'e','l'}, utilities.everyNthChar(new char[] {'h','e','l','l','o'}, 2));
        assertArrayEquals(new char[] {'h','e','l','l','o'}, utilities.everyNthChar(new char[] {'h','e','l','l','o'}, 6));
    }

    @org.junit.Test
    public void removePairs() {
        assertEquals("ABCDEF" , utilities.removePairs("AABCDDEFF"));
        assertEquals("ABCABDEF" , utilities.removePairs("ABCCABDEEF"));
        assertEquals("ABCDEF", utilities.removePairs("AABBCCDDEEFF"));
        assertEquals("A", utilities.removePairs("AA"));
        assertEquals("A", utilities.removePairs("A"));
        assertNull("No argument passed", utilities.removePairs(null));

    }

    @org.junit.Test
    public void converter() {
        assertEquals(300, utilities.converter(10, 5));
    }

    @org.junit.Test(expected = ArithmeticException.class)
    public void converter_arithmeticException() {
        utilities.converter(10,0);
//        assertEquals(308, utilities.converter(10, 1));
    }

    @org.junit.Test
    public void nullIfOddLength() {
        assertNull(utilities.nullIfOddLength("hello"));
        assertNotNull(utilities.nullIfOddLength("helloWorld"));
    }
}