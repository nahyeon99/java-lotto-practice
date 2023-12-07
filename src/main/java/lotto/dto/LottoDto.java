package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public record LottoDto(List<Integer> lottoNumbers) {
    public static List<LottoDto> of(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(LottoDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private static LottoDto from(Lotto lotto) {
        return new LottoDto(lotto.getValues());
    }
}
