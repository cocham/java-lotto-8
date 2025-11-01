package lotto;

import lotto.domain.lotto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class YeildTest {
    private WinningLotto winningLotto;
    private LottoBonusNumber bonusNumber;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(List.of(1,2,3,4,5,6));
        bonusNumber = new LottoBonusNumber(7, winningLotto);
    }

    @DisplayName("총상금과 구매 금액으로 수익률을 정확히 계산한다.")
    @Test
    void testYeild() {
        int money = 9000;
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),    // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),    // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),    // 3등
                new Lotto(List.of(1, 2, 3, 4, 5, 9)),    // 3등
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),    // 4등
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),   // 5등
                new Lotto(List.of(1, 2, 3, 11, 12, 13)), // 5등
                new Lotto(List.of(1, 2, 3, 14, 15, 16)), // 5등
                new Lotto(List.of(8, 9, 10, 11, 12, 13)) // 꽝
        ));
        LottoResult lottoResult = new LottoResult(lottos, winningLotto, bonusNumber);
        long prize = lottoResult.getTotalPrize();

        Yeild yeild = new Yeild(prize, money);

        assertThat(yeild.getYeild()).isEqualTo(22_589_611.11);
    }

}
