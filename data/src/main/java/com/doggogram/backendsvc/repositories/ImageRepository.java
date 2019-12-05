package com.doggogram.backendsvc.repositories;

import com.doggogram.backendsvc.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findById(long id);

    @Query (value = "SELECT count(*) AS AMOUNT_ROWS FROM IMAGE", nativeQuery = true)
    Long countEntities ();

    void deleteImageById(Long id);

    @Query (value = "SELECT max(IMAGE_ID) FROM IMAGE", nativeQuery = true)
    Long findMaxId();

    @Query (value = "SELECT * FROM IMAGE WHERE IMAGE_ID < ?1 ORDER BY IMAGE_ID DESC LIMIT 9", nativeQuery = true)
    List<Image> findImagesByLastId (Long lastId);

    @Query (value = "SELECT * FROM IMAGE WHERE FK_USER = ?1 AND IMAGE_ID < ?2 ORDER BY IMAGE_ID DESC LIMIT 9", nativeQuery = true)
    List<Image> findImagesByUserAndLastId (String user, Long lastId);

    @Query (value = "SELECT * FROM IMAGE AS I, USER_FOLLOWING AS UF WHERE UF.FK_USER = ?1 AND I.IMAGE_ID < ?2 AND UF.FK_FOLLOWING = I.FK_USER ORDER BY I.IMAGE_ID DESC LIMIT 9", nativeQuery = true)
    List<Image> findFollowingImagesByUserAndLastId (String user, Long lastId);

    @Query (value = "SELECT * FROM IMAGE AS I, IMAGE_LIKED_BY AS ILB WHERE FK_USER = ?1 AND I.IMAGE_ID < ?2 AND I.IMAGE_ID = ILB.FK_IMAGE ORDER BY I.IMAGE_ID DESC LIMIT 9", nativeQuery = true)
    List<Image> findLikedImagesByUserAndLastId (String user, Long lastId);

    @Query (value = "SELECT count(*) FROM IMAGE_LIKED_BY WHERE FK_USER = ?1 AND FK_IMAGE = ?2", nativeQuery = true)
    Long checkIfImageIsLikedByUser(String user, Long imageId);

    @Query (value = "SELECT count(*) FROM IMAGE_LIKED_BY WHERE FK_IMAGE = ?1", nativeQuery = true)
    Long countImageLikes(Long imageId);

    @Query (value = "SELECT count(*) FROM IMAGE WHERE FK_USER = ?1 AND IMAGE_ID = ?2", nativeQuery = true)
    Long checkImageOwnership(String user, long imageId);

    @Query (value = "SELECT * FROM IMAGE AS I, COMMENT AS C WHERE C.COMMENT_ID = ?1 AND C.FK_IMAGE = I.IMAGE_ID", nativeQuery = true)
    Image findImageByCommentId(long commentId);

}
