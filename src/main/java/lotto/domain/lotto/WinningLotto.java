package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.domain.lotto.LottoValidation.validate;

public class WinningLotto {
    private final List<Integer> winNumbers;

    public WinningLotto(List<Integer> winNumbers) {
        validate(winNumbers);
        this.winNumbers = Collections.unmodifiableList(new ArrayList<>(winNumbers));
    }

    public List<Integer> getWinNumbers() {
        return winNumbers;
    }

    public LottoRank match(Lotto purchasedLotto, LottoBonusNumber bonusNumber) {
        int matchCount = countMatches(purchasedLotto);
        boolean bonusMatch = purchasedLotto.contains(bonusNumber.getBonusNumber());

        return LottoRank.valueOf(matchCount, bonusMatch);
    }

    private int countMatches(Lotto purchasedLotto) {
        return (int) winNumbers.stream()
                .filter(purchasedLotto::contains)
                .count();
    }
}
