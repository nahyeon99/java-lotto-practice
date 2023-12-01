package lotto.domain;

import java.math.BigDecimal;
import lotto.exception.ExceptionMessage;

public class Money {
    public static final Money ZERO = Money.wons(0);
    private static final Money MINUS_ONE = Money.wons(-1);
    private static final long MIN_LOTTOS_PURCHASE_AMOUNT = 1_000;
    private static final long MAX_LOTTOS_PURCHASE_AMOUNT = 300_000;
    private static final long LOTTO_PER_AMOUNT = 1_000;

    private final BigDecimal amount;

    private Money(final BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(final long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money createMoneyByPurchaseLottos(final long amount) {
        validateLottosAmountUnit(amount);
        validateLottosAmountSize(amount);

        return new Money(BigDecimal.valueOf(amount));
    }

    private static void validateLottosAmountSize(long amount) {
        if (amount < MIN_LOTTOS_PURCHASE_AMOUNT || amount > MAX_LOTTOS_PURCHASE_AMOUNT) {
            throw new IllegalStateException(ExceptionMessage.INVALID_MONEY_SIZE.getKorean());
        }
    }

    private static void validateLottosAmountUnit(long amount) {
        if (amount / LOTTO_PER_AMOUNT != 0) {
            throw new IllegalStateException(ExceptionMessage.INVALID_MONEY_UNIT.getKorean());
        }
    }
}