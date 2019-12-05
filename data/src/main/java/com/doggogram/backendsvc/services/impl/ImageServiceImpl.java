package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.domain.User;
import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.mapper.ImageMapper;
import com.doggogram.backendsvc.repositories.ImageRepository;
import com.doggogram.backendsvc.repositories.UserRepository;
import com.doggogram.backendsvc.services.ImageService;
import com.doggogram.backendsvc.util.Util;
import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
import com.doggogram.backendsvc.util.exceptions.ImageOwnershipException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private ImageMapper imageMapper;
    private UserRepository userRepository;

    public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void addImage (String user, MultipartFile image, String title, String bio) throws ImageCorruptedException {
        User userEntity = userRepository.findUserByUser(user);
        Image imageEntity = new Image();
        imageEntity.setImage(Util.getEncodedImage(image));
        imageEntity.setTitle(title);
        imageEntity.setBio(bio);
        imageRepository.save(imageEntity);
        userEntity.addImage(imageEntity);
        userRepository.save(userEntity);
    }

    @Override
    public void removeImage (long imageId) throws EntityNotFoundException {
        User user = userRepository.findOwnerByImageId(imageId);
        user.removeImage(imageRepository.findById(imageId));
        imageRepository.deleteImageById(imageId);
    }

    @Override
    public Long count () {
        return imageRepository.countEntities();
    }

    @Override
    public List<ImageDTO> getAllItems () {
        return imageRepository.findAll().stream().map(imageMapper::imageToImageDTO).collect(Collectors.toList());
    }

    @Override
    public ImageDTO getItemById (long id) throws EntityNotFoundException {
        Image image = imageRepository.findById(id);
        if(image == null) {
            throw new EntityNotFoundException("Can't find requested Entity in Database!");
        }
        return imageMapper.imageToImageDTO(image);
    }

    @Override public List<ImageDTO> getFollowedImagesByUserAndLastId (String user, long lastId) throws EntityNotFoundException {
        if(userRepository.findUserByUser(user) == null) {
            throw new EntityNotFoundException("Can't find requested base user Entity in Database!");
        }
        if(lastId <= 0) {
            if(imageRepository.findMaxId() != null)
                lastId = imageRepository.findMaxId() + 1;
        } else {
            lastId = 0;
        }
        return imageRepository.findFollowingImagesByUserAndLastId(user, lastId).stream().map(imageMapper::imageToImageDTO).collect(Collectors.toList());
    }

    @Override
    public List<ImageDTO> getUserImagesByUserAndLastId (String user, long lastId) throws EntityNotFoundException {
        if(userRepository.findUserByUser(user) == null) {
            throw new EntityNotFoundException("Can't find requested user Entity in Database!");
        }
        if(lastId <= 0) {
            if(imageRepository.findMaxId() != null)
            lastId = imageRepository.findMaxId() + 1;
        } else {
            lastId = 0;
        }
        return imageRepository.findImagesByUserAndLastId(user, lastId).stream().map(imageMapper::imageToImageDTO).collect(Collectors.toList());
    }

    @Override
    public List<ImageDTO> getFeedImagesByLastId (long lastId) {
        if(lastId <= 0) {
            if(imageRepository.findMaxId() != null)
                lastId = imageRepository.findMaxId() + 1;
        } else {
            lastId = 0;
        }
        return imageRepository.findImagesByLastId(lastId).stream().map(imageMapper::imageToImageDTO).collect(Collectors.toList());
    }

    @Override
    public boolean toggleLike (String user, long imageId) throws EntityNotFoundException {
        Image image = imageRepository.findById(imageId);
        User userEntity = userRepository.findUserByUser(user);
        if(imageRepository.checkIfImageIsLikedByUser(user, imageId) > 0) {
            image.getLiker().remove(userEntity);
            imageRepository.save(image);
            return false;
        }
        image.getLiker().add(userEntity);
        imageRepository.save(image);
        return true;
    }

    @Override
    public boolean isImageLikedBy (String user, long imageId) throws EntityNotFoundException {
        if(imageRepository.checkIfImageIsLikedByUser(user, imageId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Long getImageLikes (long imageId) throws EntityNotFoundException {
        return imageRepository.countImageLikes(imageId);
    }

    @Override
    public List<ImageDTO> getLikedImages (String user, long lastId) {
        return imageRepository.findLikedImagesByUserAndLastId(user, lastId).stream().map(imageMapper::imageToImageDTO).collect(Collectors.toList());
    }

    @Override
    public void updateTitle (String user, String content, long imageId) throws ImageOwnershipException {
        if(imageRepository.checkImageOwnership(user, imageId) > 0) {
            Image image = imageRepository.findById(imageId);
            image.setTitle(content);
            imageRepository.save(image);
        } else {
            throw new ImageOwnershipException("Not Owner of Image!");
        }
    }

    @Override
    public void updateBio (String user, String content, long imageId) throws ImageOwnershipException {
        if(imageRepository.checkImageOwnership(user, imageId) > 0) {
            Image image = imageRepository.findById(imageId);
            image.setBio(content);
            imageRepository.save(image);
        } else {
            throw new ImageOwnershipException("Not Owner of Image!");
        }
    }

}
