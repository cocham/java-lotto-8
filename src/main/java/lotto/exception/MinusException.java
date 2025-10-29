package lotto.exception;

public class MinusException extends IllegalArgumentException{
    public static final String MINUS_MESSAGE = "[ERROR]: 음수를 입력했습니다.";

    public MinusException() {
        super(MINUS_MESSAGE);
    }
}
