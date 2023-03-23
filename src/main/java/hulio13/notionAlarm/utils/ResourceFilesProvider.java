package hulio13.notionAlarm.utils;

import java.io.File;
import java.net.URL;

public class ResourceFilesProvider {
    public static File[] getResourceFolderFiles (String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).listFiles();
    }
}
