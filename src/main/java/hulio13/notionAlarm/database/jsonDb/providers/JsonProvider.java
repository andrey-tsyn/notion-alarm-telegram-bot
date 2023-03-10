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

    public String readJsonWithName(String name){
        Path path = getPathWithName(name);

        if (!path.isAbsolute()){
            throw new IllegalArgumentException();
        }

        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeJsonWithName(String name, String content) throws IOException {
        Path path = getPathWithName(name);

        FileWriter writer = new FileWriter(path.toFile());
        writer.write(content);
        writer.close();
    }

    public void deleteJsonWithName(String name){
        try {
            Files.delete(getPathWithName(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getPathWithName(String name){
        return Paths.get(pathToFolder, name+".json");
    }
}
