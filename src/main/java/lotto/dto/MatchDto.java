package lotto.dto;

public record MatchDto(int matchCount, boolean matchBonus) {
    public static MatchDto of(int matchCount, boolean matchBonus) {
        return new MatchDto(matchCount, matchBonus);
    }
}
