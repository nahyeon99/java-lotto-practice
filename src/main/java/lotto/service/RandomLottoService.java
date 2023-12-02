package lotto.service;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;
import lotto.domain.Prizes;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.LottosGenerator;
import lotto.dto.MatchDto;

public class RandomLottoService implements LottoService {
    private final LottosGenerator lottosGenerator;

    public RandomLottoService(LottosGenerator lottosGenerator) {
        this.lottosGenerator = lottosGenerator;
    }

    @Override
    public Lottos generateLottos(int lottoCount) {
        return lottosGenerator.generate(lottoCount);
    }

    @Override
    public Lotto generateLotto(List<Integer> lottoNumbers) {
        List<LottoNumber> numbers = lottoNumbers.parallelStream()
                .map(LottoNumber::userOf)
                .toList();

        return Lotto.userOf(numbers);
    }

    @Override
    public WinningLotto generateWinningLotto(Lotto lotto, LottoNumber lottoNumber) {
        return WinningLotto.of(lotto, lottoNumber);
    }

    @Override
    public Prizes compareByWinningLotto(Lottos lottos, WinningLotto winningLotto) {
        List<MatchDto> matchDtos = lottos.match(winningLotto);

        return Prizes.of(matchDtos);
    }
}