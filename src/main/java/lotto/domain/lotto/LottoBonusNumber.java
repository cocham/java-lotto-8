package lotto.domain.lotto;

import static lotto.domain.lotto.LottoValidation.validateBonusNumber;

public class LottoBonusNumber {
    private int bonusNumber;

    public LottoBonusNumber(int bonusNumber, WinningLotto winningLotto) {
        validateBonusNumber(bonusNumber, winningLotto);
        this.bonusNumber = bonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
