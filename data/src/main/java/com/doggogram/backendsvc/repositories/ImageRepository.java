package com.doggogram.backendsvc.repositories;

import com.doggogram.backendsvc.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findById(long id);

    void deleteImageById(Long id);

    @Query (value = "SELECT images_IMAGE_ID FROM USER_IMAGE WHERE USER_USER_NAME = ?1 ORDER BY images_IMAGE_ID ASC;", nativeQuery = true)
    List<Long> findImageIdByUser(String user);

    @Query (value = "SELECT images_IMAGE_ID FROM USER_IMAGE WHERE images_IMAGE_ID < ?1 ORDER BY images_IMAGE_ID DESC LIMIT 9", nativeQuery = true)
    List<Long> findImageIdByLastId(Long lastId);

}
