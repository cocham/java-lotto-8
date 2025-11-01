package lotto.domain.lotto;

public class Yield {
    private final double value;
    private static final int PERCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;

    public Yield(long totalPrize, int purchaseAmount) {
        this.value = calculateYield(totalPrize, purchaseAmount);
    }

    private double calculateYield(long totalPrize, int purchaseAmount) {
        double rawYield = ((double) (totalPrize) / purchaseAmount) * PERCENTAGE_MULTIPLIER;
        return Math.round(rawYield * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    public double getValue() {
        return value;
    }
}
