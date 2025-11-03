package lotto.exception;

public class InvalidLottoMoneyException extends BaseException{
    public static final String DEFAULT_MESSAGE = "%d 배수의 가격만 로또를 구매할 수 있습니다.";

    public InvalidLottoMoneyException(int money) {
        super(String.format(DEFAULT_MESSAGE, money));
    }
}
