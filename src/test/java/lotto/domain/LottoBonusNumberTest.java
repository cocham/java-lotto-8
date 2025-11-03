package lotto.domain;

import lotto.domain.lotto.LottoBonusNumber;
import lotto.domain.lotto.WinningLotto;
import lotto.exception.InvalidLottoNumberException;
import lotto.exception.NumberDuplicateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoBonusNumberTest {
    private static final WinningLotto VALID_WINNING_LOTTO = new WinningLotto(List.of(1,8,35,24,9,40));

    @DisplayName("보너스 번호가 1 미만 또는 45 초과면 InvalidLottoNumberException이 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void createBonusNumberByInvalidRange(int invalidNumber) {
        assertThatThrownBy(() -> new LottoBonusNumber(invalidNumber, VALID_WINNING_LOTTO))
                .isInstanceOf(InvalidLottoNumberException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복 되면 NumberDuplicateException이 발생한다.")
    @Test
    void createBonusNumberByDuplicate() {
        int duplicateNumber = 1;
        assertThatThrownBy(() -> new LottoBonusNumber(duplicateNumber, VALID_WINNING_LOTTO))
                .isInstanceOf(NumberDuplicateException.class);
    }

    @DisplayName("유효하고 중복되지 않은 보너스 번호가 정상적으로 생성된다")
    @Test
    void createBonusNumber() {
        int validateNumber = 7;
        assertThatCode(() -> new LottoBonusNumber(validateNumber, VALID_WINNING_LOTTO))
                .doesNotThrowAnyException();
    }
}
