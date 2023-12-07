package lotto.domain;

import java.math.BigDecimal;
import lotto.exception.ExceptionMessage;

public class Money {
    private static final BigDecimal LOTTO_PER_AMOUNT = BigDecimal.valueOf(1_000);
    private static final BigDecimal MAX_LOTTO_PURCHASE_AMOUNT = BigDecimal.valueOf(300_000);

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wonsByLotto(long amount) {
        validateLottoAmountUnit(BigDecimal.valueOf(amount));
        validateLottoAmountSize(BigDecimal.valueOf(amount));

        return new Money(BigDecimal.valueOf(amount));
    }

    public BigDecimal multiply(long count) {
        return amount.multiply(BigDecimal.valueOf(count));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private static void validateLottoAmountUnit(BigDecimal amount) {
        if (amount.remainder(LOTTO_PER_AMOUNT).intValue() != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MONEY_UNIT.getKorean());
        }
    }

    private static void validateLottoAmountSize(BigDecimal amount) {
        if (amount.compareTo(LOTTO_PER_AMOUNT) < 0 || amount.compareTo(MAX_LOTTO_PURCHASE_AMOUNT) > 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MONEY_SIZE.getKorean());
        }
    }

    public int calculateLottoCount() {
        return amount.divide(LOTTO_PER_AMOUNT).intValue();
    }

    public BigDecimal rate(long numerator) {
        return BigDecimal.valueOf(numerator).divide(amount);
    }
}