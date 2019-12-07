package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.dto.UserDTO;
import com.doggogram.backendsvc.dto.UserListDTO;
import com.doggogram.backendsvc.services.AuthService;
import com.doggogram.backendsvc.services.JwtTokenService;
import com.doggogram.backendsvc.services.UserService;
import com.doggogram.backendsvc.util.Util;
import com.doggogram.backendsvc.util.exceptions.ControllerCountException;
import com.doggogram.backendsvc.util.exceptions.ImageUploadException;
import com.doggogram.backendsvc.util.exceptions.PasswordDoesNotMatchException;
import com.doggogram.backendsvc.util.exceptions.UserRegistrationException;
import com.doggogram.backendsvc.util.responses.JwtTokenResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping (value = "/api/v1/users")
public class UserController {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final AuthService authService;

    public  UserController (UserService userService, JwtTokenService jwtTokenService, AuthService authService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.authService = authService;
    }

    @PostMapping ( value = {"/register", "/register/"})
    public ResponseEntity createCustomer(@RequestParam("user") String user, @RequestParam("password") String pass) throws UserRegistrationException {
        try {
            userService.registerUser(user, pass);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new UserRegistrationException("Could not register User! Contact Administrator if Problem persists!");
        }
    }

    @GetMapping ({"/$count", "/$count/"})
    public ResponseEntity<Long> getCount() throws ControllerCountException {
        try {
            return new ResponseEntity<>(userService.count(), HttpStatus.OK);
        } catch(Exception e) {
            throw new ControllerCountException("Could not count Entities! Maybe the amount of Entities is to large or Service is unavailable!");
        }
    }

    @GetMapping ({"/all", "/all/"})
    public ResponseEntity<UserListDTO> getUsers() {
        return new ResponseEntity<>(new UserListDTO(userService.getAllItems()), HttpStatus.OK);
    }

    @GetMapping ({"/user/{user}", "/user/{user}/"})
    public ResponseEntity<UserDTO> getUser(@PathVariable String user) {
        return new ResponseEntity<>(userService.findUserByUser(user), HttpStatus.OK);
    }

    @GetMapping ({"/search/{user}", "/search/{user}/"})
    public ResponseEntity<UserListDTO> searchUsers(@PathVariable String user) {
        return new ResponseEntity<>(new UserListDTO(userService.findUsersByUser(user)), HttpStatus.OK);
    }

    @PostMapping ({"/follow", "/follow/"})
    public ResponseEntity<Boolean> toggleFollowUser(@RequestHeader (value = "Authorization") String auth, @RequestParam("followUser") String followUser) throws EntityNotFoundException {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        return new ResponseEntity(userService.followUser(user, followUser), HttpStatus.CREATED);
    }

    @GetMapping ({"/username", "/username/"})
    public ResponseEntity<String> getOwnUser(@RequestHeader (value = "Authorization") String auth) {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping ({"/followers/user/{user}", "/followers/user/{user}/"})
    public ResponseEntity<UserListDTO> getFollowers(@PathVariable String user) {
        return new ResponseEntity<>(new UserListDTO(userService.getFollowers(user)), HttpStatus.OK);
    }

    @GetMapping ({"/followers/$count/{user}", "/followers/$count/{user}/"})
    public ResponseEntity<Long> getAmountFollowers(@PathVariable String user) {
        return new ResponseEntity<>(userService.countFollowers(user), HttpStatus.OK);
    }

    @GetMapping ({"/following/user/{user}", "/following/user/{user}/"})
    public ResponseEntity<UserListDTO> getFollowing(@PathVariable String user) {
        return new ResponseEntity<>(new UserListDTO(userService.getFollowing(user)), HttpStatus.OK);
    }

    @GetMapping ({"/following/$count/{user}", "/following/$count/{user}/"})
    public ResponseEntity<Long> getAmountFollowing(@PathVariable String user) {
        return new ResponseEntity<>(userService.countFollowing(user), HttpStatus.OK);
    }

    @PostMapping (value = {"/update/password", "/update/password/"})
    public ResponseEntity<JwtTokenResponse> updatePassword(@RequestHeader (value = "Authorization") String auth, @RequestParam("oldPassword") String oPass, @RequestParam("newPassword") String nPass) throws PasswordDoesNotMatchException {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        userService.updatePassword(user,oPass, nPass);
        return new ResponseEntity<>(authService.generateJWTToken(user, nPass), HttpStatus.OK);
    }

    @PostMapping (value = {"/update/bio", "/update/bio/"})
    public ResponseEntity updateBio(@RequestHeader (value = "Authorization") String auth, @RequestParam("content") String content) {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        userService.updateBio(user,content);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping ({"/image", "/image/"})
    public ResponseEntity updateUserImage(@RequestHeader (value = "Authorization") String auth, @RequestParam ("file") MultipartFile file) throws ImageUploadException {
        try {
            String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
            switch(FilenameUtils.getExtension(file.getOriginalFilename())) {
                case "jpg":
                case "jpeg":
                case "png":
                case "gif": break;
                default: throw new ImageUploadException("Not a allowed file extension! Only jpg, jpeg, png and gif are allowed!");
            }
            userService.updateImage(user, file);
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ImageUploadException(e.getMessage());
        }
    }

    @PostMapping ({"/image/remove", "/image/remove/"})
    public ResponseEntity removeUserImage(@RequestHeader(value = "Authorization") String auth) {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        userService.removeImage(user);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping ({"/images/liker/{imageId}", "/images/liker/{imageId}/"})
    public ResponseEntity<UserListDTO> getImageLiker(@PathVariable Long imageId) {
        return new ResponseEntity<>(new UserListDTO(userService.getImageLiker(imageId)), HttpStatus.OK);
    }

    @GetMapping ({"/user/image", "/user/image/"})
    public ResponseEntity<String> getUserImage(@RequestHeader (value = "Authorization") String auth) {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        return new ResponseEntity<>(userService.getUserImage(user), HttpStatus.OK);
    }

}
