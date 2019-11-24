package com.doggogram.backendsvc.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@XmlRootElement (name = "error")
public class ErrorResponse {

    private String message;
    private List<String> details;

}
