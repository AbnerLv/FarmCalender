package com.pyp.farmcalender.utils.calendar;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/6/9.
 */
public class DateUtil {

    public static String getCurrentTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }
}
