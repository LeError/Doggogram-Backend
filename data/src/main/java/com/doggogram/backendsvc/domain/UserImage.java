package com.doggogram.backendsvc.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Date;

@Data
@Entity (name = "USER_IMAGE")
public class UserImage {

    @Lob
    @Column (name = "USER_IMAGE_IMAGE", nullable = false)
    private String userImage;

    @Column (name = "USER_IMAGE_CREATED", nullable = false)
    private Date created;

}
