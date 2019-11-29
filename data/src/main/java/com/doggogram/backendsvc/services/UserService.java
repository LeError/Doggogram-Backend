package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.UserDTO;
import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
import com.doggogram.backendsvc.util.exceptions.PasswordDoesNotMatchException;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserService extends RestService<List<UserDTO>> {

    UserDTO findUserByUser(String user);
    void registerUser(String user, String pass);
    boolean followUser(String user, String followUser);
    void updatePassword(String user, String oldPassword, String newPassword) throws PasswordDoesNotMatchException;
    void updateBio(String user, String bio);
    void updateImage(String user, MultipartFile image) throws ImageCorruptedException;
    void removeImage(String user);

}
