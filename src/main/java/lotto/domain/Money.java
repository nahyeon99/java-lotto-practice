package lotto.domain;

import java.math.BigDecimal;
import lotto.exception.ExceptionMessage;

public class Money {
    private static final BigDecimal HUNDRED_CALCULATE_RATE = BigDecimal.valueOf(100);
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

    public int calculateLottoCount() {
        return amount.divide(LOTTO_PER_AMOUNT).intValue();
    }

    public BigDecimal multiply(long number) {
        return amount.multiply(BigDecimal.valueOf(number));
    }

    public BigDecimal calculateReturnRate(long winningAmount) {
        return BigDecimal.valueOf(winningAmount).divide(amount).multiply(HUNDRED_CALCULATE_RATE);
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