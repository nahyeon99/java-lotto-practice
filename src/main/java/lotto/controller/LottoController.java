package lotto.controller;

import java.math.BigDecimal;
import lotto.domain.Prizes;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;
import lotto.domain.Money;
import lotto.dto.PrizeDto;
import lotto.service.LottoService;
import lotto.util.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView,
                           OutputView outputView,
                           LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Money purchaseAmount = askPurchaseAmount();
        int lottoCount = purchaseAmount.calculateLottoCount();

        Lottos lottos = generateLottos(lottoCount);
        printLottos(lottos);

        WinningLotto winningLotto = askWinningLotto();
        Prizes prizes = calculatePrizes(lottos, winningLotto);

        printResults(purchaseAmount, prizes);
    }

    private Money askPurchaseAmount() {
        return InputUtil.repeatUntilValidInput(() ->
                Money.createMoneyByPurchaseLottos(inputView.inputPurchaseAmount()));
    }

    private Lottos generateLottos(int lottoCount) {
        return lottoService.generateLottos(lottoCount);
    }

    private void printLottos(Lottos lottos) {
        outputView.printPurchaseLottos(lottos.getLottos());
    }

    private WinningLotto askWinningLotto() {
        Lotto lotto = InputUtil.repeatUntilValidInput(
                () -> lottoService.generateLotto(inputView.inputWinningNumbers()));

        return InputUtil.repeatUntilValidInput(
                () -> {
                    LottoNumber lottoNumber = LottoNumber.userOf(inputView.inputWinningBonusNumbers());
                    return lottoService.generateWinningLotto(lotto, lottoNumber);
                });
    }

    private Prizes calculatePrizes(Lottos lottos, WinningLotto winningLotto) {
        return calculateWinningResult(lottos, winningLotto);
    }

    private void printResults(Money purchaseAmount, Prizes prizes) {

        outputView.printWinningResultTitle();
        outputView.printWinningResults(PrizeDto.from(prizes));

        outputView.printReturnRate(calculateReturnRate(purchaseAmount, prizes));
    }

    private Prizes calculateWinningResult(Lottos lottos, WinningLotto winningLotto) {
        return lottoService.compareByWinningLotto(lottos, winningLotto);
    }

    private BigDecimal calculateReturnRate(Money purchaseAmount, Prizes prizes) {
        return prizes.calculateReturnRate(purchaseAmount);
    }
}
