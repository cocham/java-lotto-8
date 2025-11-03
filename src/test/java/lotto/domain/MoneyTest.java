package lotto.domain;

import lotto.exception.InvalidLottoMoneyException;
import lotto.exception.InvalidNumberException;
import lotto.exception.OutOfRangeMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @DisplayName("구매 금액이 1000원 미만이면 예외가 발생한다.")
    @Test
    void 구매_금액이_1000원_이하면_예외_발생() {
        assertThatThrownBy(() -> new Money("-1000"))
                .isInstanceOf(OutOfRangeMoneyException.class);
    }

    @DisplayName("구매 금액이 1000 배수가 아니면 예외가 발생한다.")
    @Test
    void 구매_금액이_1000_배수가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money("2500"))
                .isInstanceOf(InvalidLottoMoneyException.class);
    }

    @DisplayName("구매 금액이 100,000원 초과면 예외가 발생한다.")
    @Test
    void 구매_금액이_1000으로_안_나눠지면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money("300000"))
                .isInstanceOf(OutOfRangeMoneyException.class);
    }

    @DisplayName("구매 금액이 정수가 아니면 예외가 발생한다.")
    @Test
    void 구매_금액이_정수가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money("1000.0"))
                .isInstanceOf(InvalidNumberException.class);
    }
}
