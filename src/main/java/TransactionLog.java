import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@ToString
public final class TransactionLog {
    private final LocalDate transactionDate;
    private final int amount;
    private final int balance;

    public TransactionLog(final LocalDate transactionDate, final int amount, final int balance) {

        this.transactionDate = transactionDate;
        this.amount = amount;
        this.balance = balance;
    }
}
