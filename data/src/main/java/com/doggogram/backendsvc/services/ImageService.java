package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.dto.ImageFilenameDTO;

import java.util.List;

public interface ImageService extends RestService<List<ImageDTO>> {

    String addImage(String user, String title, String bio, String filename);
    List<ImageFilenameDTO> getAllImageFilenames();
    String getImageName(String user, String fileExtension);

}
