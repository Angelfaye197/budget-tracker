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
     * Formats a monetary amount in cents.
     *
     * @param cents
     *            amount in cents
     * @return formatted dollar amount
     */
    private static String formatMoney(long cents) {
        boolean negative = cents < 0;
        long dollars = cents / 100;
        long remainder = cents % 100;

        String dollarPart = Long.toString(dollars);
        if (negative && dollars == 0) {
            dollarPart = "-0";
        }

        if (remainder < 0) {
            remainder = -remainder;
        }

        String centPart = Long.toString(remainder);
        if (remainder < 10) {
            centPart = "0" + centPart;
        }

        if (negative) {
            if (dollarPart.startsWith("-")) {
                dollarPart = dollarPart.substring(1);
            }
            return "-$" + dollarPart + "." + centPart;
        }

        return "$" + dollarPart + "." + centPart;
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
         * Create a budget tracker.
         */
        BudgetTracker tracker = new BudgetTracker1();

        /*
         * Set monthly spending budget to $500.
         */
        tracker.setBudget(50000);

        /*
         * Add income and expenses.
         */
        tracker.addTransaction("Paycheck", 150000, "Income");
        tracker.addTransaction("Groceries", -12000, "Food");
        tracker.addTransaction("Gas", -4500, "Transportation");
        tracker.addTransaction("Shopping", -8500, "Personal");

        /*
         * Display budget summary.
         */
        out.println("===== BUDGET TRACKER =====");
        out.println("Monthly Budget: " + formatMoney(tracker.getBudget()));
        out.println();

        out.println("Transactions:");

        for (int i = 0; i < tracker.size(); i++) {
            Transaction t = tracker.getTransaction(i);

            out.println(t.getDescription() + ": " + formatMoney(t.getAmount()));
        }

        out.println();
        out.println("Total Income: " + formatMoney(tracker.getTotalIncome()));
        out.println(
                "Total Expenses: " + formatMoney(tracker.getTotalExpenses()));
        out.println("Remaining Balance: " + formatMoney(tracker.getBalance()));
        out.println("Food Expenses: "
                + formatMoney(tracker.getExpensesByCategory("Food")));
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
