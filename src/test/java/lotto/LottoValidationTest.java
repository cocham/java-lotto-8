package lotto;

import lotto.domain.lotto.LottoValidation;
import lotto.exception.InvalidLottoNumberException;
import lotto.exception.InvalidLottoSizeException;
import lotto.exception.NumberDuplicateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoValidationTest {
    @DisplayName("로또 번호의 개수가 6개가 아니면 InvalidLottoSizeException 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {5, 7}) //validateLottosize에 매개변수로 들어감
    void validateLottoSize(int size) {
        List<Integer> numbers;
        if (size == 7) {
            numbers = List.of(1, 2, 3, 4, 5, 6, 7);
        } else {
            numbers = List.of(1, 2, 3, 4, 5);
        }
        assertThatThrownBy(() -> LottoValidation.validate(numbers))
                .isInstanceOf(InvalidLottoSizeException.class);
    }

    @DisplayName("로또 번호가 1이상 45이하가 아니면 InvalidLottoNumberException 예외가 발생한다.")
    @Test
    void 로또_번호_범위_충족_테스트() {
        List<Integer> numbers = List.of(1, 2, 3, 48, 5, 6);

        assertThatThrownBy(() -> LottoValidation.validate(numbers))
                .isInstanceOf(InvalidLottoNumberException.class);
    }

    @DisplayName("로또 번호가 중복되면 NumberDuplicateException 예외가 발생한다.")
    @Test
    void 로또_번호_중복_테스트() {
        List<Integer> numbers = List.of(1, 2, 3, 6, 5, 5);

        assertThatThrownBy(() -> LottoValidation.validate(numbers))
                .isInstanceOf(NumberDuplicateException.class);
    }
}
