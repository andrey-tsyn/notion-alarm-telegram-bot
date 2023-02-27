package hulio13.notionAlarm.database.jsonDb.providers;

import hulio13.notionAlarm.database.exceptions.EntityNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class UserJsonProvider {
    private final String pathToFolder;

    public UserJsonProvider(String pathToFolder) {
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

    public void writeJsonWithName(String name, String content){
        Path path = getPathWithName(name);

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
