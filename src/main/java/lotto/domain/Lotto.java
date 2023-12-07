package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    public static List<Lotto> randomOf(int purchaseCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseCount; i++) {
            lottos.add(new Lotto(LottoNumberFactory.getRandomNumbers(LOTTO_COUNTS)));
        }

        return lottos;
    }

    public static Lotto from(List<Integer> lottoNumbers) {
        List<LottoNumber> numbers = lottoNumbers.stream()
                .map(LottoNumberFactory::getNumber)
                .toList();

        return new Lotto(numbers);
    }

    public List<Integer> getValues() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.stream()
                .anyMatch(number -> lottoNumber.equals(number));
    }

    public int match(Lotto other) {
        return (int) numbers.stream()
                .filter(number -> other.contains(number))
                .count();
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
