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
         * Display organization finances.
         */
        out.println("===== EVENT BUDGET =====");
        out.println("Organization: " + event.getName());
        out.println("Remaining Funds: " + formatMoney(event.getBalance()));
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
