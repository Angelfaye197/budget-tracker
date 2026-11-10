
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demonstrates the BudgetTracker component for managing personal finances.
 */
public final class BudgetTrackerDemo {

    /**
     * Private constructor.
     */
    private BudgetTrackerDemo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args) {

        SimpleWriter out = new SimpleWriter1L();

        /*
         * Create a Budget Tracker
         */
        BudgetTracker tracker = new BudgetTracker1();

        /*
         * Set monthly budget to $500
         */
        tracker.setBudget(50000);

        /*
         * Add income and expenses
         */
        tracker.addTransaction("Paycheck", 150000, "Income");

        tracker.addTransaction("Groceries", -12000, "Food");

        tracker.addTransaction("Gas", -4500, "Transportation");

        tracker.addTransaction("Shopping", -8500, "Personal");

        /*
         * Display budget summary
         */
        out.println("===== BUDGET TRACKER =====");

        out.println("Monthly Budget: $" + tracker.getBudget() / 100.0);

        out.println();

        out.println("Transactions:");

        for (int i = 0; i < tracker.size(); i++) {
            Transaction t = tracker.getTransaction(i);

            out.println(t.getDescription() + ": $" + t.getAmount() / 100.0);
        }

        out.println();

        out.println("Total Income: $" + tracker.getTotalIncome() / 100.0);

        out.println("Total Expenses: $" + tracker.getTotalExpenses() / 100.0);

        out.println("Remaining Balance: $" + tracker.getBalance() / 100.0);

        out.println("Food Expenses: $"
                + tracker.getExpensesByCategory("Food") / 100.0);

        out.println();

        if (tracker.isOverBudget()) {
            out.println("WARNING: Over budget!");
        } else {
            out.println("You are within your budget!");
        }

        out.println("==========================");

        out.close();
    }
}
