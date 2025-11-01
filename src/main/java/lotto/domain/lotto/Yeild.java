package lotto.domain.lotto;

public class Yeild {
    private final double yeild;
    private static final int PERCENT = 100;
    private static final double DECIMAL_FACTOR = 100.0;

    public Yeild(long totalPrize, int money) {
        this.yeild = ((double) (totalPrize) / money) * PERCENT;
    }

    public double getYeild() {
        return Math.round(yeild * DECIMAL_FACTOR) / DECIMAL_FACTOR;
    }
}
