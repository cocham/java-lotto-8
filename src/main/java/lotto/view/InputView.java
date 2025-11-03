package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.NullInputException;

public class InputView {
    private static final String PURCHASE_LOOTTO_MSG = "구입금액을 입력해 주세요.";
    private static final String WINNING_LOTTO_MSG = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_LOTTO_MSG = "보너스 번호를 입력해 주세요.";

    public String readPurchaseAmount() {
        System.out.println(PURCHASE_LOOTTO_MSG);
        return readValidateInput();
    }

    public String readWinningNumbers() {
        System.out.println();
        System.out.println(WINNING_LOTTO_MSG);
        return readValidateInput();
    }

    public String readBonusNumer() {
        System.out.println();
        System.out.println(BONUS_LOTTO_MSG);
        return readValidateInput();
    }

    public String readValidateInput() {
        String input = Console.readLine();
        if (input == null || input.isEmpty()) {
            throw new NullInputException();
        };
        return input;
    }
}
