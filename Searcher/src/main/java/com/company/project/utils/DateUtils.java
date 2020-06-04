package com.company.project.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author aturx
 * @version 0.1
 * @date 2020/06/04 10:06
 */
public class DateUtils {
    public static String formDate(Date solrDate) {
        String newDate = "";
        try {
            if (solrDate == null) {
                return null;
            }
            Calendar ca = Calendar.getInstance();
            ca.setTime(solrDate);
            ca.add(Calendar.HOUR_OF_DAY, -8);
            newDate = DateFormatUtils.format(ca.getTime(),"yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
