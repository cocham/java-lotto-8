package lotto.exception;

public class InvalidLottoMoneyException extends BaseException{
    public static final String DEFAULT_MESSAGE = "로또 한 장 당 가격은 %d원입니다. %d원으로 나누어 떨어져야 구매하실 수 있습니다.";

    public InvalidLottoMoneyException(int money) {
        super(String.format(DEFAULT_MESSAGE, money, money));
    }
}
