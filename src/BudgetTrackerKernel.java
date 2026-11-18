import components.standard.Standard;

/**
 * Kernel interface for a Budget Tracker.
 *
 * A BudgetTracker maintains an ordered sequence of transactions and a
 * nonnegative spending limit. All monetary values are stored in cents. Positive
 * transaction amounts represent income, while negative amounts represent
 * expenses.
 *
 * @mathmodel type BudgetTracker is modeled by (transactions: string of
 *            Transaction, budget: integer)
 *
 * @initially transactions = empty_string and budget = 0
 */
public interface BudgetTrackerKernel extends Standard<BudgetTracker> {

    /**
     * Appends a transaction to the end of the tracker.
     *
     * @param description
     *            description of the transaction
     * @param amount
     *            signed amount in cents
     * @param category
     *            transaction category
     * @requires description is not blank and category is not blank and amount
     *           != 0
     * @ensures the new transaction is appended to the end of the transaction
     *          sequence and the budget is unchanged
     */
    void addTransaction(String description, long amount, String category);

    /**
     * Removes and returns the transaction at the given index.
     *
     * @param index
     *            position of the transaction
     * @return the removed transaction
     * @requires 0 <= index and index < size()
     * @ensures the transaction at index is removed, the remaining transactions
     *          retain their order, and the budget is unchanged
     */
    Transaction removeTransaction(int index);

    /**
     * Returns the transaction at the given index without removing it.
     *
     * @param index
     *            position of the transaction
     * @return the transaction at index
     * @requires 0 <= index and index < size()
     * @aliases reference returned by getTransaction aliases the transaction
     *          stored at index
     * @ensures the returned transaction is the transaction at index and this
     *          BudgetTracker is unchanged
     */
    Transaction getTransaction(int index);

    /**
     * Reports the number of transactions.
     *
     * @return the number of transactions
     * @ensures size = number of transactions in the sequence and this
     *          BudgetTracker is unchanged
     */
    int size();

    /**
     * Updates the spending limit.
     *
     * @param limit
     *            new spending limit in cents
     * @requires limit >= 0
     * @ensures the budget equals limit and the transaction sequence is
     *          unchanged
     */
    void setBudget(long limit);

    /**
     * Reports the current spending limit.
     *
     * @return the spending limit in cents
     * @ensures the returned value equals the current budget and this
     *          BudgetTracker is unchanged
     */
    long getBudget();
}
