/**
 * Secondary implementation of BudgetTracker.
 *
 * Implements enhanced operations using only the kernel methods.
 */
public abstract class BudgetTrackerSecondary implements BudgetTracker {

    @Override
    public long getTotalIncome() {
        long total = 0;

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            if (t.getAmount() > 0) {
                total += t.getAmount();
            }
        }

        return total;
    }

    @Override
    public long getTotalExpenses() {
        long total = 0;

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            if (t.getAmount() < 0) {
                total -= t.getAmount();
            }
        }

        return total;
    }

    @Override
    public long getBalance() {
        return this.getTotalIncome() - this.getTotalExpenses();
    }

    @Override
    public boolean isOverBudget() {
        return this.getTotalExpenses() > this.getBudget();
    }

    @Override
    public long getExpensesByCategory(String category) {

        long total = 0;

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            if (t.getAmount() < 0 && t.getCategory().equals(category)) {
                total -= t.getAmount();
            }
        }

        return total;
    }

    /**
     * Compares this tracker with another object.
     *
     * @param obj
     *            object to compare
     * @return true if the trackers have equal values
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BudgetTracker)) {
            return false;
        }

        BudgetTracker other = (BudgetTracker) obj;

        if (this.getBudget() != other.getBudget()
                || this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            Transaction a = this.getTransaction(i);
            Transaction b = other.getTransaction(i);

            if (a.getAmount() != b.getAmount()
                    || !a.getDescription().equals(b.getDescription())
                    || !a.getCategory().equals(b.getCategory())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Computes a hash code for this tracker.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {

        int result = Long.hashCode(this.getBudget());

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            result = 31 * result + t.getDescription().hashCode();

            result = 31 * result + Long.hashCode(t.getAmount());

            result = 31 * result + t.getCategory().hashCode();
        }

        return result;
    }

    /**
     * Returns a string representation of the tracker.
     *
     * @return formatted tracker information
     */
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        result.append("Budget: $");
        result.append(this.getBudget() / 100.0);
        result.append("\n");

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            result.append(t.getDescription());
            result.append(": $");
            result.append(t.getAmount() / 100.0);
            result.append("\n");
        }

        return result.toString();
    }
}
