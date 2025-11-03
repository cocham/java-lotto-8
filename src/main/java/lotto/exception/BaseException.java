package lotto.exception;

public abstract class BaseException extends IllegalArgumentException {
    public BaseException(String message) {
        super(ensureErrorPrefix(message));
    }

    private static String ensureErrorPrefix(String message) {
        final String PREFIX = "[ERROR]";
        if (message.startsWith(PREFIX)) {
            return message;
        }

        return PREFIX + ": " + message;
    }
}
