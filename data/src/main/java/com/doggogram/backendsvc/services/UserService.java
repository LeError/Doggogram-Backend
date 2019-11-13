package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.UserDTO;

import java.util.List;

public interface UserService extends RestService<List<UserDTO>> {

    UserDTO findUserByUser(String user);
    void registerUser(String user, String pass);

}
