package hulio13.notionAlarm.database.jsonDb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hulio13.notionAlarm.core.entities.User;
import hulio13.notionAlarm.database.exceptions.EntityNotFoundException;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

class UserJsonHandler {
    public static final String FOLDER_NAME = "OmegaSecureDatabase/";
    private ObjectMapper objectMapper;
    public UserJsonHandler() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public boolean serializeInFile(String name, User user) {
        FileWriter writer;
        try{
            String str = objectMapper.writeValueAsString(user);
            try {
                Files.createDirectory(Paths.get("./" + FOLDER_NAME));
            }
            catch (FileAlreadyExistsException e){}
            writer = new FileWriter(FOLDER_NAME + name + ".json");

            writer.write(str);
            writer.close();
            return true;
        }
        catch (IOException e){
            return false;
        }
    }

    public User deserializeFromFile(String name) throws EntityNotFoundException{
        try{
            File file = new File(FOLDER_NAME + name + ".json");
            User user = objectMapper.readValue(file, User.class);
            for (var n :
                    user.getNotifications()) {
                n.setUserIfNull(user);
            }
            return user;
        }
        catch (FileNotFoundException e){
            throw new EntityNotFoundException(name, "File does not exist");
        }
        catch (IOException e){
            throw new EntityNotFoundException();
        }
    }
}
