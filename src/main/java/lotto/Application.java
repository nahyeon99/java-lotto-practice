package lotto;

import lotto.config.AppConfig;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        AppConfig config = AppConfig.instance();
        LottoController lottoController = config.lottoController();

        lottoController.run();
    }
}
