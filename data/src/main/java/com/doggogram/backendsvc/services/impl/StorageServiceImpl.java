package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.services.StorageService;
import com.doggogram.backendsvc.storage.properties.StorageProperties;
import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
import com.doggogram.backendsvc.util.exceptions.ImageNotFoundException;
import com.doggogram.backendsvc.util.exceptions.StorageException;
import com.google.common.io.ByteStreams;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    public StorageServiceImpl (StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void init () {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Storage could not be initialized!", e);
        }
    }

    @Override
    public void store (MultipartFile file, String filename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException("Cannot store file with relative path outside current directory " + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    /*
    public Stream<Path> loadAll () {
        try {
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }*/

    @Override
    public Path load (String filename) throws ImageNotFoundException {
        try {
            return rootLocation.resolve(filename);
        } catch (NullPointerException e) {
            throw new ImageNotFoundException("Could not find image + " + filename + "!");
        }
    }

    @Override
    public Resource loadAsResource (String filename) throws ImageNotFoundException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new ImageNotFoundException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new ImageNotFoundException("Could not read file: " + filename);
        }
    }

    @Override
    public String getEncodedImage (String filename) throws ImageNotFoundException, ImageCorruptedException {
        try {
            return Base64Utils.encodeToString(ByteStreams.toByteArray(loadAsResource(filename).getInputStream()));
        } catch(IOException e) {
            throw new ImageCorruptedException("Could not load Image " + filename + "! Probably corrupted or gone!");
        }
    }

    @Override
    public void deleteAll () {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
