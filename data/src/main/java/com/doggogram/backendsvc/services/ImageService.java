package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.ImageDTO;

import java.util.List;

public interface ImageService extends RestService<List<ImageDTO>> {

    String addImage(String user, String title, String bio);

}
