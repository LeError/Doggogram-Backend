package com.doggogram.backendsvc.dto;

import com.doggogram.backendsvc.domain.Image;
import lombok.Data;

import java.util.List;

@Data
public class UserImagesDTO {

    private List<Image> images;

}
