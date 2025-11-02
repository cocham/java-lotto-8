package lotto.domain.lotto.dto;

import java.util.List;

public class LottoResultDTO {
    private final List<RankStatDTO> rankStats;
    private final double yield;

    public LottoResultDTO(List<RankStatDTO> rankStats, double yield) {
        this.rankStats = rankStats;
        this.yield = yield;
    }

    public List<RankStatDTO> getRankStats() {
        return rankStats;
    }

    public double getYield() {
        return yield;
    }
}
