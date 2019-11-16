package com.doggogram.backendsvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ImageListDTO {

    private List<ImageDTO> imageDTOS;

}
