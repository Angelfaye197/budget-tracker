/**
 * Represents a single financial transaction. Positive amounts are income.
 * Negative amounts are expenses. All amounts are stored in cents.
 */
public final class Transaction {

    /** Human-readable transaction description. */
    private final String description;

    /** Signed amount in cents: positive for income, negative for expenses. */
    private final long amount;

    /** Category used to group expenses. */
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
     * @requires description is not blank and category is not blank and amount
     *           != 0
     */
    public Transaction(String description, long amount, String category) {

        assert !description.trim()
                .isEmpty() : "Violation of: valid description";
        assert !category.trim().isEmpty() : "Violation of: valid category";
        assert amount != 0 : "Violation of: amount != 0";

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
