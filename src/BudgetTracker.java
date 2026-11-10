
/**
 * Enhanced interface for the BudgetTracker component.
 *
 * Includes operations for calculating income, expenses, balance, and spending
 * limits.
 */
public interface BudgetTracker extends BudgetTrackerKernel {

    /**
     * Calculates the total income.
     *
     * @return total income in cents
     * @ensures getTotalIncome >= 0
     */
    long getTotalIncome();

    /**
     * Calculates the total expenses.
     *
     * @return total expenses in cents
     * @ensures getTotalExpenses >= 0
     */
    long getTotalExpenses();

    /**
     * Calculates the remaining balance.
     *
     * @return income minus expenses in cents
     */
    long getBalance();

    /**
     * Determines whether expenses exceed the spending limit.
     *
     * @return true if over budget, false otherwise
     */
    boolean isOverBudget();

    /**
     * Calculates expenses for a category.
     *
     * @param category
     *            category to search
     * @return total expenses for the category
     * @requires category != null
     */
    long getExpensesByCategory(String category);
}
