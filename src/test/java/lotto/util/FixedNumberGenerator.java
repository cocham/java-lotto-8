package lotto.util;

import java.util.ArrayList;
import java.util.List;


public class FixedNumberGenerator implements NumberGenerator {
    private final List<List<Integer>> numbers;
    private int index = 0;

    public FixedNumberGenerator(List<List<Integer>> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    @Override
    public List<Integer> generate() {
        if (index >= numbers.size()) {
            throw new IllegalStateException("더 이상 생성할 로또 번호가 없습니다.");
        }
        return numbers.get(index++);
    }
}