package lotto.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.PrizeType;
import lotto.dto.LottoDto;
import lotto.dto.PrizeDto;

public class OutputView {
    public void printPurchaseLottos(List<LottoDto> lottos) {
        printPurchaseCountOfLotto(lottos.size());

        lottos.forEach(this::printLotto);
        System.out.println();
    }

    public void printWinningResultTitle() {
        System.out.println(OutputMessage.RESULT_OF_WINNING_TITLE.message);
        System.out.println(OutputMessage.RESULT_OF_WINNING_TITLE_DELIMITER.message);
    }

    public void printWinningResults(List<PrizeDto> prizes) {
        prizes.forEach(this::printWinningResult);
    }

    public void printReturnRate(BigDecimal returnRate) {
        System.out.printf(OutputMessage.RESULT_OF_RETURN_RATE.message,
                returnRate.setScale(2, RoundingMode.HALF_UP));

        System.out.println();
    }

    private void printPurchaseCountOfLotto(int count) {
        System.out.printf(OutputMessage.RESULT_OF_PURCHASE_COUNT_FORMAT.message, count);
        System.out.println();
    }

    private void printLotto(LottoDto lotto) {
        String numbers = lotto.lottos()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(OutputMessage.DELIMITER_OF_LOTTO_NUMBER.message));

        System.out.printf(OutputMessage.RESULT_OF_LOTTO_NUMBERS.message, numbers);
        System.out.println();
    }

    private void printWinningResult(PrizeDto prizeDto) {
        PrizeType prizeType = prizeDto.prizeType();

        if (prizeType.getMatchBonus()) {
            printWinningResultOfSecond(prizeDto);
            return;
        }

        System.out.printf(OutputMessage.RESULT_OF_WINNING_LOTTO_FORMAT.message,
                prizeType.getMatchNumber(),
                amountFormat(prizeType.getPrizeAmount()),
                prizeDto.prizeCount());

        System.out.println();
    }

    private String amountFormat(BigDecimal amount) {
        return String.format("%,d", amount.intValue());
    }

    private void printWinningResultOfSecond(PrizeDto prizeDto) {
        System.out.printf(OutputMessage.RESULT_OF_SECOND_WINNING_LOTTO_FORMAT.message,
                PrizeType.SECOND.getMatchNumber(),
                amountFormat(PrizeType.SECOND.getPrizeAmount()),
                prizeDto.prizeCount());

        System.out.println();
    }

    private enum OutputMessage {
        RESULT_OF_PURCHASE_COUNT_FORMAT("%d개를 구매했습니다."),
        RESULT_OF_LOTTO_NUMBERS("[%s]"),
        DELIMITER_OF_LOTTO_NUMBER(", "),
        RESULT_OF_WINNING_TITLE("당첨 통계"),
        RESULT_OF_WINNING_TITLE_DELIMITER("---"),
        RESULT_OF_WINNING_LOTTO_FORMAT("%d개 일치 (%s원) - %d개"),
        RESULT_OF_SECOND_WINNING_LOTTO_FORMAT("%d개 일치, 보너스 볼 일치 (%s원) - %d개"),
        RESULT_OF_RETURN_RATE("총 수익률은 %s%%입니다.")
        ;

        private String message;

        OutputMessage(String message) {
            this.message = message;
        }
    }
}