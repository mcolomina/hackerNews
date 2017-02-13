package com.mcolomina.hackernews.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateConverterTest {

    DateConverter dateConverter;

    @Before
    public void setUp() throws Exception {
        dateConverter = new DateConverter("1175714200");
    }

    @Test
    public void getDate() throws Exception {
        assertEquals("2007-04-04",dateConverter.getDate());
    }

}