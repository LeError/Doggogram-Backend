package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.domain.User;
import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.repositories.ImageRepository;
import com.doggogram.backendsvc.repositories.UserRepository;
import com.doggogram.backendsvc.services.ImageService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private UserRepository userRepository;

    public ImageServiceImpl(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String addImage (String user, String title, String bio) {
        System.err.println(user);
        Image image = new Image();
        image.setBio(bio);
        image.setTitle(title);
        image.setCreated(new Date());
        image.setLikes(0L);
        User userObject = userRepository.findUserByUser(user);
        userObject.addImage(image);
        userRepository.save(userObject);
        return "temp";
    }

    @Override
    public int count () {
        return 0;
    }

    @Override
    public List<ImageDTO> getAllItems () {
        return null;
    }
}
