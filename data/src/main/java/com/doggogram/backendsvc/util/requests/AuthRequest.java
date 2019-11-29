package com.doggogram.backendsvc.util.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthRequest implements Serializable {

    private String user;
    private String pass;

}
