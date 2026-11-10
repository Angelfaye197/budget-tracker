
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Concrete implementation of BudgetTracker.
 *
 * @convention this.transactions != null and this.budgetLimit >= 0
 *
 * @correspondence The abstract transactions are represented by
 *                 this.transactions in insertion order. The abstract budget
 *                 limit is this.budgetLimit.
 */
public class BudgetTracker1 extends BudgetTrackerSecondary {

    /*
     * Private representation
     */
    private Sequence<Transaction> transactions;
    private long budgetLimit;

    /*
     * Constructor
     */
    public BudgetTracker1() {
        this.createNewRep();
    }

    /**
     * Initializes an empty tracker.
     */
    private void createNewRep() {
        this.transactions = new Sequence1L<>();
        this.budgetLimit = 0;
    }

    /*
     * Standard methods
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

        if (source == null || source == this
                || source.getClass() != this.getClass()) {
            throw new IllegalArgumentException("Invalid source");
        }

        BudgetTracker1 localSource = (BudgetTracker1) source;

        this.transactions = localSource.transactions;
        this.budgetLimit = localSource.budgetLimit;

        localSource.createNewRep();
    }

    /*
     * Kernel methods
     */

    @Override
    public final void addTransaction(String description, long amount,
            String category) {

        Transaction t = new Transaction(description, amount, category);

        this.transactions.add(this.transactions.length(), t);
    }

    @Override
    public final Transaction removeTransaction(int index) {

        return this.transactions.remove(index);
    }

    @Override
    public final Transaction getTransaction(int index) {

        return this.transactions.entry(index);
    }

    @Override
    public final int size() {
        return this.transactions.length();
    }

    @Override
    public final void setBudget(long limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }

        this.budgetLimit = limit;
    }

    @Override
    public final long getBudget() {
        return this.budgetLimit;
    }
}
