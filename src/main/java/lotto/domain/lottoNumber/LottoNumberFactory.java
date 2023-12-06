package lotto.domain.lottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberFactory {
    private static final List<LottoNumber> allLottoNumbers;

    static {
        allLottoNumbers = LottoNumber.allNumbersOf();
    }

    public static List<LottoNumber> getRandomNumbers(int size) {
        Collections.shuffle(allLottoNumbers);
        return new ArrayList<>(allLottoNumbers.subList(0, size));
    }

    public static LottoNumber getNumber(int number) {
        Collections.sort(allLottoNumbers);

        try {
            return allLottoNumbers.get(number - 1);
        } catch (IndexOutOfBoundsException exception) {
            return new LottoNumber(number);
        }
    }
}