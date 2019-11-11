package com.doggogram.backendsvc.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity (name = "IMAGE")
public class Image {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "IMAGE_ID")
    private Long id;

    @Column (name = "URL")
    private String url;

    @Column (name = "LIKES")
    private Long likes;



}
