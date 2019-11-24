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

    @Column (name = "COMMENT")
    private String comment;

    @Column (name = "CREATED")
    private Date created;

}
