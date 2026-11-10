import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demonstrates using BudgetTracker to manage a student organization's event
 * finances.
 */
public final class BudgetTrackerOrganizationDemo {

    /**
     * Private constructor.
     */
    private BudgetTrackerOrganizationDemo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args) {

        SimpleWriter out = new SimpleWriter1L();

        BudgetTracker event = new BudgetTracker1();

        /*
         * Event spending limit: $800
         */
        event.setBudget(80000);

        /*
         * Organization funding and expenses
         */
        event.addTransaction("Club Funding", 100000, "Funding");

        event.addTransaction("Venue Rental", -35000, "Venue");

        event.addTransaction("Food and Drinks", -25000, "Catering");

        event.addTransaction("Decorations", -15000, "Supplies");

        event.addTransaction("Equipment Rental", -10000, "Equipment");

        out.println("===== EVENT BUDGET =====");

        out.println("Spending Limit: $" + event.getBudget() / 100.0);

        out.println("Total Funding: $" + event.getTotalIncome() / 100.0);

        out.println("Total Expenses: $" + event.getTotalExpenses() / 100.0);

        out.println("Remaining Funds: $" + event.getBalance() / 100.0);

        out.println();

        if (event.isOverBudget()) {
            out.println("WARNING: Event is over budget!");
        } else {
            out.println("Event is within budget.");
        }

        out.println("========================");

        out.close();
    }
}
