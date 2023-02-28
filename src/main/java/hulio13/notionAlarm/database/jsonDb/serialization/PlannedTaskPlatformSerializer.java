package hulio13.notionAlarm.database.jsonDb.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskPlatform;

import java.io.IOException;

public final class PlannedTaskPlatformSerializer extends JsonSerializer<PlannedTaskPlatform> {
    @Override
    public void serialize(PlannedTaskPlatform platform, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(platform.getId());
    }
}
