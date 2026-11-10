import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit tests for BudgetTracker.
 */
public class BudgetTrackerTest {

    /**
     * Tests a newly created budget tracker.
     */
    @Test
    public void testConstructor() {
        BudgetTracker tracker = new BudgetTracker1();

        assertEquals(0, tracker.size());
        assertEquals(0L, tracker.getBudget());
    }

    /**
     * Tests adding an income transaction.
     */
    @Test
    public void testAddIncome() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.addTransaction("Paycheck", 150000, "Income");

        assertEquals(1, tracker.size());
        assertEquals(150000L, tracker.getTotalIncome());
    }

    /**
     * Tests adding an expense transaction.
     */
    @Test
    public void testAddExpense() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.addTransaction("Groceries", -5000, "Food");

        assertEquals(1, tracker.size());
        assertEquals(5000L, tracker.getTotalExpenses());
    }

    /**
     * Tests removing a transaction.
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

    /**
     * Tests calculating the remaining balance.
     */
    @Test
    public void testBalance() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.addTransaction("Paycheck", 150000, "Income");
        tracker.addTransaction("Groceries", -5000, "Food");

        assertEquals(145000L, tracker.getBalance());
    }

    /**
     * Tests when expenses exceed the budget.
     */
    @Test
    public void testOverBudget() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.setBudget(10000);
        tracker.addTransaction("Shopping", -15000, "Personal");

        assertEquals(true, tracker.isOverBudget());
    }

    /**
     * Tests expenses for a specific category.
     */
    @Test
    public void testExpensesByCategory() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.addTransaction("Groceries", -5000, "Food");
        tracker.addTransaction("Restaurant", -2500, "Food");
        tracker.addTransaction("Gas", -3000, "Transport");

        assertEquals(7500L, tracker.getExpensesByCategory("Food"));
    }

    /**
     * Tests clearing the tracker.
     */
    @Test
    public void testClear() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.setBudget(50000);
        tracker.addTransaction("Groceries", -5000, "Food");

        tracker.clear();

        assertEquals(0, tracker.size());
        assertEquals(0L, tracker.getBudget());
    }

    /**
     * Tests creating a new instance.
     */
    @Test
    public void testNewInstance() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.setBudget(50000);
        tracker.addTransaction("Gas", -3000, "Transport");

        BudgetTracker newTracker = tracker.newInstance();

        assertEquals(0, newTracker.size());
        assertEquals(0L, newTracker.getBudget());

        assertEquals(1, tracker.size());
        assertEquals(50000L, tracker.getBudget());
    }

    /**
     * Tests transferring the representation.
     */
    @Test
    public void testTransferFrom() {
        BudgetTracker source = new BudgetTracker1();
        BudgetTracker destination = new BudgetTracker1();

        source.setBudget(50000);
        source.addTransaction("Groceries", -5000, "Food");

        destination.transferFrom(source);

        assertEquals(1, destination.size());
        assertEquals(50000L, destination.getBudget());
        assertEquals("Groceries",
                destination.getTransaction(0).getDescription());

        assertEquals(0, source.size());
        assertEquals(0L, source.getBudget());
    }

    /**
     * Tests equality between two trackers.
     */
    @Test
    public void testEquals() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.setBudget(50000);
        b.setBudget(50000);

        a.addTransaction("Gas", -3000, "Transport");
        b.addTransaction("Gas", -3000, "Transport");

        assertEquals(true, a.equals(b));
    }

    /**
     * Tests hash code consistency.
     */
    @Test
    public void testHashCode() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.addTransaction("Food", -2500, "Groceries");
        b.addTransaction("Food", -2500, "Groceries");

        assertEquals(a.hashCode(), b.hashCode());
    }

    /**
     * Tests string representation.
     */
    @Test
    public void testToString() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.setBudget(50000);
        tracker.addTransaction("Gas", -3000, "Transport");

        String result = tracker.toString();

        assertEquals(true, result.contains("Budget: $500.0"));
        assertEquals(true, result.contains("Gas"));
    }

    /**
     * Tests an empty tracker's calculations.
     */
    @Test
    public void testEmptyTracker() {
        BudgetTracker tracker = new BudgetTracker1();

        assertEquals(0L, tracker.getTotalIncome());
        assertEquals(0L, tracker.getTotalExpenses());
        assertEquals(0L, tracker.getBalance());
        assertEquals(0L, tracker.getExpensesByCategory("Food"));
        assertEquals(false, tracker.isOverBudget());
    }

    /**
     * Tests expenses exactly equal to the budget.
     */
    @Test
    public void testExactlyAtBudget() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.setBudget(10000);
        tracker.addTransaction("Books", -10000, "School");

        assertEquals(false, tracker.isOverBudget());
    }

    /**
     * Tests two trackers with different budgets.
     */
    @Test
    public void testEqualsDifferentBudgets() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.setBudget(10000);
        b.setBudget(20000);

        assertEquals(false, a.equals(b));
    }

    /**
     * Tests transaction order in equality.
     */
    @Test
    public void testEqualsDifferentOrder() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.addTransaction("Food", -2000, "Food");
        a.addTransaction("Gas", -3000, "Transport");

        b.addTransaction("Gas", -3000, "Transport");
        b.addTransaction("Food", -2000, "Food");

        assertEquals(false, a.equals(b));
    }

    /**
     * Tests removing the final transaction.
     */
    @Test
    public void testRemoveLastTransaction() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.addTransaction("Food", -2000, "Food");
        tracker.addTransaction("Gas", -3000, "Transport");

        Transaction removed = tracker.removeTransaction(1);

        assertEquals("Gas", removed.getDescription());
        assertEquals(1, tracker.size());
        assertEquals(2000L, tracker.getTotalExpenses());
    }

    /**
     * Tests changing the spending limit.
     */
    @Test
    public void testSetBudget() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.setBudget(50000);
        assertEquals(50000L, tracker.getBudget());

        tracker.setBudget(75000);
        assertEquals(75000L, tracker.getBudget());
    }

    /**
     * Tests rejecting a zero-dollar transaction.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testZeroAmount() {
        new Transaction("Coffee", 0, "Food");
    }

    /**
     * Tests rejecting a null description.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullDescription() {
        new Transaction(null, -500, "Food");
    }

    /**
     * Tests rejecting an empty category.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyCategory() {
        new Transaction("Coffee", -500, "");
    }

    /**
     * Tests rejecting a negative budget.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBudget() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.setBudget(-1000);
    }

    /**
     * Tests retrieving a transaction without removing it from the tracker.
     */
    @Test
    public void testGetTransaction() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.addTransaction("Groceries", -5000, "Food");
        tracker.addTransaction("Gas", -3000, "Transport");

        Transaction t = tracker.getTransaction(1);

        assertEquals("Gas", t.getDescription());
        assertEquals(-3000L, t.getAmount());
        assertEquals("Transport", t.getCategory());

        // Verify the transaction was not removed
        assertEquals(2, tracker.size());
    }

    /**
     * Tests comparing a BudgetTracker with an object of a different type.
     */
    @Test
    public void testEqualsDifferentObject() {
        BudgetTracker tracker = new BudgetTracker1();

        tracker.setBudget(50000);

        String other = "Not a BudgetTracker";

        assertEquals(false, tracker.equals(other));
    }
}
