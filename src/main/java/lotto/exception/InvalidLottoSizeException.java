package lotto.exception;

public class InvalidLottoSizeException extends BaseException{
    public static final String DEFAULT_MESSAGE = "로또 번호들은 %d개여야 합니다.";

    public InvalidLottoSizeException(int count) {
        super(String.format(DEFAULT_MESSAGE, count));
    }

}
