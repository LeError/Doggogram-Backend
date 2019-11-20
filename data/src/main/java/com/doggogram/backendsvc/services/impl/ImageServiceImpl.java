package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.domain.User;
import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.mapper.ImageMapper;
import com.doggogram.backendsvc.repositories.ImageRepository;
import com.doggogram.backendsvc.repositories.UserRepository;
import com.doggogram.backendsvc.services.ImageService;
import com.doggogram.backendsvc.services.StorageService;
import com.doggogram.backendsvc.util.exceptions.EntityCorruptedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private ImageMapper imageMapper;
    private UserRepository userRepository;
    private StorageService storageService;

    public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper, UserRepository userRepository, StorageService storageService) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.userRepository = userRepository;
        this.storageService = storageService;
    }

    @Override
    public String addImage (String user, String title, String bio, String filename) {
        Image image = new Image();
        image.setBio(bio);
        image.setTitle(title);
        image.setCreated(new Date());
        image.setLikes(0L);
        image.setFilename(filename);
        User userObject = userRepository.findUserByUser(user);
        userObject.addImage(image);
        userRepository.save(userObject);
        return "temp";
    }

    @Override
    public int count () {
        return imageRepository.findAll().size();
    }

    @Override
    public List<ImageDTO> getAllItems () {
        List<ImageDTO> images = imageRepository.findAll().stream().map(imageMapper::imageToImageDTO).collect(Collectors.toList());
        List<ImageDTO> corruptedImages = new ArrayList<>();
        for(ImageDTO image : images) {
            try {
                image.setImageEncoded(storageService.getEncodedImage(image.getFilename()));
            } catch (IOException e) {
                log.error("Could not load image for " + image.getId() + ", so it will be removed!");
                imageRepository.deleteImageById(image.getId());
                log.info("Removed Image " + image.getId() + " from Database!");
                corruptedImages.add(image);
            }
        }
        images.removeAll(corruptedImages);
        log.info("Removed " + corruptedImages.size() + "Corrupted Images from Database!");
        return images;
    }

    @Override
    public ImageDTO getItemById (long id) throws EntityNotFoundException, EntityCorruptedException {
        ImageDTO imageDTO = imageMapper.imageToImageDTO(imageRepository.findById(id));
        if(imageDTO == null) {
            throw new EntityNotFoundException();
        }
        try {
            imageDTO.setImageEncoded(storageService.getEncodedImage(imageDTO.getFilename()));
        } catch (IOException e) {
            log.error("Could not load image for " + imageDTO.getId() + ", so it will be removed!");
            imageRepository.deleteImageById(imageDTO.getId());
            log.info("Removed Image " + imageDTO.getId() + " from Database!");
            throw new EntityCorruptedException("Can't return corrupted entity!");
        }
        return imageDTO;
    }

}
