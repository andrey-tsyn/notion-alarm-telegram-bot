package hulio13.notionAlarm.database.jsonDb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class UserIdsProvider {
    static public List<String> getIdsFromDatabaseFolder(String pathToFolder){
        try {
            List<String> telegramIds = Files.list(Paths.get(pathToFolder))
                    .map(Path::toFile)
                    .filter(file -> file.getName().contains(".json"))
                    .map(f -> f.getName().replace(".json", ""))
                    .collect(Collectors.toList());
            return telegramIds;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
