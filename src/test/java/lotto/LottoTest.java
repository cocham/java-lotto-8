package lotto;

import lotto.domain.lotto.Lotto;
import lotto.domain.Money;
import lotto.exception.InvalidLottoMoneyException;
import lotto.exception.InvalidNumberException;
import lotto.exception.MinusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @DisplayName("구매 금액이 1000으로 안 나눠지면 예외가 발생한다.")
    @Test
    void 구매_금액이_1000으로_안_나눠지면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money("300"))
                .isInstanceOf(InvalidLottoMoneyException.class);
    }

    @DisplayName("구매 금액이 음수면 예외가 발생한다.")
    @Test
    void 구매_금액이_음수면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money("-1000"))
                .isInstanceOf(MinusException.class);
    }

    @DisplayName("구매 금액이 정수가 아니면 예외가 발생한다.")
    @Test
    void 구매_금액이_정수가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money("1000.0"))
                .isInstanceOf(InvalidNumberException.class);
    }


}
