package lotto;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoBonusNumber;
import lotto.domain.lotto.LottoRank;
import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class WinningLottoTest {
    @DisplayName("유효한 번호로 WinningLotto 객체가 정상적으로 생성된다.")
    @Test
    void createWinningLotto() {
        List<Integer> winningNumbers = List.of(1,2,3,4,9,45);

        assertThatCode(() -> new WinningLotto(winningNumbers))
                .doesNotThrowAnyException();
    }

    @Nested
    @DisplayName("당첨 등수 판정")
    class MatchTest {
        private WinningLotto winningLotto;
        private LottoBonusNumber lottoBonusNumber;

        @BeforeEach
        void setUp() {
            winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
            lottoBonusNumber = new LottoBonusNumber(7, winningLotto);
        }

        @DisplayName("1등 (6개 일치)")
        @Test
        void match_1등() {
            Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            LottoRank rank = winningLotto.match(purchasedLotto, lottoBonusNumber);

            assertThat(rank).isEqualTo(LottoRank.FIRST);
            assertThat(rank.getPrize()).isEqualTo(2_000_000_000);
        }

        @DisplayName("2등 (5개 일치 + 보너스)")
        @Test
        void match_2등() {
            Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
            LottoRank rank = winningLotto.match(purchasedLotto, lottoBonusNumber);

            assertThat(rank).isEqualTo(LottoRank.SECOND);
            assertThat(rank.getPrize()).isEqualTo(30_000_000);
        }

        @DisplayName("3등 (5개 일치)")
        @Test
        void match_3등() {
            Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
            LottoRank rank = winningLotto.match(purchasedLotto, lottoBonusNumber);

            assertThat(rank).isEqualTo(LottoRank.THIRD);
            assertThat(rank.getPrize()).isEqualTo(1_500_000);
        }

        @DisplayName("4등 (4개 일치)")
        @Test
        void match_4등() {
            Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));
            LottoRank rank = winningLotto.match(purchasedLotto, lottoBonusNumber);

            assertThat(rank).isEqualTo(LottoRank.FOURTH);
            assertThat(rank.getPrize()).isEqualTo(50_000);
        }

        @DisplayName("5등 (3개 일치)")
        @Test
        void match_5등() {
            Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
            LottoRank rank = winningLotto.match(purchasedLotto, lottoBonusNumber);

            assertThat(rank).isEqualTo(LottoRank.FIFTH);
            assertThat(rank.getPrize()).isEqualTo(5_000);
        }

        @Nested
        @DisplayName("낙첨")
        class NoneRank {
            @DisplayName("낙첨 (2개 일치)")
            @Test
            void match_낙첨_2개만_일치() {
                Lotto purchasedLotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));
                LottoRank rank = winningLotto.match(purchasedLotto, lottoBonusNumber);

                assertThat(rank).isEqualTo(LottoRank.NONE);
                assertThat(rank.getPrize()).isEqualTo(0);
            }

            @DisplayName("낙첨 (1개 일치)")
            @Test
            void match_낙첨_1개만_일치() {
                Lotto purchasedLotto = new Lotto(List.of(1, 8, 9, 10, 11, 12));
                LottoRank rank = winningLotto.match(purchasedLotto, lottoBonusNumber);

                assertThat(rank).isEqualTo(LottoRank.NONE);
                assertThat(rank.getPrize()).isEqualTo(0);
            }

            @DisplayName("낙첨 (모두 불일치)")
            @Test
            void match_낙첨_모두_불일치() {
                Lotto purchasedLotto = new Lotto(List.of(13, 8, 9, 10, 11, 12));
                LottoRank rank = winningLotto.match(purchasedLotto, lottoBonusNumber);

                assertThat(rank).isEqualTo(LottoRank.NONE);
                assertThat(rank.getPrize()).isEqualTo(0);
            }
        }
    }
}

