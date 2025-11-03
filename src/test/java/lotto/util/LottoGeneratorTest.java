package lotto;

import lotto.domain.Money;
import lotto.domain.lotto.Lottos;
import lotto.util.FixedNumberGenerator;
import lotto.util.LottoGenerator;
import lotto.util.NumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoGenerator 클래스")
public class LottoGeneratorTest {

    @Nested
    @DisplayName("로또 구매")
    class BuyLottosTest {
        @Test
        @DisplayName("구매 금액에 맞는 개수의 로또를 생성한다")
        void 로또_5개_구매() {
            Money money = new Money("5000");
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 4, 5, 6),
                    List.of(7, 8, 9, 10, 11, 12),
                    List.of(13, 14, 15, 16, 17, 18),
                    List.of(19, 20, 21, 22, 23, 24),
                    List.of(25, 26, 27, 28, 29, 30)
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);

            Lottos lottos = lottoGenerator.buyLottos(money);

            assertThat(lottos.getCount()).isEqualTo(5);
        }

        @DisplayName("생성된 로또는 정확한 번호를 가진다")
        @Test
        void 로또_번호_확인() {
            Money money = new Money("2000");
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 4, 5, 6),
                    List.of(7, 8, 9, 10, 11, 12)
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);

            Lottos lottos = lottoGenerator.buyLottos(money);
            List<List<Integer>> sortedLottos = lottos.getSortedLottos();

            assertThat(sortedLottos).hasSize(2);
            assertThat(sortedLottos.get(0)).containsExactly(1, 2, 3, 4, 5, 6);
            assertThat(sortedLottos.get(1)).containsExactly(7, 8, 9, 10, 11, 12);
        }

        @DisplayName("1000원으로 1개의 로또를 구매한다")
        @Test
        void 최소_금액_구매() {
            Money money = new Money("1000");
            NumberGenerator generator = new FixedNumberGenerator(List.of(
                    List.of(1, 2, 3, 4, 5, 6)
            ));
            LottoGenerator lottoGenerator = new LottoGenerator(generator);

            Lottos lottos = lottoGenerator.buyLottos(money);

            assertThat(lottos.getCount()).isEqualTo(1);
        }
    }
}
