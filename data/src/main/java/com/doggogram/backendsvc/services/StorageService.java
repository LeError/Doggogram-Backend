package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
import com.doggogram.backendsvc.util.exceptions.ImageNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Path;

@Transactional
public interface StorageService {

    void init();

    void store(MultipartFile file, String filename);

    Path load(String filename)
    throws ImageNotFoundException;

    Resource loadAsResource(String filename)
    throws ImageNotFoundException;

    String getEncodedImage (String filename) throws ImageNotFoundException, ImageCorruptedException;

    void deleteAll();

}
