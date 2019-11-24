package com.doggogram.backendsvc.domain;

import com.doggogram.backendsvc.dto.ImageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity (name = "IMAGE")
public class Image {

    public Image(ImageDTO imageDTO) {
        id = imageDTO.getId();
        filename = imageDTO.getFilename();
        title = imageDTO.getTitle();
        bio = imageDTO.getBio();
        likes = imageDTO.getLikes();
        created = imageDTO.getCreated();
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "IMAGE_ID")
    private Long id;

    @Column (name = "IMAGE_FILE")
    private String filename;

    @Column (name = "IMAGE_TITLE")
    private String title;

    @Column (name = "IMAGE_BIO")
    private String bio;

    @Column (name = "IMAGE_LIKES")
    private Long likes;

    @Column (name = "IMAGE_CREATED")
    private Date created;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

}
