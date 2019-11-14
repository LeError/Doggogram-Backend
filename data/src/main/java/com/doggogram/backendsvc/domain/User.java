package com.doggogram.backendsvc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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

    @Column (name = "PASS")
    private String pass;

    @Column (name = "BIO")
    private String bio;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> images;

    public void addImage(Image image) {
        images.add(image);
    }

    public void removeImage(Image image) {
        images.remove(image);
    }

}
