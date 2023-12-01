package lotto.exception;

public enum ExceptionMessage {
    INVALID_BLANK("필수 입력 항목입니다."),
    INVALID_NUMBER_FORMAT("정수로 입력해 주세요."),
    INVALID_MONEY_UNIT("구입 금액은 1,000원 단위로 입력해 주세요."),
    INVALID_MONEY_SIZE("구입 금액은 1,000원 이상 300,000원 이하로 입력해 주세요."),
    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1~45 범위로 입력해 주세요."),
    INVALID_DUPLICATE_LOTTO_NUMBER("로또 번호는 중복 없이 입력해 주세요."),
    INVALID_DUPLICATE_BONUS_LOTTO_NUMBER("보너스 번호와 로또 번호는 중복 없이 입력해 주세요."),
    ;

    private final String korean;

    ExceptionMessage(String message) {
        this.korean = message;
    }

    public String getKorean() {
        return korean;
    }
}
