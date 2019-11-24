package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.util.exceptions.EntityCorruptedException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ImageService extends RestService<List<ImageDTO>> {

    String addImage(String user, String title, String bio, String filename);
    ImageDTO getItemById(long id) throws EntityCorruptedException;

}
