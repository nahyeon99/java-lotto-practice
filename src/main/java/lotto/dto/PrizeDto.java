package lotto.dto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.PrizeType;
import lotto.domain.Prizes;

public record PrizeDto(PrizeType prizeType, Long prizeCount) {

    public static List<PrizeDto> of(Prizes prizes) {
        return Arrays.stream(PrizeType.values())
                .sorted(Comparator.reverseOrder())
                .filter(prizeType -> prizeType != PrizeType.NONE)
                .map(prizeType -> new PrizeDto(prizeType, prizes.getCount(prizeType)))
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isSecond() {
        return prizeType == PrizeType.SECOND;
    }
}
