import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AccountTest {

    public static final LocalDate XMAX_DAY_2015 = LocalDate.of(2015, 12, 25);
    private Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account();
    }

    @Test
    public void createAccount_emptyTransactionLogs() {
        assertTrue(this.account.getTransactionLogs().isEmpty());
    }

    @Test
    public void deposit_notEmptyTransactionLogs() {
        this.account.deposit(XMAX_DAY_2015, 1);
        assertFalse(this.account.getTransactionLogs().isEmpty());
    }

    @Test
    public void createAccount_balanceIsZero() {
        assertEquals(0, this.account.currentBalance());
    }

    @Test
    public void depositOneTime_balanceIsTheDepositAmount() {
        this.account.deposit(XMAX_DAY_2015, 100);
        assertEquals(100, this.account.currentBalance());
    }

    @Test
    public void withdrawOneTime_balanceIsTheDepositAmount() {
        this.account.withdraw(XMAX_DAY_2015, 100);
        assertEquals(-100, this.account.currentBalance());
    }

    @Test
    public void withdrawTwoTimes_balanceIsDecreasedTwoTimes() {
        this.account.withdraw(XMAX_DAY_2015, 100);
        this.account.withdraw(XMAX_DAY_2015, 100);
        assertEquals(-200, this.account.currentBalance());
    }

    @Test
    public void depositTwoTimes_balanceIsIncreasedTwoTimes() {
        this.account.deposit(XMAX_DAY_2015, 100);
        this.account.deposit(XMAX_DAY_2015, 100);
        assertEquals(200, this.account.currentBalance());
    }

    @Test
    public void withdrawAndDepositMultipleTimes_balanceIsChangedAccordingly() {
        this.account.deposit(XMAX_DAY_2015, 100);
        this.account.withdraw(XMAX_DAY_2015, 50);
        this.account.withdraw(XMAX_DAY_2015, 10);
        this.account.deposit(XMAX_DAY_2015, 110);
        assertEquals(100 - 50 - 10 + 110, this.account.currentBalance());
    }

    @Test
    public void withdraw_notEmptyTransactionLogs() {
        this.account.withdraw(XMAX_DAY_2015, 1);
        assertFalse(this.account.getTransactionLogs().isEmpty());
    }

    @Test
    public void deposit_getFirstTransactionLog() {
        this.account.deposit(XMAX_DAY_2015, 100);
        assertEquals(this.account.getTransactionLogs().get(0), new TransactionLog(XMAX_DAY_2015, 100, 100));
    }

    @Test
    public void withdraw_getFirstTransactionLog() {
        this.account.withdraw(XMAX_DAY_2015, 100);
        assertEquals(this.account.getTransactionLogs().get(0), new TransactionLog(XMAX_DAY_2015, -100, -100));
    }

    @Test
    public void withdrawAndDeposit_getTwoTransactionsLog() {
        this.account.withdraw(XMAX_DAY_2015, 100);
        this.account.deposit(XMAX_DAY_2015, 250);
        assertEquals(this.account.getTransactionLogs().get(0), new TransactionLog(XMAX_DAY_2015, -100, -100));
        assertEquals(this.account.getTransactionLogs().get(1), new TransactionLog(XMAX_DAY_2015, 250, 150));
    }

    @Test
    public void depositAndWithdraw_getTwoTransactionsLog() {
        this.account.deposit(XMAX_DAY_2015, 250);
        this.account.withdraw(XMAX_DAY_2015, 100);
        assertEquals(this.account.getTransactionLogs().get(0), new TransactionLog(XMAX_DAY_2015, 250, 250));
        assertEquals(this.account.getTransactionLogs().get(1), new TransactionLog(XMAX_DAY_2015, -100, 150));
    }

    @Test
    public void createAccount_printStatementIsEmpty() {
        assertTrue(this.account.printStatement().isEmpty());
    }

    @Test
    public void deposit_printStatementOneLine() {
        this.account.deposit(XMAX_DAY_2015, 500);
        assertEquals("25.12.2015 +500 500\n", this.account.printStatement());
    }

    @Test
    public void withdraw_printStatementOneLine() {
        this.account.withdraw(XMAX_DAY_2015, 500);
        assertEquals("25.12.2015 -500 -500\n", this.account.printStatement());
    }

    @Test
    public void depositAndWithDraw_printStatementTwoLines() {
        this.account.deposit(XMAX_DAY_2015, 500);
        this.account.withdraw(XMAX_DAY_2015, 500);
        assertEquals("25.12.2015 +500 500\n25.12.2015 -500 0\n", this.account.printStatement());
    }
}

