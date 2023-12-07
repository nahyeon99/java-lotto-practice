package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.dto.LottoDto;
import lotto.service.LottoService;
import lotto.util.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService baseService;

    public LottoController(InputView inputView,
                           OutputView outputView,
                           LottoService baseService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.baseService = baseService;
    }

    public void run() {

        Money lottoAmount = askLottoAmount();

        Lottos lottos = buyLottos(lottoAmount.calculateLottoCount());
    }

    private Money askLottoAmount() {
        return InputUtil.repeatUntilValidInput(() -> Money.wons(inputView.inputPurchasePrice()));
    }

    private Lottos buyLottos(int lottoCount) {
        return Lottos.of(Lotto.randomOf(lottoCount));
    }

    private void printPurchaseLottos(Lottos lottos) {
        outputView.printPurchasedLottos(LottoDto.of(lottos));
    }
}
