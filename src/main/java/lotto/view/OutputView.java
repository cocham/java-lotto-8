package lotto.view;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;

import java.util.List;

public class OutputView {
    private static final String BUY_MESSAGE = "개를 구매했습니다.";

    public void printPurchaseHistory(Lottos lottos) {
        int lottoCount = lottos.getCount();

        List<List<Integer>> sortedLottoNumbers = lottos.getSortedLottos();
        System.out.println(lottoCount + BUY_MESSAGE);

        for (List<Integer> numbers : sortedLottoNumbers) {
            System.out.println(numbers);
        }
    }
}
