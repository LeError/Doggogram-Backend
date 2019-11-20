package com.doggogram.backendsvc.mapper;

import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.dto.ImageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);
    ImageDTO imageToImageDTO(Image image);

}
