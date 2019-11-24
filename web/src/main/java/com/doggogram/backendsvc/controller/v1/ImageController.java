package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.dto.ImageListDTO;
import com.doggogram.backendsvc.services.ImageService;
import com.doggogram.backendsvc.services.StorageService;
import com.doggogram.backendsvc.util.Util;
import com.doggogram.backendsvc.util.exceptions.EntityCorruptedException;
import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/api/v1/")
public class ImageController {

    private final StorageService storageService;
    private final ImageService imageService;

    public ImageController (StorageService storageService, ImageService imageService) {
        this.storageService = storageService;
        this.imageService = imageService;
    }

    @GetMapping ({"/images/$count", "/images/$count/"})
    public ResponseEntity<Integer> getCount() {
        return new ResponseEntity<>(imageService.count(), HttpStatus.OK);
    }

    @PostMapping({"/images/upload", "/images/upload/"})
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("user") String user, @RequestParam("bio") String bio, @RequestParam("title") String title) {
        try {
            imageService.addImage(user, title, bio, Util.getImageName(user, FilenameUtils.getExtension(file.getOriginalFilename())));
            storageService.store(file, Util.getImageName(user, FilenameUtils.getExtension(file.getOriginalFilename())));
            return new ResponseEntity<>("File Upload Accepted!", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.CONFLICT);
        }
    }

    @GetMapping ({"/images/all", "/images/all/"})
    public ResponseEntity listUploadedFiles() {
        return new ResponseEntity<>(new ImageListDTO(imageService.getAllItems()) , HttpStatus.OK);
    }

    @GetMapping({"/images/file/{filename:.+}", "/images/file/{filename:.+}/"})
    @ResponseBody
    public ResponseEntity serveFile(@PathVariable String filename) {
        try {
            Resource file = storageService.loadAsResource(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(Base64Utils.encodeToString(ByteStreams.toByteArray(file.getInputStream())));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping({"/images/image/{imageId}", "/images/image/{imageId}/"})
    public ResponseEntity getImage(@PathVariable long imageId) {
        try {
            return new ResponseEntity(imageService.getItemById(imageId), HttpStatus.OK);
        } catch (EntityCorruptedException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.GONE);
        }
    }

}
