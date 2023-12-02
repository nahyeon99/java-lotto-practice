package lotto.domain;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lotto.dto.MatchDto;

public class Prizes {
    private final Map<PrizeType, Long> prizes;

    private Prizes(Map<PrizeType, Long> prizes) {
        this.prizes = prizes;
    }

    public static Prizes of(List<MatchDto> matchDtos) {
        EnumMap<PrizeType, Long> prizes = matchDtos.stream()
                .collect(groupToEnumMapCollector());

        return new Prizes(prizes);
    }

    private static Collector<MatchDto, ?, EnumMap<PrizeType, Long>> groupToEnumMapCollector() {
        return Collectors.groupingBy(PrizeType::valueOf,
                () -> new EnumMap<>(PrizeType.class),
                Collectors.counting());
    }

    public BigDecimal calculateReturnRate(Money purchaseAmount) {
        long winningAmount = prizes.keySet().stream()
                .mapToLong(prize -> prize.calculateWinningAmount(prizes.get(prize)))
                .sum();

        return purchaseAmount.calculateReturnRate(winningAmount);
    }
}
