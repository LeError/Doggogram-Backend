package com.doggogram.backendsvc.domain;

import com.doggogram.backendsvc.repositories.CommentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Entity (name = "COMMENT")
public class Comment {

    @Transient
    @Autowired
    private CommentRepository commentRepository;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "COMMENT_ID")
    private Long id;

    @Column (name = "COMMENT_CONTENT", nullable = false)
    private String comment;

    @Column (name = "COMMENT_CREATED", nullable = false)
    private Date created = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_USER", nullable = false)
    private User author;

    @PreRemove
    private void removeUser() {
        author = null;
        commentRepository.save(this);
    }

}
