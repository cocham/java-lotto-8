package lotto.domain;

import lotto.exception.InvalidLottoMoneyException;
import lotto.util.ValidationNumber;

import static lotto.util.InputParser.parseNumber;

public class Money {
    private final int money;
    public static int LOTTO_PRICE = 1000;

    public Money(String inputMoney) {
        int parseMoney = parseNumber(inputMoney);
        this.money = validateLottoMoney(parseMoney);
    }

    private int validateLottoMoney(int money) {
        ValidationNumber.validatePositiveNumber(money);
        if (money % LOTTO_PRICE != 0) {
            throw new InvalidLottoMoneyException(LOTTO_PRICE);
        }
        return money;
    }

    public int calculateLottoCount() {
        return money / LOTTO_PRICE;
    }

    public int getAmount() {
        return money;
    }
}
