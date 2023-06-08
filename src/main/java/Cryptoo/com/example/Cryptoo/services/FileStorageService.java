package Cryptoo.com.example.Cryptoo.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileStorageService {
    String saveFile(MultipartFile file) throws IOException;

    String saveVideo(MultipartFile file) throws IOException;

    Resource getFile(String fileName) throws FileNotFoundException;

    void deleteFile(String fileName) throws FileNotFoundException;
}
