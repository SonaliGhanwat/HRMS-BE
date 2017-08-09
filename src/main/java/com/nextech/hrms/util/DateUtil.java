package com.nextech.hrms.util;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
public static Date convertToDate(String date) throws ParseException{
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
return format.parse(date);
}

public static String convertToString(Date date) throws ParseException{
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
return format.format(date);
}
}
