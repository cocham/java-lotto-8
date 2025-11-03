package lotto.exception;

public class MinusException extends BaseException{
    public static final String MINUS_MESSAGE = "음수를 입력했습니다.";

    public MinusException() {
        super(MINUS_MESSAGE);
    }
}
