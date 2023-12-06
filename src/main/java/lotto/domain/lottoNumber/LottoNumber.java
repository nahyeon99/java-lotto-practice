package lotto.domain.lottoNumber;

import java.util.ArrayList;
import java.util.List;
import lotto.exception.ExceptionMessage;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int number;

    LottoNumber(int number) {
        validateNumberSize(number);

        this.number = number;
    }

    static List<LottoNumber> allNumbersOf() {
        List<LottoNumber> numbers = new ArrayList<>();

        for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
            numbers.add(new LottoNumber(number));
        }
        return numbers;
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }

    private void validateNumberSize(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_LOTTO_NUMBER_RANGE.getKorean());
        }
    }
}
