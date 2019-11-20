package com.doggogram.backendsvc.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {

    void init();

    void store(MultipartFile file, String filename);

    Path load(String filename);

    Resource loadAsResource(String filename);

    String getEncodedImage (String filename) throws IOException;

    void deleteAll();

}
