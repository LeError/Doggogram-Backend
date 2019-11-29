package com.doggogram.backendsvc.util.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordRequest {

    String oldPassword;
    String newPassword;

}
