package lotto.util;

import lotto.domain.lotto.Lotto;
import lotto.domain.Money;
import lotto.domain.lotto.Lottos;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
    private final NumberGenerator numberGenerator;

    public LottoGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos buyLottos(Money money) {
        int count = money.calculateLottoCount();
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<Integer> lottoNumbers = numberGenerator.generate();
            lottos.add(new Lotto(lottoNumbers));
        }

        return new Lottos(lottos);
    }

}