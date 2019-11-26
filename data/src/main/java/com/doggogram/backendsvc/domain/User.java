package com.doggogram.backendsvc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity (name = "USER")
public class User {

    public User(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    @Id
    @Column (name = "USER_NAME")
    private String user;

    @Column (name = "USER_PASS", nullable = false)
    private String pass;

    @Column (name = "USER_BIO")
    private String bio;

    @Lob
    @Column (name = "USER_IMAGE")
    private String userImage;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> images;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    public void addImage(Image image) {
        images.add(image);
    }

    public void removeImage(Image image) {
        images.remove(image);
    }

}
