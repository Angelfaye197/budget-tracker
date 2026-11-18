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

        BudgetTracker tracker = new BudgetTracker1();

        tracker.setBudget(50000);

        tracker.addTransaction("Paycheck", 150000, "Income");
        tracker.addTransaction("Groceries", -12000, "Food");
        tracker.addTransaction("Gas", -4500, "Transportation");
        tracker.addTransaction("Shopping", -8500, "Personal");

        out.println("===== BUDGET TRACKER =====");
        out.println("Monthly Budget: "
                + MoneyFormatter.formatMoney(tracker.getBudget()));
        out.println();

        out.println("Transactions:");

        for (int i = 0; i < tracker.size(); i++) {
            Transaction t = tracker.getTransaction(i);

            out.println(t.getDescription() + ": "
                    + MoneyFormatter.formatMoney(t.getAmount()));
        }

        out.println();
        out.println("Total Income: "
                + MoneyFormatter.formatMoney(tracker.getTotalIncome()));
        out.println("Total Expenses: "
                + MoneyFormatter.formatMoney(tracker.getTotalExpenses()));
        out.println("Remaining Balance: "
                + MoneyFormatter.formatMoney(tracker.getBalance()));
        out.println("Food Expenses: " + MoneyFormatter
                .formatMoney(tracker.getExpensesByCategory("Food")));
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
