package lotto.domain;

import java.util.Arrays;
import java.util.function.Predicate;
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
    private Predicate<MatchDto> matchCondition;

    PrizeType(int matchNumber, boolean matchBonus, int prizeAmount) {
        this.matchNumber = matchNumber;
        this.matchBonus = matchBonus;
        this.prizeAmount = Money.wons(prizeAmount);
        setMatchCondition(matchNumber, matchBonus);
    }

    private void setMatchCondition(int matchNumber, boolean matchBonus) {
        if (matchBonus) {
            this.matchCondition = (matchDto -> isNumber(matchDto.matchCount()) && isBonus(matchDto.matchBonus()));
            return;
        }
        this.matchCondition = (matchDto -> isNumber(matchDto.matchCount()));
    }

    public static PrizeType getPrizeType(MatchDto matchDto) {
        return Arrays.stream(values())
                .filter(prize -> prize.matchCondition.test(matchDto))
                .findAny()
                .orElse(NONE);
    }

    public Money getPrizeAmount() {
        return prizeAmount;
    }

    public long calculateAmount(long count) {
        return prizeAmount.multiply(count).longValue();
    }

    private boolean isNumber(int number) {
        return this.matchNumber == number;
    }

    private boolean isBonus(boolean bonus) {
        return this.matchBonus == bonus;
    }
}
