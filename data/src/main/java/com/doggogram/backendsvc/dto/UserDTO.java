package com.doggogram.backendsvc.dto;

import com.doggogram.backendsvc.domain.Image;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private String user;
    private String bio;
    private String userImage;
    private Set<Image> images;

}
