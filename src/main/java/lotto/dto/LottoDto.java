package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;

public record LottoDto(List<Integer> lottoNumbers) {
    public static LottoDto of(String input) {
        return new LottoDto(input);
    }

    public static LottoDto from(String input) {
        return new LottoDto(input);
    }

    public static List<LottoDto> of(List<String> inputs) {
        return inputs.stream()
                .map(LottoDto::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
