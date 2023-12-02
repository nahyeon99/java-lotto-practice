package lotto.service;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;
import lotto.domain.Prizes;
import lotto.domain.WinningLotto;

public interface LottoService {
    Lottos generateLottos(int lottoCount);

    Lotto generateLotto(List<Integer> lottoNumbers);

    WinningLotto generateWinningLotto(Lotto lotto, LottoNumber lottoNumber);

    Prizes compareByWinningLotto(Lottos lottos, WinningLotto winningLotto);
}