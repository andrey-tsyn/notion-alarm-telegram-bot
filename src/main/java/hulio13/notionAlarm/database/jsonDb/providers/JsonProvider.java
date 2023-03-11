package hulio13.notionAlarm.database.jsonDb.providers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class JsonProvider {
    private final String pathToFolder;

    public JsonProvider(String pathToFolder) {
        this.pathToFolder = pathToFolder;
    }

    public String readJsonWithName(String fileName){
        Path path = getPathWithName(fileName);

        if (!path.isAbsolute()){
            throw new IllegalArgumentException();
        }

        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeJsonWithName(String fileName, String content) throws IOException {
        Path path = getPathWithName(fileName);

        FileWriter writer = new FileWriter(path.toFile());
        writer.write(content);
        writer.close();
    }

    public void deleteJsonWithName(String fileName){
        try {
            Files.delete(getPathWithName(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getPathWithName(String fileName){
        return Paths.get(pathToFolder, fileName+".json");
    }
}
