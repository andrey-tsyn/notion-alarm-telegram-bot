package hulio13.notionAlarm.database.jsonDb.providers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class JsonFilesProvider {
    static public List<String> getFileListFromFolder(String pathToFolder) throws IOException {
        Path path = Paths.get(pathToFolder);
        if (!Files.exists(path)){
            Files.createDirectory(path);
        }
        List<String> fileNames = Files.list(path)
                .map(Path::toFile)
                .filter(file -> file.getName().endsWith(".json"))
                .map(f -> f.getName().replace(".json", ""))
                .collect(Collectors.toList());
        return fileNames;
    }
}
