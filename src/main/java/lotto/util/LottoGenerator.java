package lotto.util;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.lotto.Lotto;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int PICK_COUNT = 6;

    //로또번호를 발행한다
    public static List<Integer> issuanceLotto() {
        return Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, PICK_COUNT);
    }

    //로또를 산다
    public List<Lotto> buyLottos(Money money) {
        int count = money.calculateLottoCount();
        List<Lotto> lottos = new ArrayList<>();
        for (int c = 0; c < count; c++) {
            List<Integer> lottoNumbers = issuanceLotto();
            Lotto lotto = new Lotto(lottoNumbers);
            lottos.add(lotto);
        }
        return lottos;
    }

}