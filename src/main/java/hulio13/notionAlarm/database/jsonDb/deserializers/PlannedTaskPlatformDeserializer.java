package hulio13.notionAlarm.database.jsonDb.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;
import hulio13.notionAlarm.core.repositories.PlannedTaskPlatformRepository;

import java.io.IOException;

public final class PlannedTaskPlatformDeserializer extends StdDeserializer<PlannedTaskPlatform> {
    protected PlannedTaskPlatformDeserializer() {
        super(PlannedTaskPlatform.class);
    }

    @Override
    public PlannedTaskPlatform deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String id = jsonParser.getValueAsString();
        PlannedTaskPlatform platform = PlannedTaskPlatformRepository.getPlatformById(id);
        return platform;
    }
}
