package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.dto.CommentDTO;
import com.doggogram.backendsvc.dto.CommentListDTO;
import com.doggogram.backendsvc.services.CommentService;
import com.doggogram.backendsvc.services.JwtTokenService;
import com.doggogram.backendsvc.util.Util;
import com.doggogram.backendsvc.util.exceptions.CommentOwnershipException;
import com.doggogram.backendsvc.util.exceptions.ControllerCountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping (value = "/api/v1/comment")
public class CommentController {

    private final JwtTokenService jwtTokenService;
    private final CommentService commentService;

    public CommentController (JwtTokenService jwtTokenService, CommentService commentService) {
        this.jwtTokenService = jwtTokenService;
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

    @GetMapping ({"/all", "/all/"})
    public ResponseEntity<CommentListDTO> getAllItems() {
        return new ResponseEntity<>(new CommentListDTO(commentService.getAllItems()), HttpStatus.OK);
    }

    @PostMapping ({"/add", "/add/"})
    public ResponseEntity addComment(@RequestHeader (value = "Authorization") String auth, @RequestParam("content") String content, @RequestParam("imageId") long imageId) {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        commentService.addComment(user, content, imageId);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PostMapping ({"/update", "/update/"})
    public ResponseEntity updateComment(@RequestHeader (value = "Authorization") String auth, @RequestParam("content") String content, @RequestParam("commentId") long commentId) throws CommentOwnershipException {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        commentService.updateComment(user, content, commentId);
        return new ResponseEntity(null, HttpStatus.ACCEPTED);
    }

    @PostMapping ({"/remove", "/remove/"})
    public ResponseEntity removeComment(@RequestHeader (value = "Authorization") String auth, @RequestParam("commentId") Long commentId)
    throws CommentOwnershipException {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        commentService.removeComment(user, commentId);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping ({"/image/comment/{commentId}", "/image/comment/{commentId}/"})
    public ResponseEntity<CommentDTO> getImageComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.getComment(commentId), HttpStatus.OK);
    }

    @GetMapping ({"/images/comments/{imageId}", "/images/comments/{imageId}/"})
    public ResponseEntity<CommentListDTO> getImageComments(@PathVariable Long imageId) {
        return new ResponseEntity<>(new CommentListDTO(commentService.getCommentsOfImage(imageId)), HttpStatus.OK);
    }

    @GetMapping ({"/images/$count/{imageId}", "/images/$count/{imageId}/"})
    public ResponseEntity<Long> getCommentCountForImage(@PathVariable Long imageId) {
        return new ResponseEntity<>(commentService.countCommentsOnImage(imageId), HttpStatus.OK);
    }

}
