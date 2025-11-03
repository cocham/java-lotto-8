package lotto.service;


import lotto.domain.Money;
import lotto.domain.lotto.*;
import lotto.domain.lotto.dto.LottoResultDTO;
import lotto.domain.lotto.dto.RankStatDTO;
import lotto.util.FixedNumberGenerator;
import lotto.util.LottoGenerator;
import lotto.util.NumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoService 클래스")
class LottoServiceTest {

    @Nested
    @DisplayName("로또 구매")
    class PurchaseLottosTest {
        private LottoService lottoService;

        @BeforeEach
        void setUp() {
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 4, 5, 6),
                    List.of(7, 8, 9, 10, 11, 12),
                    List.of(13, 14, 15, 16, 17, 18)
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);
            lottoService = new LottoService(lottoGenerator);
        }

        @Test
        @DisplayName("구매 금액에 맞는 개수의 로또가 DTO로 반환된다")
        void 로또_개수_확인() {
            Money money = new Money("3000");

            Lottos resultLottos = lottoService.purchaseLottos(money);

            assertThat(resultLottos.getCount()).isEqualTo(3);
        }

        @Test
        @DisplayName("DTO의 로또 번호들이 정렬되어 있다")
        void 로또_번호_정렬_확인() {
            Money money = new Money("2000");

            Lottos resultLottos = lottoService.purchaseLottos(money);
            List<List<Integer>> sortedNumbers = resultLottos.getSortedLottos();

            assertThat(sortedNumbers).hasSize(2);
            assertThat(sortedNumbers.get(0)).containsExactly(1, 2, 3, 4, 5, 6);
            assertThat(sortedNumbers.get(1)).containsExactly(7, 8, 9, 10, 11, 12);
        }

        @Test
        @DisplayName("1000원으로 1개의 로또를 구매한다")
        void 최소_금액_구매() {
            Money money = new Money("1000");

            Lottos resultLottos = lottoService.purchaseLottos(money);

            assertThat(resultLottos.getCount()).isEqualTo(1);
            assertThat(resultLottos.getSortedLottos()).hasSize(1);
        }
    }

    @Nested
    @DisplayName("당첨 계산")
    class CalculateWinningTest {
        private LottoService lottoService;
        private WinningLotto winningLotto;
        private LottoBonusNumber bonusNumber;

        @BeforeEach
        void setUp() {
            // 당첨 번호: 1, 2, 3, 4, 5, 6 / 보너스: 7
            winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
            bonusNumber = new LottoBonusNumber(7, winningLotto);
        }

        @Test
        @DisplayName("1등 당첨 시 통계에 반영된다")
        void 일등_당첨() {
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 4, 5, 6)  // 1등
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);
            lottoService = new LottoService(lottoGenerator);

            Money money = new Money("1000");
            Lottos resultLottos = lottoService.purchaseLottos(money);

            LottoResultDTO result = lottoService.calculateWinning(
                    resultLottos, winningLotto, bonusNumber, money
            );

            List<RankStatDTO> rankStats = result.getRankStats();
            RankStatDTO firstRank = findRankStat(rankStats, LottoRank.FIRST);

            assertThat(firstRank).isNotNull();
            assertThat(firstRank.getCount()).isEqualTo(1);
        }

        @Test
        @DisplayName("2등 당첨 시 통계에 반영된다 (5개 + 보너스)")
        void 이등_당첨() {
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 4, 5, 7)  // 2등 (보너스 포함)
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);
            lottoService = new LottoService(lottoGenerator);

            Money money = new Money("1000");
            Lottos lottos = lottoService.purchaseLottos(money);

            LottoResultDTO result = lottoService.calculateWinning(
                    lottos, winningLotto, bonusNumber, money
            );

            List<RankStatDTO> rankStats = result.getRankStats();
            RankStatDTO secondRank = findRankStat(rankStats, LottoRank.SECOND);

            assertThat(secondRank).isNotNull();
            assertThat(secondRank.getCount()).isEqualTo(1);
        }

        @Test
        @DisplayName("여러 등수의 당첨이 올바르게 집계된다")
        void 여러_등수_집계() {
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 4, 5, 6),    // 1등
                    List.of(1, 2, 3, 4, 5, 7),    // 2등
                    List.of(1, 2, 3, 4, 5, 8),    // 3등
                    List.of(1, 2, 3, 4, 8, 9),    // 4등
                    List.of(1, 2, 3, 8, 9, 10)    // 5등
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);
            lottoService = new LottoService(lottoGenerator);

            Money money = new Money("5000");
            Lottos lottos = lottoService.purchaseLottos(money);

            LottoResultDTO result = lottoService.calculateWinning(
                    lottos, winningLotto, bonusNumber, money
            );

            List<RankStatDTO> rankStats = result.getRankStats();

            assertThat(findRankStat(rankStats, LottoRank.FIRST).getCount()).isEqualTo(1);
            assertThat(findRankStat(rankStats, LottoRank.SECOND).getCount()).isEqualTo(1);
            assertThat(findRankStat(rankStats, LottoRank.THIRD).getCount()).isEqualTo(1);
            assertThat(findRankStat(rankStats, LottoRank.FOURTH).getCount()).isEqualTo(1);
            assertThat(findRankStat(rankStats, LottoRank.FIFTH).getCount()).isEqualTo(1);
        }

        @Test
        @DisplayName("낙첨만 있으면 모든 등수의 개수가 0이다")
        void 모두_낙첨() {
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(8, 9, 10, 11, 12, 13),
                    List.of(14, 15, 16, 17, 18, 19)
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);
            lottoService = new LottoService(lottoGenerator);

            Money money = new Money("2000");
            Lottos lottos = lottoService.purchaseLottos(money);

            LottoResultDTO result = lottoService.calculateWinning(
                    lottos, winningLotto, bonusNumber, money
            );

            List<RankStatDTO> rankStats = result.getRankStats();

            assertThat(findRankStat(rankStats, LottoRank.FIRST).getCount()).isEqualTo(0);
            assertThat(findRankStat(rankStats, LottoRank.SECOND).getCount()).isEqualTo(0);
            assertThat(findRankStat(rankStats, LottoRank.THIRD).getCount()).isEqualTo(0);
            assertThat(findRankStat(rankStats, LottoRank.FOURTH).getCount()).isEqualTo(0);
            assertThat(findRankStat(rankStats, LottoRank.FIFTH).getCount()).isEqualTo(0);
        }

        @Test
        @DisplayName("수익률이 올바르게 계산된다")
        void 수익률_계산() {
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 4, 5, 8)  // 3등: 1,500,000원
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);
            lottoService = new LottoService(lottoGenerator);

            Money money = new Money("1000");
            Lottos lottos = lottoService.purchaseLottos(money);

            LottoResultDTO result = lottoService.calculateWinning(
                    lottos, winningLotto, bonusNumber, money
            );

            // 1,500,000 / 1,000 * 100 = 150,000%
            assertThat(result.getYield()).isEqualTo(150000.0);
        }

        @Test
        @DisplayName("수익률이 100% 미만일 때 올바르게 계산된다")
        void 수익률_100퍼센트_미만() {
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 8, 9, 10),   // 5등: 5,000원
                    List.of(11, 12, 13, 14, 15, 16), // 꽝
                    List.of(17, 18, 19, 20, 21, 22), // 꽝
                    List.of(23, 24, 25, 26, 27, 28), // 꽝
                    List.of(29, 30, 31, 32, 33, 34)  // 꽝
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);
            lottoService = new LottoService(lottoGenerator);

            Money money = new Money("5000");
            Lottos lottos = lottoService.purchaseLottos(money);

            LottoResultDTO result = lottoService.calculateWinning(
                    lottos, winningLotto, bonusNumber, money
            );

            // 5,000 / 5,000 * 100 = 100%
            assertThat(result.getYield()).isEqualTo(100.0);
        }

        @Test
        @DisplayName("통계 순서가 5등부터 1등 순서로 반환된다")
        void 통계_순서_확인() {
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 4, 5, 6)
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);
            lottoService = new LottoService(lottoGenerator);

            Money money = new Money("1000");
            Lottos lottos = lottoService.purchaseLottos(money);

            LottoResultDTO result = lottoService.calculateWinning(
                    lottos, winningLotto, bonusNumber, money
            );

            List<RankStatDTO> rankStats = result.getRankStats();
            assertThat(rankStats).hasSize(5);
            assertThat(rankStats.get(0).getRank()).isEqualTo(LottoRank.FIFTH);
            assertThat(rankStats.get(1).getRank()).isEqualTo(LottoRank.FOURTH);
            assertThat(rankStats.get(2).getRank()).isEqualTo(LottoRank.THIRD);
            assertThat(rankStats.get(3).getRank()).isEqualTo(LottoRank.SECOND);
            assertThat(rankStats.get(4).getRank()).isEqualTo(LottoRank.FIRST);
        }

        private RankStatDTO findRankStat(List<RankStatDTO> rankStats, LottoRank rank) {
            return rankStats.stream()
                    .filter(stat -> stat.getRank() == rank)
                    .findFirst()
                    .orElse(null);
        }
    }
}