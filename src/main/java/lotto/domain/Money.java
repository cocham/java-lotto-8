package lotto.domain;

import lotto.exception.InvalidLottoMoneyException;
import lotto.exception.OutOfRangeMoneyException;

import static lotto.util.InputParser.parseNumber;

public class Money {
    private final int money;
    public static final int MIN_LOTTO_PRICE = 1000;
    public static final int MAX_LOTTO_PRICE = 100000;

    public Money(String inputMoney) {
        int parseMoney = parseNumber(inputMoney);
        this.money = validateLottoMoney(parseMoney);
    }

    private int validateLottoMoney(int money) {
        if (money < MIN_LOTTO_PRICE || money > MAX_LOTTO_PRICE) {
            throw new OutOfRangeMoneyException(MIN_LOTTO_PRICE, MAX_LOTTO_PRICE);
        }
        if (money % MIN_LOTTO_PRICE != 0) {
            throw new InvalidLottoMoneyException(MIN_LOTTO_PRICE);
        }
        return money;
    }

    public int calculateLottoCount() {
        return money / MIN_LOTTO_PRICE;
    }

    public int getAmount() {
        return money;
    }
}
