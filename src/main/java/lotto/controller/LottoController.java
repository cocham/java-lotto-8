package lotto.controller;

import lotto.domain.Money;
import lotto.domain.lotto.LottoBonusNumber;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.lotto.dto.LottoResultDTO;
import lotto.domain.lotto.dto.LottosDTO;
import lotto.service.LottoService;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;
    private final InputParser inputParser;

    public LottoController(InputView inputView,
                           OutputView outputView,
                           LottoService lottoService,
                           InputParser inputParser
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
        this.inputParser = inputParser;
    }

    public void run() {
        Money money = readPurchaseAmount();
        Lottos lottos = lottoService.purchaseLottos(money);

        LottosDTO lottosDTO = new LottosDTO(lottos.getCount(), lottos.getSortedLottos());
        outputView.printPurchasedHistory(lottosDTO);

        WinningLotto winningLotto = readWinningLotto();
        LottoBonusNumber lottoBonusNumber = readBonusNumber(winningLotto);

        LottoResultDTO lottoResultDTO = lottoService.calculateWinning(
            lottos,
            winningLotto,
            lottoBonusNumber,
            money
        );
        outputView.printWinningResult(lottoResultDTO);
    }

    private Money readPurchaseAmount() {
        return retryOnException(() -> {
            String inputMoney = inputView.readPurchaseAmount();
            return new Money(inputMoney);
        });
    }

    private WinningLotto readWinningLotto() {
        return retryOnException(() -> {
            String inputWinningLotto = inputView.readWinningNumbers();
            List<String> tokens = InputParser.tokenizeNumbers(inputWinningLotto);
            List<Integer> winningNumbers = inputParser.parseNumbers(tokens);

            return new WinningLotto(winningNumbers);
        });
    }

    private LottoBonusNumber readBonusNumber(WinningLotto winningLotto) {
        return retryOnException(() -> {
            String inputBonusNumber = inputView.readBonusNumer();
            int bonusNumber = InputParser.parseNumber(inputBonusNumber);

            return new LottoBonusNumber(bonusNumber, winningLotto);
        });
    }

    private <T> T retryOnException(java.util.function.Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
