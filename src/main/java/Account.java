import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public final class Account {
    private static final DecimalFormat SIGNED_INT_FORMAT = new DecimalFormat("+#;-#");
    private final List<TransactionLog> transactionLogs;
    private int currentBalance;

    public Account() {
        this.transactionLogs = new ArrayList<>();
        this.currentBalance = 0;
    }

    public List getTransactionLogs() {
        return transactionLogs;
    }

    public void deposit(final LocalDate depositDate, final int amount) {
        this.currentBalance += amount;
        this.transactionLogs.add(
                new TransactionLog(depositDate, amount, this.currentBalance)
        );
    }

    public void withdraw(final LocalDate withdrawDate, final int amount) {
        this.currentBalance -= amount;
        this.transactionLogs.add(
                new TransactionLog(withdrawDate, -amount, this.currentBalance)
        );
    }

    int currentBalance() {
        return this.currentBalance;
    }

    public String printStatement() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (TransactionLog transactionLog : this.transactionLogs) {
            stringBuilder.append(
                    format("%s %s %s", transactionLog.getTransactionDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                            , SIGNED_INT_FORMAT.format(transactionLog.getAmount()), transactionLog.getBalance())
            ).append('\n');
        }
        return stringBuilder.toString();
    }
}
