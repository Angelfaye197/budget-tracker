import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Concrete implementation of BudgetTracker using an OSU Sequence of
 * transactions.
 *
 * @convention this.budgetLimit >= 0 and every entry in this.transactions is a
 *             valid Transaction
 *
 * @correspondence this = (transactions = this.transactions, budget =
 *                 this.budgetLimit)
 */
public class BudgetTracker1 extends BudgetTrackerSecondary {

    /*
     * Private representation.
     */

    /** Ordered transactions maintained by this tracker. */
    private Sequence<Transaction> transactions;

    /** Spending limit in cents. */
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

    @Override
    public final BudgetTracker newInstance() {
        return new BudgetTracker1();
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(BudgetTracker source) {
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

    @Override
    public final void addTransaction(String description, long amount,
            String category) {

        Transaction t = new Transaction(description, amount, category);

        this.transactions.add(this.transactions.length(), t);
    }

    @Override
    public final Transaction removeTransaction(int index) {
        assert index >= 0 && index < this.transactions
                .length() : "Violation of: 0 <= index < size";

        return this.transactions.remove(index);
    }

    @Override
    public final Transaction getTransaction(int index) {
        assert index >= 0 && index < this.transactions
                .length() : "Violation of: 0 <= index < size";

        return this.transactions.entry(index);
    }

    @Override
    public final int size() {
        return this.transactions.length();
    }

    @Override
    public final void setBudget(long limit) {
        assert limit >= 0 : "Violation of: limit >= 0";

        this.budgetLimit = limit;
    }

    @Override
    public final long getBudget() {
        return this.budgetLimit;
    }
}
