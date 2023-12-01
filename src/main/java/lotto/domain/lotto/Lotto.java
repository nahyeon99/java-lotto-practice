package lotto.domain.lotto;

import static lotto.exception.ExceptionMessage.INVALID_DUPLICATE_LOTTO_NUMBER;
import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_SIZE;

import java.util.List;

public class Lotto {
    static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto computerOf(List<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public static Lotto userOf(List<LottoNumber> lottoNumbers) {
        validateNumbersSize(lottoNumbers);
        validateDuplicateNumber(lottoNumbers);

        return new Lotto(lottoNumbers);
    }

    private static void validateNumbersSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_SIZE.getKorean());
        }
    }

    private static void validateDuplicateNumber(List<LottoNumber> numbers) {
        if ((int) numbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalStateException(INVALID_DUPLICATE_LOTTO_NUMBER.getKorean());
        }
    }
}
