package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.domain.User;
import com.doggogram.backendsvc.dto.UserDTO;
import com.doggogram.backendsvc.mapper.UserMapper;
import com.doggogram.backendsvc.repositories.UserRepository;
import com.doggogram.backendsvc.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public int count () {
        return userRepository.findAll().size();
    }

    @Override
    public List<UserDTO> getAllItems () {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
    }
}
