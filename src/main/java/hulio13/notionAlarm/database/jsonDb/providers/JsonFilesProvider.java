package hulio13.notionAlarm.database.jsonDb.providers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class JsonFilesProvider {
    static public List<String> getFileListFromFolder(String pathToFolder) throws IOException {
        List<String> telegramIds = Files.list(Paths.get(pathToFolder))
                .map(Path::toFile)
                .filter(file -> file.getName().endsWith(".json"))
                .map(f -> f.getName().replace(".json", ""))
                .collect(Collectors.toList());
        return telegramIds;
    }
}
