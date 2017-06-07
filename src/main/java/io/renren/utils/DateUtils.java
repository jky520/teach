package io.renren.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM) */
	public final static String DATE_MONTH_PATTERN = "yyyy-MM";
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    public static String getDate(String str,String pattern) {
    	if(pattern == null || "".equals(pattern)) pattern = DATE_MONTH_PATTERN;
    	SimpleDateFormat sdf=new SimpleDateFormat(pattern);  
		Date date = null;
		try {
			date=sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(date);
    }
}
