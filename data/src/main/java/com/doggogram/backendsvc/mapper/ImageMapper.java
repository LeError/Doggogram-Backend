package com.doggogram.backendsvc.mapper;

import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.dto.ImageFilenameDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);
    ImageDTO imageToImageDTO(Image image);
    ImageFilenameDTO imageToImageFilenameDTO(Image image);

}
