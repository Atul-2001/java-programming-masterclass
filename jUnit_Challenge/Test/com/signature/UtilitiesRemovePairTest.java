package com.signature;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class UtilitiesRemovePairTest {
    private Utilities utilities;
    private String source;
    private String expected;

    public UtilitiesRemovePairTest(String source, String expected) {
        this.source = source;
        this.expected = expected;
    }

    @Before
    public void setup() {
        utilities = new Utilities();
        System.out.println("Running Test...");
    }

    @Parameterized.Parameters
    public static Collection<Object> testCases() {
        return Arrays.asList(new Object[][] {
                {"ABCDEFF", "ABCDEF"},
                {"AB88EFFG", "AB8EFG"},
                {"112233445566", "123456"},
                {"ZYZQQB", "ZYZQB"},
                {"A", "A"}
        });
    }

    @Test
    public void removePairs() {
        assertEquals(expected, utilities.removePairs(source));
    }
}
