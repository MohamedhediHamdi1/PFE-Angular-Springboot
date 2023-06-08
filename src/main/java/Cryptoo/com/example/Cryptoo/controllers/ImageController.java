package Cryptoo.com.example.Cryptoo.controllers;

import Cryptoo.com.example.Cryptoo.entities.ImageEntity;
import Cryptoo.com.example.Cryptoo.repositories.ImageMetadataRepository;
import Cryptoo.com.example.Cryptoo.responses.ResetPasswordResponse;
import Cryptoo.com.example.Cryptoo.services.FileStorageService;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    @Autowired
    Utils util;
    @Autowired
    LogsService logsService;


    @PostMapping
    public ResponseEntity<ResetPasswordResponse> uploadImage(@RequestParam("file") MultipartFile file) {
        if (!file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            String fileName = fileStorageService.saveFile(file);
            ImageEntity metadata = new ImageEntity();
            metadata.setName(fileName);
            metadata.setId(fileName);
            metadata.setType(file.getContentType());
            metadata.setSize(file.getSize());
            imageMetadataRepository.save(metadata);
            ResetPasswordResponse response=new ResetPasswordResponse();
            response.setResponse(fileName);
            logsService.addToLogs("The image was successfully uploaded to the server.");
            return new ResponseEntity<ResetPasswordResponse>(response,HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            logsService.addToLogs("The image failed uploading to the server.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") String id) {
        try {
            // Retrieve the image metadata from the database table
            try{
                ImageEntity metadata1 = imageMetadataRepository.findById(id);
            }catch (RuntimeException e){
                throw new RuntimeException();
            }
            ImageEntity metadata = imageMetadataRepository.findById(id);

            // Read the file from the directory
            Resource resource = fileStorageService.getFile(metadata.getName());
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(metadata.getType())).body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

