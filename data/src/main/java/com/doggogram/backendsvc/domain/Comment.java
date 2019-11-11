package com.doggogram.backendsvc.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity (name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "IMAGE_ID")
    private Long id;

    @OneToOne (fetch = FetchType.EAGER)
    @Column (name = "Author")
    private User user;

    @OneToOne (fetch = FetchType.EAGER)
    @Column (name = "IMAGE")
    private Image image;

    @Column (name = "COMMENT")
    private String comment;

}
