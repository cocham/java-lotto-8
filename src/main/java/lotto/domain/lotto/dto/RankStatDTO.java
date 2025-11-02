package lotto.domain.lotto.dto;

import lotto.domain.lotto.LottoRank;

public class RankStatDTO {
    private final LottoRank rank;
    private final int count;

    public RankStatDTO(LottoRank rank, int count) {
        this.rank = rank;
        this.count = count;
    }

    public LottoRank getRank() {
        return rank;
    }

    public int getCount() {
        return count;
    }
}