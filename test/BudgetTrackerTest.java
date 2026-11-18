import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the enhanced BudgetTracker operations.
 */
public class BudgetTrackerTest {

    @Test
    public void testEmptyTracker() {
        BudgetTracker tracker = new BudgetTracker1();

        assertEquals(0L, tracker.getTotalIncome());
        assertEquals(0L, tracker.getTotalExpenses());
        assertEquals(0L, tracker.getBalance());
        assertEquals(0L, tracker.getExpensesByCategory("Food"));
        assertFalse(tracker.isOverBudget());
    }

    @Test
    public void testTotalIncome() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Paycheck", 150000, "Income");
        tracker.addTransaction("Gift", 5000, "Income");
        tracker.addTransaction("Food", -3000, "Food");

        assertEquals(155000L, tracker.getTotalIncome());
    }

    @Test
    public void testTotalExpenses() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Paycheck", 150000, "Income");
        tracker.addTransaction("Food", -5000, "Food");
        tracker.addTransaction("Gas", -3000, "Travel");

        assertEquals(8000L, tracker.getTotalExpenses());
    }

    @Test
    public void testBalance() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Paycheck", 150000, "Income");
        tracker.addTransaction("Groceries", -5000, "Food");

        assertEquals(145000L, tracker.getBalance());
    }

    @Test
    public void testNegativeBalance() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Income", 1000, "Pay");
        tracker.addTransaction("Expense", -3000, "Food");

        assertEquals(-2000L, tracker.getBalance());
    }

    @Test
    public void testOverBudget() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.setBudget(10000);
        tracker.addTransaction("Shopping", -15000, "Personal");

        assertTrue(tracker.isOverBudget());
    }

    @Test
    public void testExactlyAtBudget() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.setBudget(10000);
        tracker.addTransaction("Books", -10000, "School");

        assertFalse(tracker.isOverBudget());
    }

    @Test
    public void testUnderBudget() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.setBudget(10000);
        tracker.addTransaction("Books", -9000, "School");

        assertFalse(tracker.isOverBudget());
    }

    @Test
    public void testZeroBudgetWithExpense() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Lunch", -500, "Food");

        assertTrue(tracker.isOverBudget());
    }

    @Test
    public void testExpensesByCategory() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Groceries", -5000, "Food");
        tracker.addTransaction("Restaurant", -2500, "Food");
        tracker.addTransaction("Gas", -3000, "Transport");

        assertEquals(7500L, tracker.getExpensesByCategory("Food"));
        assertEquals(3000L, tracker.getExpensesByCategory("Transport"));
    }

    @Test
    public void testCategoryNotFound() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Gas", -3000, "Transport");

        assertEquals(0L, tracker.getExpensesByCategory("Food"));
    }

    @Test
    public void testIncomeIgnoredByCategoryExpenses() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Refund", 300, "Food");
        tracker.addTransaction("Lunch", -200, "Food");

        assertEquals(200L, tracker.getExpensesByCategory("Food"));
    }

    @Test
    public void testEqualsSameObject() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Food", -2000, "Food");

        assertTrue(tracker.equals(tracker));
    }

    @Test
    public void testEqualsIdenticalTrackers() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.setBudget(50000);
        b.setBudget(50000);
        a.addTransaction("Gas", -3000, "Transport");
        b.addTransaction("Gas", -3000, "Transport");

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    public void testEqualsDifferentBudgets() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.setBudget(10000);
        b.setBudget(20000);

        assertFalse(a.equals(b));
    }

    @Test
    public void testEqualsDifferentSizes() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.addTransaction("Food", -2000, "Food");

        assertFalse(a.equals(b));
    }

    @Test
    public void testEqualsDifferentOrder() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.addTransaction("Food", -2000, "Food");
        a.addTransaction("Gas", -3000, "Transport");

        b.addTransaction("Gas", -3000, "Transport");
        b.addTransaction("Food", -2000, "Food");

        assertFalse(a.equals(b));
    }

    @Test
    public void testEqualsDifferentAmount() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.addTransaction("Food", -2000, "Food");
        b.addTransaction("Food", -3000, "Food");

        assertFalse(a.equals(b));
    }

    @Test
    public void testEqualsDifferentDescription() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.addTransaction("Lunch", -2000, "Food");
        b.addTransaction("Dinner", -2000, "Food");

        assertFalse(a.equals(b));
    }

    @Test
    public void testEqualsDifferentCategory() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.addTransaction("Lunch", -2000, "Food");
        b.addTransaction("Lunch", -2000, "Dining");

        assertFalse(a.equals(b));
    }

    @Test
    public void testEqualsDifferentObject() {
        BudgetTracker tracker = new BudgetTracker1();
        Object differentObject = "Not a BudgetTracker";

        assertFalse(tracker.equals(differentObject));
        assertFalse(tracker.equals(null));
    }

    @Test
    public void testHashCodeConsistency() {
        BudgetTracker a = new BudgetTracker1();
        BudgetTracker b = new BudgetTracker1();

        a.setBudget(500);
        b.setBudget(500);
        a.addTransaction("A", 100, "Income");
        b.addTransaction("A", 100, "Income");

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testToString() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.setBudget(50000);
        tracker.addTransaction("Gas", -3000, "Transportation");

        String result = tracker.toString();

        assertTrue(result.contains("Budget: $500.00"));
        assertTrue(result.contains("Gas: -$30.00"));
    }

    @Test
    public void testToStringEmpty() {
        BudgetTracker tracker = new BudgetTracker1();

        assertTrue(tracker.toString().contains("Budget: $0.00"));
    }

    @Test
    public void testCalculationsAfterRemoval() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Income", 10000, "Pay");
        tracker.addTransaction("Food", -2000, "Food");
        tracker.addTransaction("Gas", -1000, "Travel");

        tracker.removeTransaction(1);

        assertEquals(10000L, tracker.getTotalIncome());
        assertEquals(1000L, tracker.getTotalExpenses());
        assertEquals(9000L, tracker.getBalance());
    }

    @Test
    public void testLargeValidAmounts() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Income", 1000000000L, "Pay");
        tracker.addTransaction("Expense", -500000000L, "Food");

        assertEquals(1000000000L, tracker.getTotalIncome());
        assertEquals(500000000L, tracker.getTotalExpenses());
        assertEquals(500000000L, tracker.getBalance());
    }
}
