package lotto.domain.lotto.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottosDTO {
    private final int lottoCount;
    private final List<List<Integer>> sortedNumbers;

    public LottosDTO(int lottoCount, List<List<Integer>> sortedNumbers) {
        this.lottoCount = lottoCount;
        this.sortedNumbers = Collections.unmodifiableList(new ArrayList<>(sortedNumbers));
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public List<List<Integer>> getSortedNumbers() {
        return sortedNumbers;
    }
}
