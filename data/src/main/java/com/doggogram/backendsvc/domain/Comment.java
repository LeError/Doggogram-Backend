package com.doggogram.backendsvc.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity (name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "COMMENT_ID")
    private Long id;

    @Column (name = "COMMENT", nullable = false)
    private String comment;

    @Column (name = "CREATED", nullable = false)
    private Date created = new Date();

}
