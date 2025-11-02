package lotto.service;

import lotto.domain.Money;
import lotto.domain.lotto.*;
import lotto.domain.lotto.dto.LottoResultDTO;
import lotto.domain.lotto.dto.RankStatDTO;
import lotto.util.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private static final List<LottoRank> RANK_ORDER = List.of(
            LottoRank.FIFTH,
            LottoRank.FOURTH,
            LottoRank.THIRD,
            LottoRank.SECOND,
            LottoRank.FIRST
    );

    private final LottoGenerator lottoGenerator;

    public LottoService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lottos purchaseLottos(Money money) {
        return lottoGenerator.buyLottos(money);
    }

    public LottoResultDTO calculateWinning(Lottos lottos,
                                           WinningLotto winningLotto,
                                           LottoBonusNumber lottoBonusNumber,
                                           Money money) {

        LottoResult result = new LottoResult(
                lottos,
                winningLotto,
                lottoBonusNumber
        );

        List<RankStatDTO> rankStats = createRankStats(result);
        Yield yield = new Yield(result.getTotalPrize(), money.getAmount());

        return new LottoResultDTO(rankStats, yield.getValue());
    }


    private List<RankStatDTO> createRankStats(LottoResult result) {
        List<RankStatDTO> stats = new ArrayList<>();

        for (LottoRank rank : RANK_ORDER) {
            int count = result.getCountByRank(rank);
            stats.add(new RankStatDTO(rank, count));
        }

        return stats;
    }
}
