
import components.standard.Standard;

/**
 * Kernel interface for a Budget Tracker.
 *
 * @mathmodel type BudgetTracker is modeled by a sequence of transactions and a
 *            nonnegative budget limit.
 */
public interface BudgetTrackerKernel extends Standard<BudgetTracker> {

    /**
     * Adds a transaction to the tracker.
     *
     * @param description
     *            description of the transaction
     * @param amount
     *            signed amount in cents
     * @param category
     *            transaction category
     * @requires description and category are non-null and nonempty, and amount
     *           != 0
     */
    void addTransaction(String description, long amount, String category);

    /**
     * Removes a transaction at the given index.
     *
     * @param index
     *            position of the transaction
     * @return removed transaction
     * @requires 0 <= index < size()
     */
    Transaction removeTransaction(int index);

    /**
     * Returns the transaction at the given index.
     *
     * @param index
     *            position of the transaction
     * @return transaction at index
     * @requires 0 <= index < size()
     */
    Transaction getTransaction(int index);

    /**
     * Returns the number of transactions.
     *
     * @return number of transactions
     */
    int size();

    /**
     * Sets the spending limit in cents.
     *
     * @param limit
     *            new spending limit
     * @requires limit >= 0
     */
    void setBudget(long limit);

    /**
     * Returns the spending limit in cents.
     *
     * @return current spending limit
     */
    long getBudget();
}
