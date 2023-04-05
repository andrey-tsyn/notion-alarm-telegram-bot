package hulio13.notionAlarm.telegramBot.tgUserProperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public final class TgUserProperties {
    public final String telegramId;
    private final String userId;
    private String handlerId = "Default";
    private String locale;
    private final Map<String, Object> payload;

    @JsonCreator
    public TgUserProperties(@JsonProperty("userId") String userId,
                            @JsonProperty("telegramId") String telegramId,
                            @JsonProperty("locale") String locale) {
        this(userId, telegramId);
        this.locale = locale;
    }

    public TgUserProperties(String userId, String telegramId) {
        this.userId = userId;
        this.telegramId = telegramId;
        this.payload = new HashMap<>();
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerId(){
        return handlerId;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale(){
        return locale;
    }

    public void clearPayload(){
        payload.clear();
    }

    public Map<String, Object> getPayload(){
        return payload;
    }

    public String getUserId(){
        return userId;
    }
}
