package com.doggogram.backendsvc.util;

import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
import com.google.common.io.ByteStreams;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

}
