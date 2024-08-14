package bg.softUni.Countries.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Time {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private String returnTime;
    //    every min
    @Scheduled(cron = "*/1 * * * * *")
    private void reportCurrentTime() {
        returnTime = dateFormat.format(new Date());
    }

    public String getCurrentTime() {
        return returnTime;
    }
}
