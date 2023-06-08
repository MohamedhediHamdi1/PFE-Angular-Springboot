package Cryptoo.com.example.Cryptoo.services.impl;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogsService {
    private static final long MAX_FILE_SIZE =  1024; // 5MB
    private static final String LOGS_DIRECTORY = "src/logs/";
    private static final String LOG_FILE_PREFIX = "file";
    private static final String LOG_FILE_EXTENSION = ".log";

    public void addToLogs(String content) {
        String currentFilePath = getCurrentLogFilePath();
        File currentFile = new File(currentFilePath);

        try {

            try (FileWriter writer = new FileWriter(currentFile, true)) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = now.format(formatter);
                writer.write(formattedDateTime + " : " + content + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing logs.");
        }
    }

    private String getCurrentLogFilePath() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        return LOGS_DIRECTORY + LOG_FILE_PREFIX + "_" + formattedDate + LOG_FILE_EXTENSION;
    }

}
