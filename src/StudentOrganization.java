/**
 * Represents a student organization that uses a BudgetTracker to manage its
 * event finances.
 */
public final class StudentOrganization {

    /** Name of the organization. */
    private final String name;

    /** Component used to store funding, expenses, and the spending limit. */
    private final BudgetTracker finances;

    /**
     * Creates a student organization with an event budget.
     *
     * @param name
     *            organization name
     * @param budget
     *            spending limit in cents
     * @requires name is not blank and budget >= 0
     */
    public StudentOrganization(String name, long budget) {
        assert !name.trim().isEmpty() : "Violation of: valid name";
        assert budget >= 0 : "Violation of: budget >= 0";

        this.name = name;
        this.finances = new BudgetTracker1();
        this.finances.setBudget(budget);
    }

    /**
     * Records funding received by the organization.
     *
     * @param description
     *            funding description
     * @param amount
     *            positive amount in cents
     * @requires description is not blank and amount > 0
     */
    public void receiveFunding(String description, long amount) {
        assert !description.trim()
                .isEmpty() : "Violation of: valid description";
        assert amount > 0 : "Violation of: amount > 0";

        this.finances.addTransaction(description, amount, "Funding");
    }

    /**
     * Records an organization expense.
     *
     * @param description
     *            expense description
     * @param amount
     *            positive amount in cents
     * @param category
     *            expense category
     * @requires description is not blank and category is not blank and amount >
     *           0
     */
    public void recordExpense(String description, long amount,
            String category) {
        assert !description.trim()
                .isEmpty() : "Violation of: valid description";
        assert !category.trim().isEmpty() : "Violation of: valid category";
        assert amount > 0 : "Violation of: amount > 0";

        this.finances.addTransaction(description, -amount, category);
    }

    /**
     * Reports whether the organization exceeded its budget.
     *
     * @return true if spending exceeds the budget
     */
    public boolean isOverBudget() {
        return this.finances.isOverBudget();
    }

    /**
     * Returns the organization's financial balance in cents.
     *
     * @return total funding minus expenses
     */
    public long getBalance() {
        return this.finances.getBalance();
    }

    /**
     * Returns the organization's name.
     *
     * @return organization name
     */
    public String getName() {
        return this.name;
    }
}
