package hulio13.notionAlarm.database.jsonDb.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskHandler;

import java.io.IOException;

public final class PlannedTaskHandlerSerializer extends JsonSerializer<PlannedTaskHandler> {
    @Override
    public void serialize(PlannedTaskHandler platform, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(platform.getId());
    }
}
