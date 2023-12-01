package lotto.view;

import static lotto.util.InputUtil.*;
import static lotto.util.InputUtil.readNumber;

import java.util.List;

public class InputView {
    public int inputPurchaseAmount() {
        System.out.println(OutputMessage.INPUT_PURCHASE_AMOUNT.message);

        return readNumber(readLine());
    }

    public List<Integer> inputWinningNumbers() {
        System.out.println(OutputMessage.INPUT_WINNING_NUMBERS.message);

        return readNumbers(readLine(), OutputMessage.WINNING_NUMBER_DELIMITER.message);
    }

    public int inputWinningBonusNumbers() {
        System.out.println(OutputMessage.INPUT_WINNING_BONUS_NUMBERS.message);

        return readNumber(readLine());
    }

    private enum OutputMessage {
        INPUT_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
        INPUT_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
        INPUT_WINNING_BONUS_NUMBERS("보너스 번호를 입력해 주세요."),
        WINNING_NUMBER_DELIMITER(","),
        ;

        private String message;

        OutputMessage(String message) {
            this.message = message;
        }
    }
}