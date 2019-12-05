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
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity (name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "COMMENT_ID")
    private Long id;

    @Column (name = "COMMENT_CONTENT", nullable = false)
    private String comment;

    @Column (name = "COMMENT_CREATED", nullable = false)
    private Date created = new Date();

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_USER", nullable = false)
    private User author;

}
