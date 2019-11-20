package com.doggogram.backendsvc.repositories;

import com.doggogram.backendsvc.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findById(long id);
    void deleteImageById(Long id);
}
