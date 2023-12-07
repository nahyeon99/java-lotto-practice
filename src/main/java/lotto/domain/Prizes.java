package lotto.domain;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lotto.dto.MatchDto;

public class Prizes {
    private final EnumMap<PrizeType, Long> prizes;

    private Prizes(final EnumMap<PrizeType, Long> prizes) {
        this.prizes = prizes;
    }

    public static Prizes of(final List<MatchDto> matchDtos) {
        EnumMap<PrizeType, Long> prizes = matchDtos.stream()
                .collect(groupToEnumMapCollector());

        return new Prizes(prizes);
    }

    private static Collector<MatchDto, ?, EnumMap<PrizeType, Long>> groupToEnumMapCollector() {
        return Collectors.groupingBy(PrizeType::getPrizeType,
                () -> new EnumMap<>(PrizeType.class),
                Collectors.counting());
    }

    public Long getCount(PrizeType prizeType) {
        return prizes.get(prizeType);
    }

    public BigDecimal calculateReturnRate(Money purchaseAmount) {
        return purchaseAmount.rate(calculateTotalAmount());
    }

    private long calculateTotalAmount() {
        return prizes.keySet().stream()
                .mapToLong(prize -> prize.calculateAmount(prizes.get(prize)))
                .sum();
    }
}
