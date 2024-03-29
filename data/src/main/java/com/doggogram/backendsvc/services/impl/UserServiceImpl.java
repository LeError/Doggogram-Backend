package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.domain.User;
import com.doggogram.backendsvc.dto.UserDTO;
import com.doggogram.backendsvc.mapper.Mapper;
import com.doggogram.backendsvc.repositories.UserRepository;
import com.doggogram.backendsvc.services.UserService;
import com.doggogram.backendsvc.util.Util;
import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
import com.doggogram.backendsvc.util.exceptions.PasswordDoesNotMatchException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;

    public UserServiceImpl (UserRepository userRepository, Mapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Override

    public UserDTO findUserByUser (String user) {
        return mapper.userToUserDTO(userRepository.findUserByUser(user));
    }

    @Override public List<UserDTO> findUsersByUser (String user) {
        user += "%";
        return userRepository.findUsersByUser(user).stream().map(mapper::userToUserDTO).collect(Collectors.toList());
    }

    @Override public void registerUser (String user, String pass) {
        userRepository.save(new User(user, passwordEncoder.encode(pass)));
    }

    @Override
    public Long count () {
        return userRepository.countEntities();
    }

    @Override
    public List<UserDTO> getAllItems () {
        return userRepository.findAll().stream().map(mapper::userToUserDTO).collect(Collectors.toList());
    }

    @Override
    public boolean followUser (String user, String followUser) throws EntityNotFoundException {
        User userEntity = userRepository.findUserByUser(user);
        User followUserEntity = userRepository.findUserByUser(followUser);
        Boolean following = false;
        if(userEntity == null || followUser == null) {
            throw new EntityNotFoundException("Cant find Entity!");
        }
        if(userRepository.checkIfUserFollowsUser(user, followUser) == 0) {
            userEntity.getFollowing().add(followUserEntity);
            following = true;
        } else {
            userEntity.getFollowing().remove(followUserEntity);
        }
        userRepository.save(userEntity);
        return following;
    }

    @Override
    public List<UserDTO> getFollowers (String user) {
        return userRepository.findFollowersByUser(user).stream().map(mapper::userToUserDTO).collect(Collectors.toList());
    }

    @Override
    public Long countFollowers (String user) {
        return userRepository.countFollowers(user);
    }

    @Override
    public List<UserDTO> getFollowing (String user) {
        return userRepository.findFollowingByUser(user).stream().map(mapper::userToUserDTO).collect(Collectors.toList());
    }

    @Override
    public Long countFollowing (String user) {
        return userRepository.countFollowing(user);
    }

    @Override
    public void updatePassword (String user, String oldPassword, String newPassword) throws PasswordDoesNotMatchException {
        if(passwordEncoder.matches(oldPassword, userRepository.findUserByUser(user).getPass())) {
            User userEntity = userRepository.findUserByUser(user);
            userEntity.setPass(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
        } else {
            throw new PasswordDoesNotMatchException("Old Password does not Match Database!");
        }
    }

    @Override
    public void updateBio (String user, String bio) {
        User userEntity = userRepository.findUserByUser(user);
        userEntity.setBio(bio);
        userRepository.save(userEntity);
    }

    @Override
    public void updateImage (String user, MultipartFile image) throws ImageCorruptedException {
        User userEntity = userRepository.findUserByUser(user);
        userEntity.setUserImage(Util.getEncodedImage(image));
        userRepository.save(userEntity);
    }

    @Override
    public void removeImage (String user) {
        User userEntity = userRepository.findUserByUser(user);
        userEntity.setUserImage(null);
        userRepository.save(userEntity);
    }

    @Override
    public List<UserDTO> getImageLiker (long imageId) {
        return userRepository.findLikerByImageId(imageId).stream().map(mapper::userToUserDTO).collect(Collectors.toList());
    }

    @Override
    public String getUserImage (String user) {
        return userRepository.findUserImageByUser(user);
    }

}
