package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.exceptions.FileStorageException;
import Cryptoo.com.example.Cryptoo.services.FileStorageService;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class LocalFileStorageService implements FileStorageService {

    private final Path fileStorageLocation;


    @Autowired
    Utils util;

    @Autowired
    public LocalFileStorageService(@Value("${file.upload-dir}") String fileStorageLocation) {
        this.fileStorageLocation = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {

        String fileName = util.generateStringId(32)+".png";
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileStorageException("Failed to store file " + fileName, ex);
        }
        return fileName;
    }

    @Override
    public String saveVideo(MultipartFile file) throws IOException {

        String fileName = util.generateStringId(32)+".mp4";
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileStorageException("Failed to store file " + fileName, ex);
        }
        return fileName;
    }

    @Override
    public Resource getFile(String fileName) throws FileNotFoundException {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

    @Override
    public void deleteFile(String fileName) throws FileNotFoundException {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (IOException ex) {
            throw new FileStorageException("Failed to delete file " + fileName, ex);
        }
    }


}
