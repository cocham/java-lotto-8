package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParserTest {
    private final InputParser parser = new InputParser();

    @DisplayName("쉼표를 기준으로 문자열을 분리하고 앞뒤 공백을 제거한다.")
    @Test
    void tokenizeNumbers() {
        String input = " 1,2 , 3, 40 ,45 ";
        List<String> result = parser.tokenizeNumbers(input);

        assertThat(result).containsExactly("1", "2", "3", "40", "45");
        assertThat(result).hasSize(5);
    }

    @DisplayName("String 리스트를 Integer 리스트로 반환한다.")
    @Test
    void parseNumbers() {
        List<String> input = List.of("1", "2", "3", "40", "45");
        List<Integer> result = parser.parseNumbers(input);

        assertThat(result).containsExactly(1, 2, 3, 40, 45);
        assertThat(result).hasSize(5);
    }
}
