package com.doggogram.backendsvc.util;

import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
import com.google.common.io.ByteStreams;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Util {

    public static String getJwtToken(String auth) {
        return auth.split("\\s+")[1];
    }

    public static String getEncodedImage(MultipartFile image) throws ImageCorruptedException {
        try {
            return Base64Utils.encodeToString(ByteStreams.toByteArray(image.getInputStream()));
        } catch (IOException e) {
            throw new ImageCorruptedException(e.getMessage());
        }
    }

    public static List<Image> sortImages(List<Image> images) {
        Collections.sort(images, Comparator.comparingLong(image -> image.getId()));
        return images;
    }

}
