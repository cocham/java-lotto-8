package lotto.domain;

import lotto.exception.InvalidLottoMoneyException;
import lotto.util.ValidationNumber;

public class Money {
    private int money;
    public static int LOTTO_PRICE = 1000;

    public Money(String inputMoney) {
        int parseMoney = ValidationNumber.parseNumber(inputMoney);
        this.money = validateLottoMoney(parseMoney);
    }

    private int validateLottoMoney(int money) {
        ValidationNumber.validatePositiveNumber(money);
        if (money % LOTTO_PRICE != 0) {
            throw new InvalidLottoMoneyException(LOTTO_PRICE);
        }
        return money;
    }

}
