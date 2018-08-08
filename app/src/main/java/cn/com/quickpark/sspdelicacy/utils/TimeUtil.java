package cn.com.quickpark.sspdelicacy.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by songdongliang on 2016/11/16.
 */

public class TimeUtil {

    /**
     * 15:00    分:秒
     *
     * @param time 毫秒
     * @return
     */
    public static String formatMinuteAndSecond(long time) {
        int second = (int) (time / 1000);
        return second / 60 + ":" + (second % 60 < 10 ? ("0" + second % 60) : second % 60);
    }

    /**
     * 10:02    时:分
     *
     * @param time 毫秒
     * @return
     */
    public static String formatHourAndMinute(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute);
    }

    /**
     * @param time
     * @return
     */
    public static String parseYearMonthDay(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        String result = sdf.format(date);
        if (result != null) result = result.split(" ")[0];
        if (result != null) return result;
        return "";
    }


    public static String formatMsgTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Calendar currentCalendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        if (Math.abs(currentDay - day) == 1) {
            return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        } else if (Math.abs(currentDay - day) > 1) {
            return calendar.get(Calendar.MONTH) + 1 + "月" + day + "日";
        } else {
            return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        }
    }

    /**
     * 10:02    时:分
     *
     * @param time 毫秒
     * @return
     */
    public static String formatHourAndMinuteC(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return (hour < 10 ? "0" + hour : hour) + "时" + (minute < 10 ? "0" + minute : minute) + "分";
    }

    /**
     * 获取当前年月格式为:yyyy-MM
     *
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        int month = date.getMonth() + 1;
        return (date.getYear() + 1900) + "-" + (month > 9 ? month : "0" + month);
    }

    /**
     * 获取当前年月的上一个月格式为:yyyy-MM
     *
     * @param date
     * @return
     */
    public static String getPreMonth(Date date) {
        int month = date.getMonth();
        if (month <= 0) {
            return (date.getYear() + 1899) + "-12";
        } else {
            return (date.getYear() + 1900) + "-" + (month > 9 ? month : "0" + month);
        }

    }

    /**
     * 获取当前年月的上两个月格式为:yyyy-MM
     *
     * @param date
     * @return
     */
    public static String getPreSecondMonth(Date date) {
        int month = date.getMonth();
        if (month == 0) {
            return (date.getYear() + 1899) + "-11";
        } else if (month == 1) {
            return (date.getYear() + 1899) + "-12";
        } else {
            return (date.getYear() + 1900) + "-" + ((month - 1) > 9 ? (month - 1) : "0" + (month - 1));
        }
    }

    public static Date getDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDifferenceValue(Date date1, Date date2) {
        long duration = getDuration(date1, date2);
        return formatHourAndMinuteC((int) duration / 1000);
    }

    public static long getDuration(Date date1, Date date2) {
        return date2.getTime() - date1.getTime();
    }

    public static String formatHourAndMinuteC(int second) {
        int h = second / (60 * 60);
        int s = (second % (60 * 60)) / 60;
        StringBuffer sb = new StringBuffer();
        if (h > 0) {
            sb.append(h + "时");
        }
        if (s >= 0) {
            sb.append(s + "分");
        }
        return sb.toString();
    }

    public static String strToMonthAndDay(String time) {
        if (time == null) return "";
        String[] dates = time.split(" ");
        String[] strs = dates[0].split("-");
        return strs[1] + "月" + strs[2] + "日";
    }

    public static String timeToMonthAndDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        StringBuilder sb = new StringBuilder(16);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) sb.append("0");
        sb.append(month);
        sb.append("月");
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10) sb.append("0");
        sb.append(day);
        sb.append("日");
        return sb.toString();
    }

    public static String formatHourAndMinute(String time) {
        if (TextUtils.isEmpty(time)) return "";
        return formatHourAndMinuteC(TimeUtil.getDate(time).getTime());
    }

    public static String getDurationByStr(String from, String to) {
        if (TextUtils.isEmpty(from) || TextUtils.isEmpty(to)) return "";
        return TimeUtil.getDifferenceValue(TimeUtil.getDate(from), TimeUtil.getDate(to));
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 通过时间格式的字符串得到周几
     *
     * @param time
     * @return
     */
    public static String getWeekByStr(String time) {
        if (TextUtils.isEmpty(time)) return "";
        return getWeekOfDate(getDate(time));
    }

    public static String getWeekOfTimeStamp(long time) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 两个时间的差值是否满足被给的duration
     *
     * @param start    开始时间
     * @param end      结束时间
     * @param duration 单位为小时
     * @return
     */
    public static boolean timeDifferentValue(long start, long end, int duration) {
        return duration >= (end - start) / 1000 / 60 / 60;
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * yyyy-mm-dd
     *
     * @param time
     * @return
     */
    public static String timeFormat(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time * 1000L));
    }

    /**
     * yyyy
     *
     * @param time
     * @return
     */
    public static String timeyy(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(new Date(time));
    }

    /**
     * 掉此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    public static String dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * String转Date
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String 转Long
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType) {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = date.getTime(); // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * long 转化 string
     *
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static String longToString(long currentTime, String formatType) {
        Date date = null; // long类型转成Date类型
        try {
            date = longToDate(currentTime, formatType);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * String TO String
     */
    public static String stringToString(String a) {
        return longToString(stringToLong(a, "MM月dd日"), "MM月dd日");
    }

    /**
     * 根据传入周几返回对应的数字  周一 =1；周二=2；。。。。
     */
    public static int weekToInt(String week) {
        switch (week) {
            case "周一":
                return 1;
            case "周二":
                return 2;
            case "周三":
                return 3;
            case "周四":
                return 4;
            case "周五":
                return 5;
            case "周六":
                return 6;
            case "周日":
                return 7;
        }
        return 1;
    }

    /**
     * 0：0格式转化成00：00
     */
    public static String timeformat(int hh, int mm) {
        String HH;
        if (hh < 10) {
            HH = "0" + hh;
        } else {
            HH = "" + hh;
        }
        String MM;
        if (mm < 10) {
            MM = "0" + mm;
        } else {
            MM = "" + mm;
        }
        return HH + ":" + MM;
    }

    /**
     * 7-1格式转化成00-00
     */
    public static String dataformat(int yy, int mm, int dd) {
        String HH;
        if (mm < 10) {
            HH = "0" + mm;
        } else {
            HH = "" + mm;
        }
        String MM;
        if (mm < 10) {
            MM = "0" + dd;
        } else {
            MM = "" + dd;
        }
        return yy + "-" + HH + "-" + MM;
    }

}
