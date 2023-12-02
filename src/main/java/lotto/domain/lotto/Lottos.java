package lotto.domain.lotto;

import java.util.List;
import lotto.domain.WinningLotto;
import lotto.dto.LottoDto;
import lotto.dto.MatchDto;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(final List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public List<MatchDto> match(WinningLotto winningLotto) {
        return lottos.parallelStream()
                .map(lotto -> lotto.match(winningLotto))
                .toList();
    }

    public List<LottoDto> getLottos() {
        return lottos.stream()
                .map(Lotto::getLotto)
                .map(LottoDto::new)
                .toList();
    }
}