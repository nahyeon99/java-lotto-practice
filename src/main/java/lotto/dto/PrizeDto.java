package lotto.dto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lotto.domain.PrizeType;
import lotto.domain.Prizes;

public record PrizeDto(PrizeType prizeType, long prizeCount) {
    public PrizeDto(PrizeType prizeType, long prizeCount) {
        this.prizeType = prizeType;
        this.prizeCount = prizeCount;
    }

    public static List<PrizeDto> from(Prizes prizes) {
        return Arrays.stream(PrizeType.values())
                .filter(prizeType -> !prizeType.equals(PrizeType.NONE))
                .sorted(Comparator.reverseOrder())
                .map(prize -> new PrizeDto(prize, prizes.getCount(prize)))
                .toList();
    }
}
