package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(new ArrayList<>(lottos));
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<List<Integer>> getSortedLottos() {
        return lottos.stream()
                .map(Lotto::getSortedNumbers)
                .toList();
    }

    public int getCount() {
        return lottos.size();
    }

}
