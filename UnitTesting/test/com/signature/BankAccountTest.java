package com.signature;

import org.junit.*;

import static junit.framework.TestCase.*;

public class BankAccountTest {

    private BankAccount account;
    private static int count;

    @BeforeClass
    public static void beforeAll() {
        System.out.println("This method run only one time. Count = " + count++);
    }

    @Before
    public void setup() {
        account = new BankAccount("8254000100043576", 1000.00, BankAccount.SAVING);
        System.out.println("Running test..");
    }


    @Test
    public void deposit() {
        assertEquals(1200.00, account.deposit(200.00, true), 0);
    }

    @Test
    public void withdraw_Branch() {
        assertEquals(400.00, account.withdraw(600.00, true), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdraw_nonBranch() {
        assertEquals(400.00, account.withdraw(600.00, false), 0);
    }

    @Test
    public void getBalance_Deposit() {
        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @Test
    public void getBalance_Withdraw() {
        account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @Test
    public void isCurrent() {
        assertFalse("This is not a saving account", account.isCurrent());
    }

    @After
    public void tearDown() {
        System.out.println("Count = " + count++);
    }

    @AfterClass
    public static void afterAll() {
        System.out.println("This method work only once at the last. Count = " + count++);
    }
}