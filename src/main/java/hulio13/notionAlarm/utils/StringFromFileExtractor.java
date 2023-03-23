package hulio13.notionAlarm.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class StringFromFileExtractor {
    static public String extractString(File file){
        if (!file.isFile()){
            throw new IllegalArgumentException();
        }

        try {
            return Files.readString(file.getAbsoluteFile().toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
