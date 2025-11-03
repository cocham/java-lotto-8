package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.exception.InvalidLottoNumberException;
import lotto.exception.InvalidLottoSizeException;
import lotto.exception.NumberDuplicateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(InvalidLottoSizeException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(NumberDuplicateException.class);
    }

    @DisplayName("로또에 1 이상 45이하의 숫자가 포함될 시 예외가 발생한다.")
    @Test
    void outOfRangeNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 48)))
                .isInstanceOf(InvalidLottoNumberException.class);
    }

}
