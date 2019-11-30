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
import com.doggogram.backendsvc.util.requests.AuthRequest;
import com.doggogram.backendsvc.util.requests.ContentRequest;
import com.doggogram.backendsvc.util.requests.PasswordRequest;
import com.doggogram.backendsvc.util.responses.JwtTokenResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping ( value = {"/register", "/register/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(@RequestBody AuthRequest authRequest) throws UserRegistrationException {
        try {
            userService.registerUser(authRequest.getUser(), authRequest.getPass());
            return new ResponseEntity<>(null, HttpStatus.CREATED);
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

    @GetMapping ({"/follow/{followUser}", "/follow/{followUser}/"})
    public ResponseEntity<Boolean> toggleFollowUser(@RequestHeader (value = "Authorization") String auth, @PathVariable String followUser) throws EntityNotFoundException {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        return new ResponseEntity(userService.followUser(user, followUser), HttpStatus.CREATED);
    }

    @PostMapping (value = {"/update/password", "/update/password/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtTokenResponse> updatePassword(@RequestHeader (value = "Authorization") String auth, @RequestBody PasswordRequest passwordRequest) throws PasswordDoesNotMatchException {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        userService.updatePassword(user, passwordRequest.getOldPassword(), passwordRequest.getNewPassword());
        return new ResponseEntity<>(authService.generateJWTToken(user, passwordRequest.getNewPassword()), HttpStatus.OK);
    }

    @PostMapping (value = {"/update/bio", "/update/bio/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBio(@RequestHeader (value = "Authorization") String auth, @RequestBody ContentRequest contentRequest) {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        userService.updateBio(user, contentRequest.getContent());
        return new ResponseEntity(null, HttpStatus.OK);
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
            return new ResponseEntity(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ImageUploadException(e.getMessage());
        }
    }

    @PostMapping ({"/image/remove", "/image/remove/"})
    public ResponseEntity removeUserImage(@RequestHeader(value = "Authorization") String auth) {
        String user = jwtTokenService.getUserFromToken(Util.getJwtToken(auth));
        userService.removeImage(user);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
