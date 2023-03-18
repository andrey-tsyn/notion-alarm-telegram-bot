package hulio13.notionAlarm.database.jsonDb;

import hulio13.notionAlarm.database.jsonDb.io.JsonSaver;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class UsersDumpingScheduler {
    static public void start(JsonSaver saver, int delayToSaveInFolder, TimeUnit timeUnit){
        Thread saveThread = new Thread(() -> {
            try {
                saver.saveAll();
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
        });

        Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LoggerFactory.getLogger(UsersDumpingScheduler.class).error(String.format(
                        "Can't save users. Thread with saver killed. Exception message: %s", e.getMessage()
                ));
                try {
                    throw e;
                } catch (Throwable ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(
                saveThread,
                0,
                delayToSaveInFolder,
                timeUnit);

        Runtime.getRuntime().addShutdownHook(saveThread);
    }
}
