package lotto.exception;

public class NumberDuplicateException extends BaseException {
    public static final String DEFAULT_MESSAGE = "로또 번호는 중복 될 수 없습니다.";
    public static final String CUSTOM_MESSAGE = "(%d)가 이미 존재합니다.";

    public NumberDuplicateException(int number) {
        super(DEFAULT_MESSAGE + String.format(CUSTOM_MESSAGE, number));
    }
}