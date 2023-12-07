package lotto.config;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig implements Config {

    public static AppConfig instance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public LottoController lottoController() {
        return LazyHolder.lottoController;
    }

    private static class LazyHolder {
        private static final AppConfig INSTANCE = new AppConfig();

        private static final InputView inputView = createInputView();
        private static final OutputView ouputView = createOuputView();

        private static final LottoController lottoController = createLottoController();

        private static InputView createInputView() {
            return new InputView();
        }

        private static OutputView createOuputView() {
            return new OutputView();
        }

        private static LottoController createLottoController() {
            return new LottoController(inputView, ouputView);
        }
    }
}
