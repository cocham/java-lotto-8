package lotto.exception;

public class InvalidNumberException extends IllegalArgumentException {
    public static final String DEFAULT_MESSAGE = "[ERROR]: 정수를 입력하세요.";

    public InvalidNumberException() {
        super(DEFAULT_MESSAGE);
    }
}
