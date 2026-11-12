/**
 * Represents a student organization that uses a BudgetTracker to manage its
 * event finances.
 */
public final class StudentOrganization {

    private final String name;
    private final BudgetTracker finances;

    /**
     * Creates a student organization with an event budget.
     *
     * @param name
     *            organization name
     * @param budget
     *            spending limit in cents
     */
    public StudentOrganization(String name, long budget) {
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
     */
    public void receiveFunding(String description, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Funding must be positive");
        }
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
     */
    public void recordExpense(String description, long amount,
            String category) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Expense must be positive");
        }
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
