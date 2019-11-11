package com.doggogram.backendsvc.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity (name = "USER")
public class User {

    @Id
    @Column (name = "USER_NAME")
    private String user;

    @Column (name = "PASS")
    private String pass;

    @Column (name = "BIO")
    private String bio;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> images;

}
