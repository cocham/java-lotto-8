package lotto.exception;

public class NullInputException extends BaseException {
    public static final String DEFAULT_MESSAGE = "입력이 없습니다. (null)";

    public NullInputException() {
        super(DEFAULT_MESSAGE);
    }
}
