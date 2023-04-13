package hulio13.notionAlarm.configuration.exceptions;

public class ConfigureMethodsTooMuchException extends RuntimeException {
    public ConfigureMethodsTooMuchException() {
    }

    public ConfigureMethodsTooMuchException(String message) {
        super(message);
    }

    public ConfigureMethodsTooMuchException(String message, Throwable cause) {
        super(message, cause);
    }
}
