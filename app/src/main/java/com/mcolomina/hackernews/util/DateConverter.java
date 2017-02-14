package com.mcolomina.hackernews.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class DateConverter {

    public static String unixToString(String unixDate) {
        long timestamp = Long.parseLong(unixDate) * 1000;
        Date time = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(time);
    }

}
