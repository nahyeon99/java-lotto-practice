package lotto.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.LottoDto;
import lotto.dto.PrizeDto;

public class OutputView {
    public void printPurchasedLottos(List<LottoDto> lottos) {
        printPurchasedLottosCount(lottos.size());

        lottos.forEach(this::printLotto);
    }

    private void printPurchasedLottosCount(int size) {
        System.out.printf(Message.PURCHASE_LOTTO_COUNT_FORMAT.korean, size);
        System.out.println();
    }

    private void printLotto(LottoDto lotto) {
        String numbers = lotto.lottoNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(Message.DELIMITER_OF_LOTTO_NUMBER.korean));

        System.out.printf(Message.PURCHASE_LOTTO_FORMAT.korean, numbers);
        System.out.println();
    }

    public void printWinningResults(List<PrizeDto> prizes) {
        prizes.forEach(prize -> {
            String message = Message.COMMON_PRIZE_FORMAT.korean;

            if (prize.isSecond()) {
                message = Message.SECOND_PRIZE_FORMAT.korean;
            }
            printWinningResult(message, prize);
        });
    }

    private void printWinningResult(String message, PrizeDto prizeDto) {
        System.out.printf(message,
                prizeDto.prizeCount(),
                amountFormat(prizeDto.prizeType().getPrizeAmount().getAmount()));

        System.out.println();
    }

    public void printReturnRate(BigDecimal returnRate) {
        System.out.printf(Message.RETURN_RATE_FORMAT.korean,
                returnRate.setScale(2, RoundingMode.HALF_UP));

        System.out.println();
    }

    private String amountFormat(BigDecimal amount) {
        return String.format("%,d", amount.intValue());
    }

    private enum Message {
        PURCHASE_LOTTO_COUNT_FORMAT("%d개를 구매했습니다."),
        PURCHASE_LOTTO_FORMAT("[%s]"),
        DELIMITER_OF_LOTTO_NUMBER(", "),
        WINNING_RESULT_TITLE("당첨 통계"),
        DELIMITER_OF_TITLE("---"),
        COMMON_PRIZE_FORMAT("%d개 일치 (%s원) - %d개"),
        SECOND_PRIZE_FORMAT("%d개 일치, 보너스 볼 일치 (%s원) - %d개"),
        RETURN_RATE_FORMAT("총 수익률은 %s%%입니다."),
        ;
        private String korean;

        Message(String korean) {
            this.korean = korean;
        }
    }
}
