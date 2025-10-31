package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.lotto.LottoValidation.validate;

public class WinningLotto {
    private List<Integer> winNumbers;

    public WinningLotto(List<Integer> winNumbers) {
        validate(winNumbers);
        this.winNumbers = new ArrayList<>(winNumbers);
    }

    public List<Integer> getWinNumbers() {
        return winNumbers;
    }
}
