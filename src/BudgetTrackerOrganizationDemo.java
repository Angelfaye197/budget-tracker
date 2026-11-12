import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demonstrates using BudgetTracker through a StudentOrganization class to
 * manage event finances.
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

        /*
         * Create an engineering club with an $800 event budget.
         */
        StudentOrganization event = new StudentOrganization("Engineering Club",
                80000);

        /*
         * Record organization funding.
         */
        event.receiveFunding("Club Funding", 100000);

        /*
         * Record event expenses.
         */
        event.recordExpense("Venue Rental", 35000, "Venue");
        event.recordExpense("Food and Drinks", 25000, "Catering");
        event.recordExpense("Decorations", 15000, "Supplies");
        event.recordExpense("Equipment Rental", 10000, "Equipment");

        /*
         * Display the organization's financial information.
         */
        out.println("===== EVENT BUDGET =====");

        out.println("Organization: " + event.getName());

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
