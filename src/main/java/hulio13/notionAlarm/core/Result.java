package hulio13.notionAlarm.core;

public record Result<T>(boolean isSuccess, T object, String error, String message) {
}
