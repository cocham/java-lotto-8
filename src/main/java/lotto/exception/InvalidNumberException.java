package lotto.exception;

public class InvalidNumberException extends BaseException {
    public static final String DEFAULT_MESSAGE = "정수를 입력하세요.";

    public InvalidNumberException() {
        super(DEFAULT_MESSAGE);
    }
}
