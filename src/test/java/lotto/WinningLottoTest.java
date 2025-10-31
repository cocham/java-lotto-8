package lotto;

import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class WinningLottoTest {
    @DisplayName("유효한 번호로 WinningLotto 객체가 정상적으로 생성된다.")
    @Test
    void crateWinningLotto() {
        List<Integer> winningNumbers = List.of(1,2,3,4,9,45);

        assertThatCode(() -> new WinningLotto(winningNumbers))
                .doesNotThrowAnyException();
    }
}
