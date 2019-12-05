package com.doggogram.backendsvc.repositories;

import com.doggogram.backendsvc.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query (value = "SELECT count(*) AS AMOUNT_ROWS FROM COMMENT", nativeQuery = true)
    Long countEntities ();

    void deleteCommentById(long imageId);

    Comment findCommentById(long imageId);

    @Query (value = "SELECT * FROM COMMENT WHERE FK_IMAGE = ?1", nativeQuery = true)
    List<Comment> findCommentsByImageId(long imageId);

    @Query (value = "SELECT count(*) FROM COMMENT WHERE FK_USER = ?1 AND COMMENT_ID = ?2", nativeQuery = true)
    Long checkCommentOwnership(String user, long imageId);

}
