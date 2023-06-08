package Cryptoo.com.example.Cryptoo.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/admin/logs")
public class LogsController {
    @Value("${logs.directory}")
    private String logsDirectory;

    @GetMapping("/{date}")
    public ResponseEntity<FileSystemResource> getLogFile(@PathVariable String date) {
        String logFilePath = getLogFilePath(date);
        File logFile = new File(logFilePath);
        if (logFile.exists()) {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + logFile.getName())
                    .body(new FileSystemResource(logFile));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private String getLogFilePath(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate logDate = LocalDate.parse(date, formatter);
        String formattedDate = logDate.format(formatter);
        return logsDirectory + "file_" + formattedDate + ".log";
    }
}
