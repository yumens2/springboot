package com.example.springbootf.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public String getNowDate(){
        Date now = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");

        return date.format(now);
    }
}
