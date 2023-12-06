package lotto.util;

import static lotto.exception.ExceptionMessage.INVALID_BLANK;
import static lotto.exception.ExceptionMessage.INVALID_NUMBER_FORMAT;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class InputUtil {
    private InputUtil() {
        throw new AssertionError("생성자 호출이 불가합니다.");
    }

    public static int readNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT.getKorean());
        }
    }

    public static String readLine() {
        String input = Console.readLine();

        if (Objects.isNull(input)) {
            throw new IllegalArgumentException(INVALID_BLANK.getKorean());
        }

        if (input.isBlank()) {
            throw new IllegalArgumentException(INVALID_BLANK.getKorean());
        }
        return input;
    }

    public static List<Integer> readNumbers(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .map(InputUtil::readNumber)
                .toList();
    }

    public static <T> T repeatUntilValidInput(Supplier<T> repeatSupplier) {
        while (true) {
            try {
                return repeatSupplier.get();
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}