import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the BudgetTracker1 kernel and Standard methods.
 */
public class BudgetTracker1Test {

    /**
     * Tests the initial empty state.
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
        final long income = 150000;
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Paycheck", income, "Income");

        assertEquals(1, tracker.size());
        assertEquals("Paycheck", tracker.getTransaction(0).getDescription());
        assertEquals(income, tracker.getTransaction(0).getAmount());
        assertEquals("Income", tracker.getTransaction(0).getCategory());
    }

    /**
     * Tests adding an expense transaction.
     */
    @Test
    public void testAddExpense() {
        final long expense = -5000;
        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Groceries", expense, "Food");

        assertEquals(1, tracker.size());
        assertEquals(expense, tracker.getTransaction(0).getAmount());
    }

    /**
     * Tests insertion order and preservation of the budget.
     */
    @Test
    public void testAddSeveralPreservesOrderAndBudget() {
        final long budget = 7000;
        final long firstAmount = 100;
        final long secondAmount = -200;
        final long thirdAmount = 300;
        final int expectedSize = 3;

        BudgetTracker tracker = new BudgetTracker1();
        tracker.setBudget(budget);
        tracker.addTransaction("First", firstAmount, "A");
        tracker.addTransaction("Second", secondAmount, "B");
        tracker.addTransaction("Third", thirdAmount, "C");

        assertEquals(expectedSize, tracker.size());
        assertEquals("First", tracker.getTransaction(0).getDescription());
        assertEquals("Second", tracker.getTransaction(1).getDescription());
        assertEquals("Third", tracker.getTransaction(2).getDescription());
        assertEquals(budget, tracker.getBudget());
    }

    /**
     * Tests removing the first transaction.
     */
    @Test
    public void testRemoveFirst() {
        final long income = 100;
        final long expense = -200;

        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("A", income, "Income");
        tracker.addTransaction("B", expense, "Food");

        Transaction removed = tracker.removeTransaction(0);

        assertEquals("A", removed.getDescription());
        assertEquals(1, tracker.size());
        assertEquals("B", tracker.getTransaction(0).getDescription());
    }

    /**
     * Tests removing a middle transaction.
     */
    @Test
    public void testRemoveMiddle() {
        final long income = 100;
        final long foodExpense = -200;
        final long travelExpense = -300;

        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("A", income, "Income");
        tracker.addTransaction("B", foodExpense, "Food");
        tracker.addTransaction("C", travelExpense, "Travel");

        Transaction removed = tracker.removeTransaction(1);

        assertEquals("B", removed.getDescription());
        assertEquals(2, tracker.size());
        assertEquals("A", tracker.getTransaction(0).getDescription());
        assertEquals("C", tracker.getTransaction(1).getDescription());
    }

    /**
     * Tests removing the last transaction.
     */
    @Test
    public void testRemoveLast() {
        final long income = 100;
        final long expense = -200;

        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("A", income, "Income");
        tracker.addTransaction("B", expense, "Food");

        Transaction removed = tracker.removeTransaction(1);

        assertEquals("B", removed.getDescription());
        assertEquals(1, tracker.size());
        assertEquals("A", tracker.getTransaction(0).getDescription());
    }

    /**
     * Tests removing the only transaction.
     */
    @Test
    public void testRemoveOnlyTransaction() {
        final long expense = -1500;

        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Lunch", expense, "Food");

        Transaction removed = tracker.removeTransaction(0);

        assertEquals(expense, removed.getAmount());
        assertEquals(0, tracker.size());
    }

    /**
     * Tests that getting a transaction preserves the tracker state.
     */
    @Test
    public void testGetTransactionPreservesState() {
        final long budget = 500;
        final long expense = -1500;

        BudgetTracker tracker = new BudgetTracker1();
        tracker.setBudget(budget);
        tracker.addTransaction("Lunch", expense, "Food");

        Transaction first = tracker.getTransaction(0);
        Transaction again = tracker.getTransaction(0);

        assertTrue(first == again);
        assertEquals(1, tracker.size());
        assertEquals(budget, tracker.getBudget());
    }

    /**
     * Tests changing and resetting the budget.
     */
    @Test
    public void testSetBudget() {
        final long income = 12000;
        final long budget = 2500;

        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("Income", income, "Pay");
        tracker.setBudget(budget);

        assertEquals(budget, tracker.getBudget());
        assertEquals(1, tracker.size());

        tracker.setBudget(0);
        assertEquals(0L, tracker.getBudget());
    }

    /**
     * Tests clearing a nonempty tracker.
     */
    @Test
    public void testClearNonempty() {
        final long budget = 2500;
        final long income = 12000;

        BudgetTracker tracker = new BudgetTracker1();
        tracker.setBudget(budget);
        tracker.addTransaction("Income", income, "Pay");

        tracker.clear();

        assertEquals(0, tracker.size());
        assertEquals(0L, tracker.getBudget());
    }

    /**
     * Tests clearing an already empty tracker.
     */
    @Test
    public void testClearEmpty() {
        BudgetTracker tracker = new BudgetTracker1();
        tracker.clear();

        assertEquals(0, tracker.size());
        assertEquals(0L, tracker.getBudget());
    }

    /**
     * Tests reusing a tracker after clearing it.
     */
    @Test
    public void testClearAndReuse() {
        final long income = 100;
        final long expense = -50;

        BudgetTracker tracker = new BudgetTracker1();
        tracker.addTransaction("A", income, "Income");
        tracker.clear();
        tracker.addTransaction("B", expense, "Food");

        assertEquals(1, tracker.size());
        assertEquals("B", tracker.getTransaction(0).getDescription());
    }

    /**
     * Tests that a new instance is independent of the original.
     */
    @Test
    public void testNewInstanceIndependent() {
        final long originalBudget = 500;
        final long freshBudget = 900;
        final long income = 100;
        final long expense = -200;

        BudgetTracker original = new BudgetTracker1();
        original.setBudget(originalBudget);
        original.addTransaction("A", income, "Income");

        BudgetTracker fresh = original.newInstance();
        fresh.setBudget(freshBudget);
        fresh.addTransaction("B", expense, "Food");

        assertEquals(originalBudget, original.getBudget());
        assertEquals("A", original.getTransaction(0).getDescription());
        assertEquals(freshBudget, fresh.getBudget());
        assertEquals("B", fresh.getTransaction(0).getDescription());
    }

    /**
     * Tests transferring into a nonempty destination.
     */
    @Test
    public void testTransferIntoNonemptyDestination() {
        final long sourceBudget = 700;
        final long destinationBudget = 900;
        final long income = 100;
        final long expense = -100;

        BudgetTracker source = new BudgetTracker1();
        source.setBudget(sourceBudget);
        source.addTransaction("Source", income, "Income");

        BudgetTracker destination = new BudgetTracker1();
        destination.setBudget(destinationBudget);
        destination.addTransaction("Old", expense, "Food");

        destination.transferFrom(source);

        assertEquals(sourceBudget, destination.getBudget());
        assertEquals(1, destination.size());
        assertEquals("Source", destination.getTransaction(0).getDescription());
        assertEquals(0L, source.getBudget());
        assertEquals(0, source.size());
    }

    /**
     * Tests transferring an empty source into a nonempty destination.
     */
    @Test
    public void testTransferEmptySource() {
        final long destinationBudget = 800;
        final long income = 100;

        BudgetTracker source = new BudgetTracker1();
        BudgetTracker destination = new BudgetTracker1();
        destination.setBudget(destinationBudget);
        destination.addTransaction("Old", income, "Income");

        destination.transferFrom(source);

        assertEquals(0, destination.size());
        assertEquals(0L, destination.getBudget());
        assertEquals(0, source.size());
    }
}
