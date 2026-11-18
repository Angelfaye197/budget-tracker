/**
 * Utility for formatting monetary amounts stored in cents.
 */
public final class MoneyFormatter {

    /** Number of cents in one dollar. */
    private static final long CENTS_PER_DOLLAR = 100;

    /** Smallest two-digit amount of cents. */
    private static final long TEN_CENTS = 10;

    /** Prevents instantiation. */
    private MoneyFormatter() {
    }

    /**
     * Formats a signed amount of cents in dollars.
     *
     * @param cents
     *            signed amount in cents
     * @return formatted monetary amount
     * @ensures formatMoney = the dollar representation of cents
     */
    public static String formatMoney(long cents) {
        boolean negative = cents < 0;
        long dollars = cents / CENTS_PER_DOLLAR;
        long remainder = cents % CENTS_PER_DOLLAR;

        String dollarPart = Long.toString(dollars);

        if (dollarPart.startsWith("-")) {
            dollarPart = dollarPart.substring(1);
        }

        if (remainder < 0) {
            remainder = -remainder;
        }

        String centPart = Long.toString(remainder);

        if (remainder < TEN_CENTS) {
            centPart = "0" + centPart;
        }

        String sign = "";

        if (negative) {
            sign = "-";
        }

        return sign + "$" + dollarPart + "." + centPart;
    }
}
