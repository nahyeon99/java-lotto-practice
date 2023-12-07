package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Prizes;
import lotto.domain.WinningLotto;
import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoNumber.LottoNumberFactory;
import lotto.dto.LottoDto;
import lotto.dto.PrizeDto;
import lotto.util.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView,
                           OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money lottoAmount = askLottoAmount();
        Lottos lottos = generateLottos(lottoAmount.calculateLottoCount());
        generatePrizes(lottoAmount, lottos);
    }

    private Money askLottoAmount() {
        return InputUtil.repeatUntilValidInput(() -> Money.wonsByLotto(inputView.inputPurchasePrice()));
    }

    private Lottos generateLottos(int lottoCount) {
        Lottos lottos = Lottos.of(Lotto.randomOf(lottoCount));
        printPurchaseLottos(lottos);

        return lottos;
    }

    private void generatePrizes(Money purchaseAmount, Lottos lottos) {
        Prizes prizes = Prizes.of(lottos.match(askWinningNumber()));

        printPrizes(prizes);
        printReturnRate(purchaseAmount, prizes);
    }

    private WinningLotto askWinningNumber() {
        Lotto lotto = InputUtil.repeatUntilValidInput(() -> Lotto.from(inputView.inputWinningNumbers()));
        LottoNumber lottoNumber = InputUtil.repeatUntilValidInput(
                () -> LottoNumberFactory.getNumber(inputView.inputBonusNumber()));

        return WinningLotto.of(lotto, lottoNumber);
    }

    private void printPurchaseLottos(Lottos lottos) {
        outputView.printPurchasedLottos(LottoDto.of(lottos));
    }

    private void printPrizes(Prizes prizes) {
        outputView.printWinningResults(PrizeDto.of(prizes));
    }

    private void printReturnRate(Money purchaseAmount, Prizes prizes) {
        outputView.printReturnRate(prizes.calculateReturnRate(purchaseAmount));
    }
}
