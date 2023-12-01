package lotto.domain;

import java.math.BigDecimal;
import lotto.exception.ExceptionMessage;

public class Money {
    public static final Money ZERO = Money.wons(0);
    private static final Money MINUS_ONE = Money.wons(-1);
    private static final BigDecimal LOTTO_PER_AMOUNT = BigDecimal.valueOf(1_000);
    private static final BigDecimal MAX_LOTTOS_PURCHASE_AMOUNT = BigDecimal.valueOf(300_000);

    private final BigDecimal amount;

    private Money(final BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(final long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money createMoneyByPurchaseLottos(final int amount) {
        validateLottosAmountUnit(amount);
        validateLottosAmountSize(amount);

        return new Money(BigDecimal.valueOf(amount));
    }

    public BigDecimal calculateLottoCount() {
        return amount.divide(LOTTO_PER_AMOUNT);
    }

    private static void validateLottosAmountSize(int amount) {
        if (amount < LOTTO_PER_AMOUNT.intValue() || amount > MAX_LOTTOS_PURCHASE_AMOUNT.intValue()) {
            throw new IllegalStateException(ExceptionMessage.INVALID_MONEY_SIZE.getKorean());
        }
    }

    private static void validateLottosAmountUnit(int amount) {
        if (amount % LOTTO_PER_AMOUNT.intValue() != 0) {
            throw new IllegalStateException(ExceptionMessage.INVALID_MONEY_UNIT.getKorean());
        }
    }
}