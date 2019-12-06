package com.doggogram.backendsvc.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String user;
    private String bio;
    private String userImage;
    private Long images;
    private Long following;
    private Long followers;

}
