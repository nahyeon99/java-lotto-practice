package lotto.domain.lotto;

import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_RANGE;

import java.util.Objects;
import lotto.exception.ExceptionMessage;

public class LottoNumber {
    static final int MIN_LOTTO_NUMBER = 1;
    static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber computerOf(int number) {
        return new LottoNumber(number);
    }

    public static LottoNumber userOf(int number) {
        validateNumberRange(number);
        return new LottoNumber(number);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LottoNumber)) {
            return false;
        }
        LottoNumber that = (LottoNumber) other;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public int getLottoNumber() {
        return number;
    }

    private static void validateNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE.getKorean());
        }
    }
}
