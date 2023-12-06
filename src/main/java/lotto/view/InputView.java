package lotto.view;

import static lotto.util.InputUtil.readLine;
import static lotto.util.InputUtil.readNumber;
import static lotto.util.InputUtil.readNumbers;

import java.util.List;

public class InputView {

    public int inputPurchasePrice() {
        System.out.println(Message.INPUT_PURCHASE_AMOUNT.korean);

        return readNumber(readLine());
    }

    public List<Integer> inputWinningNumbers() {
        System.out.println(Message.INPUT_WINNING_NUMBERS.korean);

        return readNumbers(readLine(), Message.WINNING_NUMBER_DELIMITER.korean);
    }

    public int inputBonusNumber() {
        System.out.println(Message.INPUT_BONUS_NUMBER.korean);

        return readNumber(readLine());
    }

    private enum Message {
        INPUT_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
        INPUT_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
        INPUT_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
        WINNING_NUMBER_DELIMITER(","),
        ;
        private String korean;

        Message(String korean) {
            this.korean = korean;
        }
    }
}