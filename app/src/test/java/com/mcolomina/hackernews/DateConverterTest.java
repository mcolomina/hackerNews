package com.mcolomina.hackernews;

import com.mcolomina.hackernews.app.HackerNewsApp;
import com.mcolomina.hackernews.util.DateConverter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateConverterTest {
    SimpleDateFormat sdf;
    Date date;

    @Before
    public void setUp(){
        sdf= new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        date = new Date(Calendar.getInstance().getTimeInMillis());
    }

    @Test
    public void unixToStringTest(){
        long unixDate = (long)date.getTime()/1000;
        String assertedDate = DateConverter.unixToString(String.valueOf(unixDate));
        Assert.assertNotNull(assertedDate);
    }

    @Test
    public void unixEqualsStringTest(){
        String currDate = sdf.format(date);
        long unixDate = (long)date.getTime()/1000;
        String assertedDate = DateConverter.unixToString(String.valueOf(unixDate));
        Assert.assertEquals(currDate,assertedDate);
    }

    @Test
    public void unixNotEqualsStringTest(){
        String currDate = sdf.format(date);
        long unixDate = (long)date.getTime();
        String assertedDate = DateConverter.unixToString(String.valueOf(unixDate));
        Assert.assertNotEquals(currDate,assertedDate);
    }

}