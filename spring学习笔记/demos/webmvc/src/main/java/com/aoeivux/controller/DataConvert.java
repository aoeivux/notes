package com.aoeivux.controller;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConvert implements Converter<String, Date> {

    private String pattern;

    public DataConvert(String pattern) {
        this.pattern = pattern;
    }


    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat(this.pattern);
        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
