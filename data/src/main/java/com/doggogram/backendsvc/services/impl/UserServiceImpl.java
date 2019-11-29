package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.domain.User;
import com.doggogram.backendsvc.dto.UserDTO;
import com.doggogram.backendsvc.mapper.UserMapper;
import com.doggogram.backendsvc.repositories.UserRepository;
import com.doggogram.backendsvc.services.UserService;
import com.doggogram.backendsvc.util.exceptions.PasswordDoesNotMatchException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Override

    public UserDTO findUserByUser (String user) {
        return userMapper.userToUserDTO(userRepository.findUserByUser(user));
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
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
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
    public void updatePassword (String user, String oldPassword, String newPassword) throws PasswordDoesNotMatchException {
        if(passwordEncoder.matches(oldPassword, userRepository.findUserByUser(user).getPass())) {
            User userEntity = userRepository.findUserByUser(user);
            userEntity.setPass(passwordEncoder.encode(newPassword));
            userRepository.save(userEntity);
        } else {
            throw new PasswordDoesNotMatchException("Old Password does not Match Database!");
        }
    }

}
