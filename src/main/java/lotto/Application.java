package lotto;

import lotto.config.AppConfig;
import lotto.config.Config;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        Config config = generateConfig();
        LottoController lottoController = generateController(config);

        lottoController.run();
    }

    private static Config generateConfig() {
        return AppConfig.instance();
    }

    private static LottoController generateController(Config config) {
        return config.lottoController();
    }
}