package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.MatchDto;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(final List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<MatchDto> match(WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.toUnmodifiableList());
    }

}
