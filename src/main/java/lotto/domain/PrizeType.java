package lotto.domain;

import java.util.Arrays;
import lotto.dto.MatchDto;

public enum PrizeType {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FORTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0),
    ;

    private final int matchNumber;
    private final boolean matchBonus;
    private final Money prizeAmount;

    PrizeType(int matchNumber, boolean matchBonus, int prizeAmount) {
        this.matchNumber = matchNumber;
        this.matchBonus = matchBonus;
        this.prizeAmount = Money.wons(prizeAmount);
    }

    public static PrizeType valueOf(MatchDto matchDto) {
        return Arrays.stream(values())
                .filter(prize -> prize.matchNumber == matchDto.matchNumber())
                .filter(prize -> prize.matchBonus == matchDto.matchBonus())
                .findAny()
                .orElse(NONE);
    }

    public long calculateWinningAmount(long count) {
        return prizeAmount.multiply(count).longValue();
    }
}