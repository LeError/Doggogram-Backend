package com.doggogram.backendsvc.repositories;

import com.doggogram.backendsvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUser(String user);

    @Query (value = "SELECT USER_USER_NAME FROM USER_IMAGE WHERE images_IMAGE_ID = ?1", nativeQuery = true)
    String findUserByImageId(Long id);

    @Query (value = "SELECT count(*) AS AMOUNT_ROWS FROM USER_FOLLOWING WHERE FK_USER = ?1 AND FK_FOLLOWING = ?2", nativeQuery = true)
    short checkIfUserFollowsUser(String user, String followUser);

}
