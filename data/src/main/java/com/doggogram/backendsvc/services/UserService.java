package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.UserDTO;
import com.doggogram.backendsvc.util.exceptions.UserFollowException;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserService extends RestService<List<UserDTO>> {

    UserDTO findUserByUser(String user);
    void registerUser(String user, String pass);
    boolean followUser(String user, String followUser)
    throws UserFollowException;

}
