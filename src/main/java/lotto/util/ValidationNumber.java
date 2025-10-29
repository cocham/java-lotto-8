package lotto.util;

import lotto.exception.InvalidNumberException;
import lotto.exception.MinusException;

public class ValidationNumber {
    public static int parseNumber(String inputNumber) {
        try {
            return Integer.parseInt(inputNumber);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
    }

    public static void validatePositiveNumber(int number) {
        if (number < 0) {
            throw new MinusException();
        }
    }
}
