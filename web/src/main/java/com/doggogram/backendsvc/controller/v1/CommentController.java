package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.services.CommentService;
import com.doggogram.backendsvc.util.exceptions.ControllerCountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (value = "/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController (CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping ({"/$count", "/$count/"})
    public ResponseEntity<Long> getCount() throws ControllerCountException {
        try {
            return new ResponseEntity<>(commentService.count(), HttpStatus.OK);
        } catch(Exception e) {
            throw new ControllerCountException("Could not count Entities! Maybe the amount of Entities is to large or Service is unavailable!");
        }
    }

}
