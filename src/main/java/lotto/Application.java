package lotto;

import lotto.config.AppConfig;
import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        LottoService lottoService = appConfig.lottoService();
        InputView inputView = appConfig.inputView();
        OutputView outputView = appConfig.outputView();

        LottoController lottoController = new LottoController(
                inputView,
                outputView,
                lottoService
        );

        lottoController.run();
    }
}
