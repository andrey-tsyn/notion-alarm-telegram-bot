package hulio13.notionAlarm.database.jsonDb;

import hulio13.notionAlarm.database.jsonDb.io.JsonSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class DumpingScheduler {

    private static final Logger logger = LoggerFactory.getLogger(DumpingScheduler.class);

    static public void start(JsonSaver saver, int delayToSaveInFolder, TimeUnit timeUnit) {
        Thread saveThread = new Thread(() -> {
            try {
                saver.saveAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                logger.error(String.format(
                        "Can't save entities. Thread with saver killed. " +
                                "Exception message: %s",
                        e.getMessage()
                ));
                try {
                    throw e;
                } catch (Throwable ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

        saveThread.setUncaughtExceptionHandler(handler);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(
                saveThread,
                0,
                delayToSaveInFolder,
                timeUnit);

        Runtime.getRuntime().addShutdownHook(saveThread);
    }
}
