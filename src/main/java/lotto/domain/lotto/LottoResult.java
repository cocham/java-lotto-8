package lotto.domain.lotto;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> rankCounts;

    public LottoResult(Lottos lottos, WinningLotto winningLotto, LottoBonusNumber bonusNumber) {
        this.rankCounts = initializeRankCounts();
        for (Lotto lotto : lottos.getLottos()) {
            LottoRank resultRank = winningLotto.match(lotto, bonusNumber);
            this.rankCounts.put(
                    resultRank,
                    this.rankCounts.get(resultRank) + 1
            );
        }
    }

    public int getCountByRank(LottoRank rank) {
        return this.rankCounts.getOrDefault(rank, 0);
    }

    public Map<LottoRank, Integer> getRankCounts() {
        return Collections.unmodifiableMap(rankCounts);
    }

    public long getTotalPrize() {
        long totalPrize = 0;

        for (Map.Entry<LottoRank, Integer> entry : rankCounts.entrySet()) {
            LottoRank lottoRank = entry.getKey();
            int count = entry.getValue();
            totalPrize += (long)lottoRank.getPrize() * count;
        }

        return totalPrize;
    }

    private Map<LottoRank, Integer> initializeRankCounts() {
        Map<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        for (LottoRank rank : LottoRank.values()) {
            counts.put(rank, 0);
        }
        return counts;
    }
}
