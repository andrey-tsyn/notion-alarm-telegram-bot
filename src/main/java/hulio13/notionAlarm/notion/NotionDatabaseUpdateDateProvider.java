package hulio13.notionAlarm.notion;

import hulio13.notionAlarm.core.Result;
import hulio13.notionAlarm.core.entities.plannedTask.PlannedTask;
import notion.api.v1.NotionClient;
import notion.api.v1.exception.NotionAPIError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class NotionDatabaseUpdateDateProvider {
    private static Logger logger = LoggerFactory.getLogger(NotionDatabaseUpdateDateProvider.class);

    static public Result<LocalDateTime> getDate(PlannedTask task){
        String token, linkToDatabase;

        synchronized (task){
            token = task.getUser().getTokenById("notion");
            linkToDatabase = task.getPlannedTaskDescriptor().dataForAccess;
        }

        NotionClient client = new NotionClient(token);

        try{
            String data = client.retrieveDatabase(linkToDatabase).getLastEditedTime();
            LocalDateTime dateTime = LocalDateTime.parse(data.replace("Z", ""));
            return new Result<>(true, dateTime, null, null);
        }
        catch (NotionAPIError e){
            if (e.getError().getStatus() == 401){
                return new Result<>(false, null, "notion_unauthorized",
                        "Unauthorized, maybe invalid token");
            }
            if (e.getError().getStatus() == 404){
                return new Result<>(false, null, "notion_not_found",
                        "Not found, do you add integration to database?");
            }

            logger.warn("Unhandled answer from notion api");
            throw new RuntimeException(e);
        }
        finally {
            client.close();
        }
    }
}
