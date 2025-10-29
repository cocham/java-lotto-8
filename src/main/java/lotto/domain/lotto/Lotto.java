package lotto.domain.lotto;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoValidation.validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }


    // TODO: 추가 기능 구현
    //발행 로또를 오름차순으로 정렬한다
    public List<Integer> getSortedNumbers() {
        List<Integer> sortedLottoNumbers = new ArrayList<>(numbers); //numbers를 변경하면 안되므로 복사본을 만듦.
        Collections.sort(sortedLottoNumbers);
        return sortedLottoNumbers;
    }
}

