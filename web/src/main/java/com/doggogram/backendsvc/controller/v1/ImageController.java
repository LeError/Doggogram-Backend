package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.dto.ImageListDTO;
import com.doggogram.backendsvc.dto.UserImagesDTO;
import com.doggogram.backendsvc.services.ImageService;
import com.doggogram.backendsvc.services.JwtTokenService;
import com.doggogram.backendsvc.util.Util;
import com.doggogram.backendsvc.util.exceptions.ControllerCountException;
import com.doggogram.backendsvc.util.exceptions.ImageUploadException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Controller
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;
    private final JwtTokenService jwtTokenService;

    public ImageController (ImageService imageService, JwtTokenService jwtTokenService) {
        this.imageService = imageService;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping ({"/$count", "/$count/"})
    public ResponseEntity<Long> getCount() throws ControllerCountException {
        try {
            return new ResponseEntity<>(imageService.count(), HttpStatus.OK);
        } catch(Exception e) {
            throw new ControllerCountException("Could not count Entities! Maybe the amount of Entities is to large or Service is unavailable!");
        }
    }

    @PostMapping({"/upload", "/upload/"})
    public ResponseEntity<String> handleFileUpload(@RequestHeader(value = "Authorization") String auth, @RequestParam("file") MultipartFile file, @RequestParam("bio") String bio, @RequestParam("title") String title) throws ImageUploadException {
        try {
            String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
            switch(FilenameUtils.getExtension(file.getOriginalFilename())) {
                case "jpg":
                case "jpeg":
                case "png":
                case "gif": break;
                default: throw new ImageUploadException("Not a allowed file extension! Only jpg, jpeg, png and gif are allowed!");
            }
            imageService.addImage(user, file, title, bio);
            return new ResponseEntity<>("File Upload Accepted!", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ImageUploadException(e.getMessage());
        }
    }

    @GetMapping ({"/all", "/all/"})
    public ResponseEntity listUploadedFiles() {
        return new ResponseEntity<>(new ImageListDTO(imageService.getAllItems()) , HttpStatus.OK);
    }


    @GetMapping({"/image/{imageId}", "/image/{imageId}/"})
    public ResponseEntity<ImageDTO> getImage(@PathVariable long imageId) throws EntityNotFoundException {
        return new ResponseEntity(imageService.getItemById(imageId), HttpStatus.OK);
    }

    @GetMapping({"/user/{user}/{lastId}", "/user/{user}/{lastId}/"})
    public ResponseEntity<UserImagesDTO> getUserImages (@PathVariable String user, @PathVariable long lastId) throws EntityNotFoundException {
        return new ResponseEntity(imageService.getUserImagesByUserAndLastId(user, lastId), HttpStatus.OK);
    }

    @GetMapping({"/discover/{lastId}", "/discover/{lastId}/"})
    public ResponseEntity<UserImagesDTO> getDiscoverImages (@PathVariable Long lastId) throws EntityNotFoundException {
        return new ResponseEntity(imageService.getFeedImagesByLastId(lastId), HttpStatus.OK);
    }

    @GetMapping({"/feed/{lastId}", "/feed/{lastId}/"})
    public ResponseEntity<UserImagesDTO> getFollowingImages(@RequestHeader(value = "Authorization") String auth,@PathVariable Long lastId) throws EntityNotFoundException {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        return new ResponseEntity(imageService.getFollowedImagesByUserAndLastId(user, lastId), HttpStatus.OK);
    }

}
