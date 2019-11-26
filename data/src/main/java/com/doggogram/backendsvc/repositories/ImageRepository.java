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

    @Query (value = "SELECT IMAGE_ID FROM IMAGE WHERE IMAGE_ID < ?1 ORDER BY IMAGE_ID DESC LIMIT 9", nativeQuery = true)
    List<Long> findImageIdByLastId(Long lastId);

    @Query (value = "SELECT IMAGE_ID FROM IMAGE WHERE FK_USER = ?1 AND IMAGE_ID < ?2 ORDER BY IMAGE_ID DESC LIMIT 9", nativeQuery = true)
    List<Long> findImageIdByUserAndLastId(String user, Long lastId);

    @Query (value = "SELECT max(IMAGE_ID) FROM IMAGE", nativeQuery = true)
    Long findMaxId();

    @Query(value = "SELECT I.IMAGE_ID FROM IMAGE AS I, USER_FOLLOWING AS UF WHERE UF.FK_USER = ?1 AND I.IMAGE_ID < ?2 AND UF.FK_FOLLOWING = I.FK_USER ORDER BY I.IMAGE_ID DESC LIMIT 9", nativeQuery = true)
    List<Long> findFollowingIdByUser(String user, Long lastId);

}
