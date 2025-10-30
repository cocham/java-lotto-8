package lotto;

import lotto.domain.lotto.Lotto;
import lotto.domain.Money;
import lotto.util.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {
    @DisplayName("투입 금액에 따라 정확한 개수의 로또가 발행되는지 검증")
    @Test
    void 로또_5개_구매() {
        Money money = new Money("5000");
        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.buyLottos(money);
        assertThat(lottos).hasSize(5);
    }
}
