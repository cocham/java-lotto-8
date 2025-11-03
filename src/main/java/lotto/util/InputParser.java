package lotto.util;

import lotto.exception.InvalidNumberException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    public static final String DELIMITER = ",";

    public static List<String> tokenizeNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static List<Integer> parseNumbers(List<String> inputNumbers) {
        return inputNumbers.stream()
                .map(InputParser::parseNumber)
                .toList();
    }

    public static int parseNumber(String inputNumber) {
        try {
            return Integer.parseInt(inputNumber);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
    }
}
