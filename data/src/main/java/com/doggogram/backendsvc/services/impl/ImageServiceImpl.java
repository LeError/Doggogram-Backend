package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.domain.User;
import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.dto.UserImagesDTO;
import com.doggogram.backendsvc.mapper.ImageMapper;
import com.doggogram.backendsvc.repositories.ImageRepository;
import com.doggogram.backendsvc.repositories.UserRepository;
import com.doggogram.backendsvc.services.ImageService;
import com.doggogram.backendsvc.util.Util;
import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
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
    public void addImage (String user, MultipartFile image, String title, String bio)
    throws ImageCorruptedException {
        Image imageEntity = new Image();
        imageEntity.setImage(Util.getEncodedImage(image));
        imageEntity.setTitle(title);
        imageEntity.setBio(bio);
        User userEntity = userRepository.findUserByUser(user);
        userEntity.addImage(imageEntity);
        userRepository.save(userEntity);
    }

    @Override
    public int count () {
        return imageRepository.findAll().size();
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

    @Override
    public UserImagesDTO getUserImagesByUser (String user, int idx) throws EntityNotFoundException {
        if(userRepository.findUserByUser(user) == null) {
            throw new EntityNotFoundException("Can't find requested user Entity in Database!");
        }
        List<Long> imageIds = imageRepository.findImageIdByUser(user);
        UserImagesDTO imagesDTO = new UserImagesDTO();
        for(int i = 0; i < 9; i++) {
            int selected = imageIds.size() - 9 * idx - i;
            if(selected > - 1) {
                imagesDTO.getImages().add(imageRepository.findById(selected));
            } else {
                break;
            }

        }
        return imagesDTO;
    }

    @Override
    public UserImagesDTO getFeedImagesByLastId (long lastId) {
        UserImagesDTO imagesDTO = new UserImagesDTO();
        for(Long imageId : imageRepository.findImageIdByLastId(lastId)) {
            imagesDTO.getImages().add(imageRepository.findById(imageId.longValue()));
        }
        return imagesDTO;
    }

}
