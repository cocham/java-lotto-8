package lotto.util;

import lotto.exception.MinusException;

public class ValidationNumber {
    public static void validatePositiveNumber(int number) {
        if (number < 0) {
            throw new MinusException();
        }
    }
}
