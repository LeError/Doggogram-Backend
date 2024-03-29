package com.doggogram.backendsvc.repositories;

import com.doggogram.backendsvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUser(String user);

    @Query (value = "SELECT count(*) AS AMOUNT_ROWS FROM `USER`", nativeQuery = true)
    Long countEntities ();

    @Query (value = "SELECT count(*) AS AMOUNT_ROWS FROM `USER_FOLLOWING` WHERE FK_FOLLOWING = ?1", nativeQuery = true)
    Long countFollowers (String user);

    @Query (value = "SELECT count(*) AS AMOUNT_ROWS FROM `USER_FOLLOWING` WHERE FK_USER = ?1", nativeQuery = true)
    Long countFollowing (String user);

    @Query (value = "SELECT * FROM `USER` AS U, USER_FOLLOWING AS UF WHERE UF.FK_FOLLOWING = ?1 AND U.USER_NAME = UF.FK_USER ORDER BY U.USER_NAME", nativeQuery = true)
    List<User> findFollowersByUser (String user);

    @Query (value = "SELECT  * FROM `USER` AS U, USER_FOLLOWING AS UF WHERE UF.FK_USER = ?1 AND U.USER_NAME = UF.FK_FOLLOWING ORDER BY U.USER_NAME", nativeQuery = true)
    List<User> findFollowingByUser (String user);

    @Query (value = "SELECT count(*) AS AMOUNT_ROWS FROM USER_FOLLOWING WHERE FK_USER = ?1 AND FK_FOLLOWING = ?2", nativeQuery = true)
    short checkIfUserFollowsUser (String user, String followUser);

    @Query (value = "SELECT  * FROM `USER` WHERE USER_NAME LIKE ?1", nativeQuery = true)
    List<User> findUsersByUser (String user);

    @Query (value = "SELECT * FROM `USER` AS U, IMAGE I WHERE I.IMAGE_ID = ?1 AND U.USER_NAME = FK_USER", nativeQuery = true)
    User findOwnerByImageId(Long imageId);

    @Query (value = "SELECT USER_NAME FROM `USER` AS U, IMAGE I WHERE I.IMAGE_ID = ?1 AND U.USER_NAME = FK_USER", nativeQuery = true)
    String findOwnerUserByImageId(Long imageId);

    @Query (value = "SELECT USER_IMAGE FROM `USER` AS U, IMAGE I WHERE I.IMAGE_ID = ?1 AND U.USER_NAME = FK_USER", nativeQuery = true)
    String findOwnerUserImageByImageId(Long imageId);

    @Query (value = "SELECT * FROM `USER` AS U, IMAGE_LIKED_BY AS ILB WHERE ILB.FK_IMAGE = ?1 AND U.USER_NAME = ILB.FK_USER", nativeQuery = true)
    List<User> findLikerByImageId(Long imageId);

    @Query (value = "SELECT USER_IMAGE FROM `USER` WHERE USER_NAME = ?1", nativeQuery = true)
    String findUserImageByUser(String user);

}
