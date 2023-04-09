package hulio13.notionAlarm.configuration;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationMap {
    private final HashMap<String, String> map = new HashMap<>();

    public ConfigurationMap(Map<String, String> map) {
        this.map.putAll(map);
    }

    public String getValueById(String id){
        return map.get(id);
    }
}
