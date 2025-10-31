package lotto.domain.lotto;

import lotto.exception.InvalidLottoNumberException;
import lotto.exception.InvalidLottoSizeException;
import lotto.exception.NumberDuplicateException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoValidation {
    private static final int BASE_SIZE = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    public static void validate(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateLottoNumber(numbers);
        validateDuplicates(numbers);
    }

    public static void validate(int number, WinningLotto winningLotto) {
        validateLottoNumber(number);
        validateBonusNumber(number, winningLotto);
    }

    private static void validateBonusNumber(int bonusNumber, WinningLotto winningLotto) {
        if (winningLotto.getWinNumbers().contains(bonusNumber)) {
            throw new NumberDuplicateException(bonusNumber);
        }
    }

    private static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != BASE_SIZE) {
            throw new InvalidLottoSizeException(BASE_SIZE);
        }
    }

    private static void validateDuplicates(List<Integer> numbers) {
        Set<Integer> checkNumbers = new HashSet<>(BASE_SIZE);
        for (Integer number : numbers) {
            if(!checkNumbers.add(number)) {
                throw new NumberDuplicateException(number);
            };
        }
    }

    private static void validateLottoNumber(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
                throw new InvalidLottoNumberException(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, number);
            }
        }
    }
}
