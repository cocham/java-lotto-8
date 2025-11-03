package lotto.domain;

import lotto.domain.lotto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoResult 클래스")
public class LottoResultTest {
    private WinningLotto winningLotto;
    private LottoBonusNumber bonusNumber;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(List.of(1,2,3,4,5,6));
        bonusNumber = new LottoBonusNumber(7, winningLotto);
    }

    @DisplayName("각 등수별 개수를 정확히 집계한다.")
    @Test
    void 둥수별_개수_집계() {
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

        assertThat(lottoResult.getCountByRank(LottoRank.FIRST)).isEqualTo(1);
        assertThat(lottoResult.getCountByRank(LottoRank.SECOND)).isEqualTo(1);
        assertThat(lottoResult.getCountByRank(LottoRank.THIRD)).isEqualTo(2);
        assertThat(lottoResult.getCountByRank(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(lottoResult.getCountByRank(LottoRank.FIFTH)).isEqualTo(3);
        assertThat(lottoResult.getCountByRank(LottoRank.NONE)).isEqualTo(1);
    }

    @Test
    @DisplayName("모두 낙첨이면 모든 등수의 개수는 0이다.")
    void 모두_낙첨() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(8, 9, 10, 11, 12, 13)),
                new Lotto(List.of(14, 15, 16, 17, 18, 19))
        ));

        LottoResult lottoResult = new LottoResult(lottos, winningLotto, bonusNumber);

        assertThat(lottoResult.getCountByRank(LottoRank.FIRST)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(LottoRank.SECOND)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(LottoRank.THIRD)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(LottoRank.FIFTH)).isEqualTo(0);
        assertThat(lottoResult.getCountByRank(LottoRank.NONE)).isEqualTo(2);
    }

    @Nested
    @DisplayName("당첨 금액 계산")
    class PrizeTest {
        private WinningLotto winningLotto;
        private LottoBonusNumber bonusNumber;

        @BeforeEach
        void setUp() {
            winningLotto = new WinningLotto(List.of(1,2,3,4,5,6));
            bonusNumber = new LottoBonusNumber(7, winningLotto);
        }

        @Test
        @DisplayName("총 당첨 금액을 계산한다.")
        void 총_당첨금() {
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

            assertThat(lottoResult.getTotalPrize()).isEqualTo(2_033_065_000L);
        }

        @DisplayName("당첨되지 않으면 총 당첨 금액은 0원이다.")
        @Test
        void 낙첨_총_당첨금() {
            Lottos lottos = new Lottos(List.of(
                    new Lotto(List.of(8, 9, 10, 11, 12, 13)),
                    new Lotto(List.of(1, 9, 10, 11, 12, 13))
            ));

            LottoResult lottoResult = new LottoResult(lottos, winningLotto, bonusNumber);

            assertThat(lottoResult.getTotalPrize()).isEqualTo(0);
        }
    }

}

