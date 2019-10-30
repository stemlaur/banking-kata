import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TransactionLogTest {
    private static final LocalDate NOW = LocalDate.now();

    @Test
    public void assertSameTransactionLogValue_equals() {
        assertEquals(
                new TransactionLog(NOW, 100, 100),
                new TransactionLog(NOW, 100, 100));
    }

    @Test
    public void assertdifferentTransactionLogValue_notEquals() {
        assertNotEquals(
                new TransactionLog(NOW, 110, 100),
                new TransactionLog(NOW, 100, 100));
        assertNotEquals(
                new TransactionLog(NOW.minusDays(1), 100, 100),
                new TransactionLog(NOW, 100, 100));
        assertNotEquals(
                new TransactionLog(NOW, 100, 110),
                new TransactionLog(NOW, 100, 100));
    }
}