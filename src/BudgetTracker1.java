import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Concrete implementation of BudgetTracker using an OSU Sequence of
 * transactions.
 *
 * @convention this.transactions != null and this.budgetLimit >= 0 and every
 *             entry in this.transactions is non-null and every entry is a valid
 *             Transaction
 *
 * @correspondence this = (transactions = this.transactions, budget =
 *                 this.budgetLimit)
 */
public class BudgetTracker1 extends BudgetTrackerSecondary {

    /*
     * Private representation.
     */
    private Sequence<Transaction> transactions;
    private long budgetLimit;

    /**
     * Initializes the representation to an empty sequence and a spending limit
     * of zero.
     */
    private void createNewRep() {
        this.transactions = new Sequence1L<>();
        this.budgetLimit = 0;
    }

    /**
     * Creates a new empty BudgetTracker with a budget of zero.
     */
    public BudgetTracker1() {
        this.createNewRep();
    }

    /*
     * Standard methods.
     */

    /**
     * Creates a new instance with an empty initial state.
     *
     * @return a new empty BudgetTracker
     */
    @Override
    public final BudgetTracker newInstance() {
        return new BudgetTracker1();
    }

    /**
     * Resets this tracker to its initial empty state.
     */
    @Override
    public final void clear() {
        this.createNewRep();
    }

    /**
     * Transfers the representation of source to this tracker. The source is
     * reset to its initial state.
     *
     * @param source
     *            tracker whose representation is transferred
     * @requires source != null and source != this and source has the same
     *           dynamic type as this
     */
    @Override
    public final void transferFrom(BudgetTracker source) {
        assert source != null : "Violation of: source != null";
        assert source != this : "Violation of: source != this";
        assert source.getClass() == this
                .getClass() : "Violation of: same dynamic type";

        BudgetTracker1 localSource = (BudgetTracker1) source;

        this.transactions = localSource.transactions;
        this.budgetLimit = localSource.budgetLimit;

        localSource.createNewRep();
    }

    /*
     * Kernel methods.
     */

    /**
     * Appends a transaction to the end of the tracker.
     *
     * @param description
     *            transaction description
     * @param amount
     *            signed amount in cents
     * @param category
     *            transaction category
     */
    @Override
    public final void addTransaction(String description, long amount,
            String category) {
        Transaction t = new Transaction(description, amount, category);

        this.transactions.add(this.transactions.length(), t);
    }

    /**
     * Removes and returns the transaction at the given index.
     *
     * @param index
     *            index of the transaction to remove
     * @return removed transaction
     * @requires 0 <= index and index < this.size()
     */
    @Override
    public final Transaction removeTransaction(int index) {
        assert index >= 0 && index < this.transactions
                .length() : "Violation of: 0 <= index < size";

        return this.transactions.remove(index);
    }

    /**
     * Returns the transaction at the given index without removing it.
     *
     * @param index
     *            index of the requested transaction
     * @return transaction at index
     * @requires 0 <= index and index < this.size()
     */
    @Override
    public final Transaction getTransaction(int index) {
        assert index >= 0 && index < this.transactions
                .length() : "Violation of: 0 <= index < size";

        return this.transactions.entry(index);
    }

    /**
     * Returns the number of transactions in this tracker.
     *
     * @return number of transactions
     */
    @Override
    public final int size() {
        return this.transactions.length();
    }

    /**
     * Sets the spending limit in cents.
     *
     * @param limit
     *            new spending limit
     * @requires limit >= 0
     */

    @Override
    public final void setBudget(long limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }

        this.budgetLimit = limit;
    }

    /**
     * Returns the current spending limit in cents.
     *
     * @return spending limit
     */
    @Override
    public final long getBudget() {
        return this.budgetLimit;
    }
}
