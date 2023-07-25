package com.xfzcode.genie.utils;


import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static boolean isExpired(Date expiry) {
        return System.currentTimeMillis() > expiry.getTime();
    }

    public static boolean isNonExpired(Date expiry) {
        return System.currentTimeMillis() < expiry.getTime();
    }

    public static Date getMaxDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(2099, 12, 30, 0, 0, 0);
        return instance.getTime();
    }

}
