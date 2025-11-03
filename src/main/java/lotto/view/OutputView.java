package lotto.view;

import lotto.domain.lotto.LottoRank;
import lotto.domain.lotto.dto.LottoResultDTO;
import lotto.domain.lotto.dto.LottosDTO;
import lotto.domain.lotto.dto.RankStatDTO;

import java.util.List;

public class OutputView {
    private static final String BUY_MESSAGE = "개를 구매했습니다.";
    private static final String STAT_HEADER = "\n당첨 통계\n---";
    private static final String RESULT_FORMAT = "%s - %d개";
    private static final String YIELD_FORMAT = "총 수익률은 %.1f%%입니다.";


        System.out.println(lottoCount + BUY_MESSAGE);

        for (List<Integer> numbers : sortedLottoNumbers) {
            System.out.println(numbers);
        }
    }

    public void printWinningResult(LottoResultDTO lottoResultDto) {
        System.out.println();
        System.out.println(STAT_HEADER);

        for (RankStatDTO stat : lottoResultDto.getRankStats()) {
            LottoRank lottoRank = stat.getRank();

            if (lottoRank == LottoRank.NONE) {
                continue;
            }

            String message = createMessage(lottoRank);
            int count = stat.getCount();

            System.out.printf(RESULT_FORMAT + "\n", message, count);
        }

        System.out.printf(YIELD_FORMAT + "\n", lottoResultDto.getYield());
    }

    private String createMessage(LottoRank rank) {
        String prizeFormatted = String.format("%,d원", rank.getPrize());

        if (rank.isBonusMatch()) {
            return String.format("%d개 일치, 보너스 볼 일치 (%s)", rank.getMatchCount(), prizeFormatted);
        }

        return String.format("%d개 일치 (%s)", rank.getMatchCount(), prizeFormatted);
    }
}
