package hulio13.notionAlarm.core.registrars;

import hulio13.notionAlarm.core.emptyImplementations.EmptyPlannedTaskHandler;
import hulio13.notionAlarm.core.repositories.PlannedTaskHandlerRepository;
import hulio13.notionAlarm.notion.NotionPlannedTaskHandler;

public final class PlannedTaskHandlerRegistrar {
    static public void registerPlatforms(){
        PlannedTaskHandlerRepository.addHandler(new EmptyPlannedTaskHandler().getId(), new EmptyPlannedTaskHandler());
        PlannedTaskHandlerRepository.addHandler(new NotionPlannedTaskHandler().getId(), new NotionPlannedTaskHandler());

    }
}