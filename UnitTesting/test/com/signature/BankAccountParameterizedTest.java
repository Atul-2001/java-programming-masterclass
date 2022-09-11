package com.signature;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class BankAccountParameterizedTest {
    private BankAccount account;
    private double amount;
    private boolean branch;
    private double expected;

    public BankAccountParameterizedTest(double amount, boolean branch, double expected) {
        this.amount = amount;
        this.branch = branch;
        this.expected = expected;
    }

    @Before
    public void setup() {
        account = new BankAccount("8254000100047829", 1000.00, BankAccount.SAVING);
        System.out.println("Running a test....");
    }

    @Parameterized.Parameters
    public static Collection<Object> testCases() {
        return Arrays.asList(new Object[][] {
                {100.00, true, 1100.00},
                {200.00, true, 1200.00},
                {325.14, true, 1325.14},
                {489.33, true, 1489.33},
                {1000.00, true, 2000.00}
        });
    }

    @Test
    public void deposit() {
        account.deposit(amount, branch);
        assertEquals(expected, account.getBalance(), 0.01);
    }

}
