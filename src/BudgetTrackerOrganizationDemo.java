import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demonstrates using BudgetTracker through a StudentOrganization class to
 * manage event finances.
 */
public final class BudgetTrackerOrganizationDemo {

    /**
     * Private constructor to prevent instantiation.
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
        final long eventBudget = 80000;
        final long clubFunding = 100000;
        final long venueCost = 35000;
        final long cateringCost = 25000;
        final long suppliesCost = 15000;
        final long equipmentCost = 10000;

        SimpleWriter out = new SimpleWriter1L();

        StudentOrganization event = new StudentOrganization("Engineering Club",
                eventBudget);

        event.receiveFunding("Club Funding", clubFunding);

        event.recordExpense("Venue Rental", venueCost, "Venue");
        event.recordExpense("Food and Drinks", cateringCost, "Catering");
        event.recordExpense("Decorations", suppliesCost, "Supplies");
        event.recordExpense("Equipment Rental", equipmentCost, "Equipment");

        out.println("===== EVENT BUDGET =====");
        out.println("Organization: " + event.getName());
        out.println("Remaining Funds: "
                + MoneyFormatter.formatMoney(event.getBalance()));
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
