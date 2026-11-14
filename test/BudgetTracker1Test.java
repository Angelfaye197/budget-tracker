import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit tests for the BudgetTracker1 kernel implementation and Standard
 * methods.
 */
public class BudgetTracker1Test {

    /**
     * Tests the initial state of a new BudgetTracker.
     */
    @Test
    public void testConstructor() {
        BudgetTracker tracker = new BudgetTracker1();

        assertEquals(0, tracker.size());
        assertEquals(0L, tracker.getBudget());
    }

    /**
     * Tests adding a transaction to an empty tracker.
     */
    @Test
    public void testAddTransaction() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.addTransaction("Paycheck", 150000, "Income");

        assertEquals(1, tracker.size());
        assertEquals("Paycheck", tracker.getTransaction(0).getDescription());
        assertEquals(150000L, tracker.getTransaction(0).getAmount());
        assertEquals("Income", tracker.getTransaction(0).getCategory());
    }

    /**
     * Tests removing a transaction from the tracker.
     */
    @Test
    public void testRemoveTransaction() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.addTransaction("Groceries", -5000, "Food");
        tracker.addTransaction("Gas", -3000, "Transport");

        Transaction removed = tracker.removeTransaction(0);

        assertEquals("Groceries", removed.getDescription());
        assertEquals(1, tracker.size());
        assertEquals("Gas", tracker.getTransaction(0).getDescription());
    }
}
