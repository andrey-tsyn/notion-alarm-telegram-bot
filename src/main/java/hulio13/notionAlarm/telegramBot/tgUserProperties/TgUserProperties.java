package hulio13.notionAlarm.telegramBot.tgUserProperties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public final class TgUserProperties {
    public final String telegramId;
    private final String userId;
    private String handlerId = "DefaultHandler";
    private String locale;
    private final Map<String, Object> payload;

    @JsonCreator
    public TgUserProperties(@JsonProperty("userId") String userId,
                            @JsonProperty("telegramId") String telegramId,
                            @JsonProperty("handlerId") String handlerId,
                            @JsonProperty("locale") String locale,
                            @JsonProperty("payload") Map<String, Object> payload) {
        this.userId = userId;
        this.telegramId = telegramId;
        this.handlerId = handlerId;
        this.locale = locale;
        this.payload = payload;
    }

    public TgUserProperties(String userId, String telegramId, String handlerId, String locale) {
        this(userId, telegramId, handlerId, locale, new HashMap<>());
    }

    public TgUserProperties(String userId, String telegramId, String locale){
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

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void clearPayload(){
        payload.clear();
    }

    public Map<String, Object> getPayload(){
        return payload;
    }
}
