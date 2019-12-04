package com.doggogram.backendsvc.dto;

import com.doggogram.backendsvc.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserImagesDTO {

    private List<Image> images = new ArrayList<>();

}
