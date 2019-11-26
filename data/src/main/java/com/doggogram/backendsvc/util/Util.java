package com.doggogram.backendsvc.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static String getImageName(String user, String fileExtension) {
        String filename = user;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("--dd-MM-yyyy--HH-mm-ss");
        filename += dateFormat.format(date);
        filename += "." + fileExtension;
        return filename;
    }

    public static String getJwtToken(String auth) {
        return auth.split("\\s+")[1];
    }

}
