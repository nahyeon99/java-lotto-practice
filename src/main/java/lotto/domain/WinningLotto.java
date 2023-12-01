package lotto.domain;

import static lotto.exception.ExceptionMessage.*;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    private WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateDuplicateBonusNumber(lotto, bonusNumber);

        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto lotto, LottoNumber lottoNumber) {
        return new WinningLotto(lotto, lottoNumber);
    }

    private void validateDuplicateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_DUPLICATE_BONUS_LOTTO_NUMBER.getKorean());
        }
    }
}
