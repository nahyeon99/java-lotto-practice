package lotto.domain.lotto;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static lotto.domain.lotto.Lotto.LOTTO_NUMBER_COUNT;
import static lotto.domain.lotto.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.domain.lotto.LottoNumber.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.List;

public class RandomLottosGenerator implements LottosGenerator {
    @Override
    public Lottos generate(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generateLotto());
        }
        return Lottos.of(lottos);
    }

    private Lotto generateLotto() {
        return Lotto.computerOf(generateLottoNumbers());
    }

    private List<LottoNumber> generateLottoNumbers() {
        return generateRandomNumbers().stream()
                .map(LottoNumber::computerOf)
                .toList();
    }

    private List<Integer> generateRandomNumbers() {
        return pickUniqueNumbersInRange(MIN_LOTTO_NUMBER,
                MAX_LOTTO_NUMBER,
                LOTTO_NUMBER_COUNT);
    }
}