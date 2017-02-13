package com.mcolomina.hackernews.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter {
    String date;

    public DateConverter(String unixDate) {

        long timestamp = Long.parseLong(unixDate) * 1000;
        Date time = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        date = sdf.format(time);
    }

    public String getDate() {
        return date;
    }
}
