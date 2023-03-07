package hulio13.notionAlarm.database.jsonDb.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hulio13.notionAlarm.core.entities.plannedTask.abstraction.PlannedTaskHandler;
import hulio13.notionAlarm.core.repositories.PlannedTaskHandlerRepository;

import java.io.IOException;

public final class PlannedTaskHandlerDeserializer extends StdDeserializer<PlannedTaskHandler> {
    protected PlannedTaskHandlerDeserializer() {
        super(PlannedTaskHandler.class);
    }

    @Override
    public PlannedTaskHandler deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String id = jsonParser.getValueAsString();
        PlannedTaskHandler platform = PlannedTaskHandlerRepository.getHandlerById(id);
        return platform;
    }
}
