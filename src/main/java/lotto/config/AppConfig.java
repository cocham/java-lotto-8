package lotto.config;

import lotto.service.LottoService;
import lotto.util.LottoGenerator;
import lotto.util.NumberGenerator;
import lotto.util.RandomNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    public LottoService lottoService() {
        return new LottoService(lottoGenerator());
    }

    public LottoGenerator lottoGenerator() {
        return new LottoGenerator(randomNumberGenerator());
    }

    public NumberGenerator randomNumberGenerator() {
        return new RandomNumberGenerator();
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
