package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.dto.UserImagesDTO;
import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
public interface ImageService extends RestService<List<ImageDTO>> {

    void addImage(String user, MultipartFile image, String title, String bio) throws ImageCorruptedException;
    ImageDTO getItemById(long id) throws EntityNotFoundException;
    UserImagesDTO getUserImagesByUser(String user) throws EntityNotFoundException;

}
