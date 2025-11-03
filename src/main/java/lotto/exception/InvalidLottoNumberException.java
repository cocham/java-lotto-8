package lotto.exception;

public class InvalidLottoNumberException extends BaseException {
    public static final String DEFAULT_MESSAGE = "로또 번호는 %d 이상 %d이하만 가능합니다. %d를 입력하셨습니다.";

    public InvalidLottoNumberException(int min, int max, int num) {
        super(String.format(DEFAULT_MESSAGE, min, max, num));
    }

}
