import java.util.Objects;

/**
 * Secondary implementation of BudgetTracker.
 *
 * Implements enhanced operations using only kernel methods. All monetary
 * amounts are stored in cents.
 */
public abstract class BudgetTrackerSecondary implements BudgetTracker {

    @Override
    public long getTotalIncome() {
        long total = 0;

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            if (t.getAmount() > 0) {
                total = Math.addExact(total, t.getAmount());
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
                total = Math.subtractExact(total, t.getAmount());
            }
        }

        return total;
    }

    @Override
    public long getBalance() {
        return Math.subtractExact(this.getTotalIncome(),
                this.getTotalExpenses());
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
                total = Math.subtractExact(total, t.getAmount());
            }
        }

        return total;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = this == obj;

        if (!equal && obj instanceof BudgetTracker) {
            BudgetTracker other = (BudgetTracker) obj;

            equal = this.getBudget() == other.getBudget()
                    && this.size() == other.size();

            int index = 0;

            while (equal && index < this.size()) {
                Transaction first = this.getTransaction(index);
                Transaction second = other.getTransaction(index);

                equal = first.getAmount() == second.getAmount()
                        && first.getDescription()
                                .equals(second.getDescription())
                        && first.getCategory().equals(second.getCategory());

                index++;
            }
        }

        return equal;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.getBudget());

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            result = Objects.hash(result, t.getDescription(), t.getAmount(),
                    t.getCategory());
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Budget: ");
        result.append(MoneyFormatter.formatMoney(this.getBudget()));
        result.append("\n");

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            result.append(t.getDescription());
            result.append(": ");
            result.append(MoneyFormatter.formatMoney(t.getAmount()));
            result.append("\n");
        }

        return result.toString();
    }
}
