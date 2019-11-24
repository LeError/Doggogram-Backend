package com.doggogram.backendsvc.controller.v1;

import com.doggogram.backendsvc.dto.UserDTO;
import com.doggogram.backendsvc.dto.UserListDTO;
import com.doggogram.backendsvc.security.requests.AuthRequest;
import com.doggogram.backendsvc.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (value = "/api/v1/users")
public class UserController {

    private UserService userService;

    public  UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping ( value = {"/register", "/register/"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(@RequestBody AuthRequest authRequest) {
        try {
            userService.registerUser(authRequest.getUser(), authRequest.getPass());
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping ({"/$count", "/$count/"})
    public ResponseEntity<Integer> getCount() {
        return new ResponseEntity<>(userService.count(), HttpStatus.OK);
    }

    @GetMapping ({"/all", "/all/"})
    public ResponseEntity<UserListDTO> getUsers() {
        return new ResponseEntity<>(new UserListDTO(userService.getAllItems()), HttpStatus.OK);
    }

    @GetMapping ({"/user/{user}", "/user/{user}/"})
    public ResponseEntity<UserDTO> getUser(@PathVariable String user) {
        return new ResponseEntity<>(userService.findUserByUser(user), HttpStatus.OK);
    }

}
