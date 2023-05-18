package hulio13.notionAlarm.database.jsonDb.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;
import hulio13.notionAlarm.core.repositories.PlannedTaskPlatformRepository;

import java.io.IOException;

public final class PlannedTaskPlatformDeserializer extends StdDeserializer<PlannedTaskPlatform> {
    private PlannedTaskPlatformDeserializer() {
        super(PlannedTaskPlatform.class);
    }

    @Override
    public PlannedTaskPlatform deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String id = jsonParser.getValueAsString();
        PlannedTaskPlatform platform = PlannedTaskPlatformRepository.getPlatformById(id);
        return platform;
    }
}
