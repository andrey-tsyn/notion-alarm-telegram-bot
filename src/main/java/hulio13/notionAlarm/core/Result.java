package hulio13.notionAlarm.core;

import java.util.Objects;

public final class Result<T> {
    private final boolean isSuccess;
    private final T object;
    private final String error;
    private final String message;

    public Result(boolean isSuccess, T object, String error, String message) {
        this.isSuccess = isSuccess;
        this.object = object;
        this.error = error;
        this.message = message;
    }

    /**
     * Use if result is successful.
     */
    public Result(T object){
        this(true, object, "", "");
    }

    /**
     * Use if result is not successful.
     */
    public Result(String error, String message){
        this(false, null, error, message);
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public T getObject() {
        return object;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Result) obj;
        return this.isSuccess == that.isSuccess &&
                Objects.equals(this.object, that.object) &&
                Objects.equals(this.error, that.error) &&
                Objects.equals(this.message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSuccess, object, error, message);
    }

    @Override
    public String toString() {
        return "Result[" +
                "isSuccess=" + isSuccess + ", " +
                "object=" + object + ", " +
                "error=" + error + ", " +
                "message=" + message + ']';
    }
}
