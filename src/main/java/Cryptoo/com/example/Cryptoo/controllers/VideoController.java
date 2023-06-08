package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.ImageEntity;
import Cryptoo.com.example.Cryptoo.repositories.ImageMetadataRepository;
import Cryptoo.com.example.Cryptoo.responses.ResetPasswordResponse;
import Cryptoo.com.example.Cryptoo.services.FileStorageService;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    @Autowired
    Utils util;
    @Autowired
    LogsService logsService;

    @PostMapping
    public ResponseEntity<ResetPasswordResponse> uploadVideo(@RequestParam("file") MultipartFile file) {
        if (!file.getContentType().startsWith("video/")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            String fileName = fileStorageService.saveVideo(file);
            ImageEntity metadata = new ImageEntity();
            metadata.setName(fileName);
            metadata.setId(fileName);
            metadata.setType(file.getContentType());
            metadata.setSize(file.getSize());
            imageMetadataRepository.save(metadata);
            ResetPasswordResponse response=new ResetPasswordResponse();
            response.setResponse(fileName);
            logsService.addToLogs("Video "+fileName+" uploaded sucsufully to server");
            return new ResponseEntity<ResetPasswordResponse>(response, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/edit/{video}")
    public ResponseEntity<ResetPasswordResponse> EditVideo(@RequestParam("file") MultipartFile file,@PathVariable String video) {
        if (!file.getContentType().startsWith("video/")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            String fileName = fileStorageService.saveVideo(file);
            ImageEntity metadata = new ImageEntity();
            metadata.setName(fileName);
            metadata.setId(fileName);
            metadata.setType(file.getContentType());
            metadata.setSize(file.getSize());
            imageMetadataRepository.save(metadata);
            fileStorageService.deleteFile(video);
            ResetPasswordResponse response=new ResetPasswordResponse();
            response.setResponse(fileName);
            logsService.addToLogs("Video "+fileName+" uploaded sucsufully to server");
            return new ResponseEntity<ResetPasswordResponse>(response, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
