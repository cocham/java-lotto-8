package lotto.exception;

public class OutOfRangeMoneyException extends BaseException{
    public static final String DEFAULT_MESSAGE = "로또 한 장 당 가격은 %d원입니다. 최대 구매 가격은 %d원입니다.";

    public OutOfRangeMoneyException(int minMoney, int maxMoney) {
        super(String.format(DEFAULT_MESSAGE, minMoney, maxMoney));
    }
}
