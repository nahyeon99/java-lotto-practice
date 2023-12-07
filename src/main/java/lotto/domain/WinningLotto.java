package lotto.domain;

import static lotto.exception.ExceptionMessage.INVALID_DUPLICATE_BONUS_NUMBER;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.dto.MatchDto;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    private WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        validateDuplicate(winningLotto, bonusNumber);

        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto lotto, LottoNumber lottoNumber) {
        return new WinningLotto(lotto, lottoNumber);
    }

    public MatchDto match(Lotto lotto) {
        return MatchDto.of(lotto.match(winningLotto), lotto.contains(bonusNumber));
    }

    private void validateDuplicate(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_DUPLICATE_BONUS_NUMBER.getKorean());
        }
    }
}
