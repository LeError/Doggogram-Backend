package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.services.ImageService;
import com.doggogram.backendsvc.services.StorageService;
import com.doggogram.backendsvc.storage.exceptions.StorageFileNotFoundException;
import com.google.common.io.ByteStreams;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/v1/storage")
public class ImageController {

    private final StorageService storageService;
    private final ImageService imageService;

    public ImageController (StorageService storageService, ImageService imageService) {
        this.storageService = storageService;
        this.imageService = imageService;
    }

    @GetMapping ({"/images/filenames/all", "/images/filenames/all/"})
    public String listUploadedFiles() throws IOException {
        //todo
        return "uploadForm";
    }

    @GetMapping({"/images/load/{filename:.+}", "/images/load/{filename:.+}/"})
    @ResponseBody
    public ResponseEntity serveFile(@PathVariable String filename) {
        try {
            Resource file = storageService.loadAsResource(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(Base64Utils.encodeToString(ByteStreams.toByteArray(file.getInputStream())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/images/upload", "/images/upload/"})
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("user") String user, @RequestParam("bio") String bio, @RequestParam("title") String title) {
        try {
            imageService.addImage(user, title, bio, imageService.getImageName(user, FilenameUtils.getExtension(file.getOriginalFilename())));
            storageService.store(file, imageService.getImageName(user, FilenameUtils.getExtension(file.getOriginalFilename())));
            return new ResponseEntity<>("File Upload Accepted!", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.CONFLICT);
        }
    }

    @ExceptionHandler (StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
