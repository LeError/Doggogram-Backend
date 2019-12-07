package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.util.exceptions.ImageCorruptedException;
import com.doggogram.backendsvc.util.exceptions.ImageOwnershipException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
public interface ImageService extends RestService<List<ImageDTO>> {

    void addImage(String user, MultipartFile image, String title, String bio) throws ImageCorruptedException;
    void removeImage(long imageId) throws EntityNotFoundException;
    ImageDTO getItemById(long id) throws EntityNotFoundException;
    List<ImageDTO> getFollowedImagesByUserAndLastId (String user, long lastId) throws EntityNotFoundException;
    List<ImageDTO> getUserImagesByUserAndLastId (String user, long lastId) throws EntityNotFoundException;
    List<ImageDTO> getFeedImagesByLastId (long lastId) throws EntityNotFoundException;
    boolean toggleLike(String user, long imageId) throws EntityNotFoundException;
    boolean isImageLikedBy(String user, long imageId) throws EntityNotFoundException;
    Long getImageLikes(long imageId) throws EntityNotFoundException;
    List<ImageDTO> getLikedImages(String user, long lastId);
    void updateTitle(String user, String content, long imageId) throws ImageOwnershipException;
    void updateBio(String user, String content, long imageId) throws ImageOwnershipException;
}
