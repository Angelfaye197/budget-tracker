/**
 * Secondary implementation of BudgetTracker.
 *
 * Implements enhanced operations using only kernel methods. All monetary
 * amounts are stored in cents.
 */
public abstract class BudgetTrackerSecondary implements BudgetTracker {

    /**
     * Returns the total income in cents.
     *
     * @return total of all positive transactions
     * @throws ArithmeticException
     *             if the total exceeds the long range
     */
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

    /**
     * Returns the total expenses in cents as a positive value.
     *
     * @return total expenses
     * @throws ArithmeticException
     *             if the total exceeds the long range
     */
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

    /**
     * Calculates income minus expenses.
     *
     * @return current balance in cents
     * @throws ArithmeticException
     *             if a calculation exceeds the long range
     */
    @Override
    public long getBalance() {
        return Math.subtractExact(this.getTotalIncome(),
                this.getTotalExpenses());
    }

    /**
     * Determines whether expenses exceed the spending limit.
     *
     * @return true if expenses exceed the budget
     * @throws ArithmeticException
     *             if total expenses exceed the long range
     */
    @Override
    public boolean isOverBudget() {
        return this.getTotalExpenses() > this.getBudget();
    }

    /**
     * Calculates total expenses for a specified category.
     *
     * @param category
     *            category to search
     * @return total expenses in the category, in cents
     * @throws ArithmeticException
     *             if the total exceeds the long range
     */
    @Override
    public long getExpensesByCategory(String category) {
        assert category != null : "Violation of: category != null";

        long total = 0;

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            if (t.getAmount() < 0 && t.getCategory().equals(category)) {
                total = Math.subtractExact(total, t.getAmount());
            }
        }

        return total;
    }

    /**
     * Compares this tracker with another object.
     *
     * @param obj
     *            object to compare
     * @return true if both trackers have equal budgets and transactions
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
     * Computes a hash code consistent with equals.
     *
     * @return hash code for this tracker
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
     * Converts a signed amount in cents to a formatted dollar string.
     *
     * @param cents
     *            signed monetary amount in cents
     * @return amount formatted with two decimal places
     */
    private static String formatMoney(long cents) {
        boolean negative = cents < 0;

        long dollars = cents / 100;
        long remainingCents = cents % 100;

        if (dollars < 0) {
            dollars = -dollars;
        }

        if (remainingCents < 0) {
            remainingCents = -remainingCents;
        }

        String centPart = Long.toString(remainingCents);

        if (remainingCents < 10) {
            centPart = "0" + centPart;
        }

        String result = "$" + dollars + "." + centPart;

        if (negative) {
            result = "-" + result;
        }

        return result;
    }

    /**
     * Returns a formatted string containing the budget and transactions.
     *
     * @return formatted tracker information
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Budget: ");
        result.append(formatMoney(this.getBudget()));
        result.append("\n");

        for (int i = 0; i < this.size(); i++) {
            Transaction t = this.getTransaction(i);

            result.append(t.getDescription());
            result.append(": ");
            result.append(formatMoney(t.getAmount()));
            result.append("\n");
        }

        return result.toString();
    }
}
