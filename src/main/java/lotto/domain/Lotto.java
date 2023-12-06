package lotto.domain;

import java.util.List;
import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoNumber.LottoNumberFactory;
import lotto.exception.ExceptionMessage;

public class Lotto {
    private static final int LOTTO_COUNTS = 6;
    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        validateNumbersSize(numbers.size());
        validateDuplicateNumber(numbers);

        this.numbers = numbers;
    }

    public static Lotto randomOf() {
        return new Lotto(LottoNumberFactory.getRandomNumbers(LOTTO_COUNTS));
    }

    public static Lotto from(List<Integer> lottoNumbers) {
        List<LottoNumber> numbers = lottoNumbers.stream()
                .map(LottoNumberFactory::getNumber)
                .toList();

        return new Lotto(numbers);
    }

    private void validateNumbersSize(int count) {
        if (count != LOTTO_COUNTS) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicateNumber(List<LottoNumber> numbers) {
        if ((int) numbers.stream().distinct().count() != LOTTO_COUNTS) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DUPLICATE_LOTTO_NUMBER.getKorean());
        }
    }
}
