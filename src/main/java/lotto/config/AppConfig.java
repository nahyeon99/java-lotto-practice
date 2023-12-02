package lotto.config;

import lotto.controller.LottoController;
import lotto.domain.lotto.LottosGenerator;
import lotto.domain.lotto.RandomLottosGenerator;
import lotto.service.LottoService;
import lotto.service.RandomLottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    public static AppConfig instance() {
        return LazyHolder.INSTANCE;
    }

    public LottoController lottoController() {
        return LazyHolder.lottoController;
    }

    private static class LazyHolder {
        private static final AppConfig INSTANCE = new AppConfig();
        private static final InputView inputView = createInputView();
        private static final OutputView outputView = createOutputView();
        private static final LottosGenerator lottosGenerator = createLottosGenerator();
        private static final LottoService lottoService = createLottoService();
        private static final LottoController lottoController = createLottoController();

        private static InputView createInputView() {
            return new InputView();
        }

        private static OutputView createOutputView() {
            return new OutputView();
        }

        private static LottosGenerator createLottosGenerator() {
            return new RandomLottosGenerator();
        }

        private static LottoService createLottoService() {
            return new RandomLottoService(lottosGenerator);
        }

        private static LottoController createLottoController() {
            return new LottoController(inputView, outputView, lottoService);
        }
    }
}
