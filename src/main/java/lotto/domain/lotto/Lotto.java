package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoValidation.validate(numbers);
        this.numbers = Collections.unmodifiableList(new ArrayList<>(numbers));
    }

    public List<Integer> getSortedNumbers() {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        return sorted;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }
}

