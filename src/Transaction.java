
/**
 * Represents a single financial transaction. Positive amounts are income.
 * Negative amounts are expenses. All amounts are stored in cents.
 */
public final class Transaction {

    private final String description;
    private final long amount;
    private final String category;

    /**
     * Creates a new transaction.
     *
     * @param description
     *            description of the transaction
     * @param amount
     *            signed amount in cents
     * @param category
     *            transaction category
     */
    public Transaction(String description, long amount, String category) {

        if (description == null || description.trim().isEmpty()
                || category == null || category.trim().isEmpty()
                || amount == 0) {
            throw new IllegalArgumentException("Invalid transaction");
        }

        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    /**
     * Returns the description.
     *
     * @return transaction description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the signed amount in cents.
     *
     * @return transaction amount
     */
    public long getAmount() {
        return this.amount;
    }

    /**
     * Returns the category.
     *
     * @return transaction category
     */
    public String getCategory() {
        return this.category;
    }
}
