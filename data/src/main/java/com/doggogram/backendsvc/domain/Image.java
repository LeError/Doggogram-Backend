package com.doggogram.backendsvc.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@Entity (name = "IMAGE")
public class Image {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "IMAGE_ID")
    private Long id;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_USER", nullable = false)
    private User owner;

    @Lob
    @Column (name = "IMAGE_IMAGE", nullable = false)
    private String image;

    @Column (name = "IMAGE_TITLE", nullable = false)
    private String title;

    @Column (name = "IMAGE_BIO")
    private String bio;

    @ManyToMany ( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable (name = "IMAGE_LIKED_BY", joinColumns = { @JoinColumn(name = "FK_IMAGE") }, inverseJoinColumns = { @JoinColumn(name = "FK_USER") })
    private List<User> liker;

    @Column (name = "IMAGE_CREATED", nullable = false)
    private Date created = new Date();

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn (name = "FK_IMAGE")
    private List<Comment> comments;

}
