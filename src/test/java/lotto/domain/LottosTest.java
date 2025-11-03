package lotto;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    @DisplayName("로또 개수를 올바르게 반환한다")
    @Test
    void getCount() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        Lotto lotto3 = new Lotto(List.of(13, 14, 15, 16, 17, 18));
        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        int count = lottos.getCount();

        assertThat(count).isEqualTo(3);
    }

    @DisplayName("로또 번호들이 오름차순으로 정렬되어 반환된다")
    @Test
    void getSortedLottos_정렬_확인() {
        Lotto lotto1 = new Lotto(List.of(6, 1, 3, 5, 2, 4));
        Lotto lotto2 = new Lotto(List.of(45, 10, 20, 15, 30, 25));
        Lottos lottos = new Lottos(List.of(lotto1, lotto2));

        List<List<Integer>> result = lottos.getSortedLottos();

        assertThat(result).containsExactly(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(10, 15, 20, 25, 30, 45)
        );
    }

    @DisplayName("여러 로또를 정렬해도 각 로또는 독립적으로 정렬된다")
    @Test
    void getSortedLottos_다중_로또_독립성() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));  // 이미 정렬됨
        Lotto lotto2 = new Lotto(List.of(43, 21, 37, 9, 15, 28));  // 정렬 필요
        Lotto lotto3 = new Lotto(List.of(45, 44, 43, 42, 41, 40));  // 역순
        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        List<List<Integer>> result = lottos.getSortedLottos();

        assertThat(result).hasSize(3);
        assertThat(result.get(0)).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(result.get(1)).containsExactly(9, 15, 21, 28, 37, 43);
        assertThat(result.get(2)).containsExactly(40, 41, 42, 43, 44, 45);
    }



}
