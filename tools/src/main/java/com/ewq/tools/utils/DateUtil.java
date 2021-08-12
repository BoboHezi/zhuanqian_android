package com.ewq.tools.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Description: 常用的日期格式化方法
 */
@SuppressWarnings("unused")
public class DateUtil {
    // 注意SimpleDateFormat不是线程安全的
    private static final SoftHashMap<String, ThreadLocal<SimpleDateFormat>> map = new SoftHashMap<>();

    /**
     * 日期格式化
     */
    public static String formatter(Format format, Object date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf;
            String key = format.getValue();
            if (map.containsKey(key)) {
                sdf = map.get(key).get();
            } else {
                sdf = new SimpleDateFormat(key, Locale.getDefault());
                ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();
                threadLocal.set(sdf);
                map.put(key, threadLocal);
            }
            return sdf.format(new Date(ConverterUtil.getLong(date.toString())));
        }
    }

    /**
     * 日期格式化
     */
    public static String formatter(String format, Object date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
            return sdf.format(new Date(ConverterUtil.getLong(date.toString())));
        }
    }

    /**
     * 初略的剩余时间，年、个月、天、小时、分钟
     */
    public static String getTimeLeft(Object time) {
        if (time == null) {
            return "";
        } else {
            long diffValue = ConverterUtil.getLong(time.toString());

            long minute = 1000 * 60;
            long hour = minute * 60;
            long day = hour * 24;
            long month = day * 30;
            long year = month * 12;

            long _year = diffValue / year;
            long _month = diffValue / month;
            long _day = diffValue / day;
            long _hour = diffValue / hour;
            long _min = diffValue / minute;

            if (_year >= 1) {
                return (_year) + "年";
            } else if (_month >= 1) {
                return (_month) + "个月";
            } else if (_day >= 1) {
                return (_day) + "天";
            } else if (_hour >= 1) {
                return (_hour) + "小时";
            } else {
                return (_min) + "分钟";
            }
        }
    }

    /**
     * 倒计时格式化，时:分:秒
     */
    public static String getCountdownTime(Object time) {
        if (time == null) {
            return "";
        } else {
            long diffValue = ConverterUtil.getLong(time.toString());
            long day = diffValue / (24 * 60 * 60 * 1000);
            long hour = (diffValue / (60 * 60 * 1000) - day * 24);
            long min = ((diffValue / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long sec = (diffValue / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            //return (hour > 9 ? hour : ("0" + hour)) + ":" + (min > 9 ? min : ("0" + min)) + ":" + (sec > 9 ? sec : ("0" + sec));
            return hour > 0 ? hour + ":" + min + ":" + sec : (min > 0 ? min + ":" + sec : sec + "");
        }
    }

    /**
     * 倒计时格式化，时:分:秒
     */
    public static String getCountdownTimeTwo(Object time) {
        if (time == null) {
            return "";
        } else {
            long diffValue = ConverterUtil.getLong(time.toString());
            long day = diffValue / (24 * 60 * 60 * 1000);
            long hour = (diffValue / (60 * 60 * 1000) - day * 24);
            long min = ((diffValue / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long sec = (diffValue / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            // return (hour > 9 ? hour : ( hour)) + "小时" + (min > 9 ? min : (min)) + "分钟" + (sec > 9 ? sec : ("0" + sec));
            return hour > 0 ? hour + "小时" + min + "分钟" + sec + "秒" : (min > 0 ? min + "分钟" + sec + "秒" : sec + "秒");

        }
    }

    public static String getLongForDate(long time) {
        if (time <= 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(time);
        return dateString;
    }

    /**
     * 获取当前时间的毫秒
     *
     * @return yyyyMMddHHmmss格式的字符串
     */
    public static String getCurrentTime() {
        Date now = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd HH:mm");
        dateformat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String sDateTime = dateformat.format(now);
        return sDateTime;
    }

    public static String getLongForTime(long time) {
        if (time <= 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(time);
        return dateString;
    }

    public static String getSubStringForDate(String time) {
        String dateString = null;
        if (!TextUtil.isEmpty(time) && time.length() > 10) {
            dateString = time.substring(0, 10);
        }
        return dateString;
    }

    /**
     * @Desction : 传入时间戳和当前时间比较天数
     * Creater at 2019/4/20 20:39
     */
    public static int daysOfTwo(String time) {
        int result = 0;
        try {
            String ftime = stampToDateTwo(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //跨年的情况会出现问题哦
            //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 1
            Date fDate = sdf.parse(ftime);
            Date oDate = sdf.parse(getNowDateTwo());
            Calendar aCalendar = Calendar.getInstance();
            aCalendar.setTime(fDate);
            int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
            aCalendar.setTime(oDate);
            int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
            result = day1 - day2;

            result = differDayQty(oDate, fDate);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return result;
    }

    /*
     * 将时间戳转换为日期
     */
    public static String stampToDateTwo(String s) {
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long lt = new Long(s);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @Desction : 得到当前的日期
     * Creater at 2019/3/22 14:25
     */
    public static String getNowDateTwo() {
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            return f.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 比较两个日期Date2比Date1(年月日)多的天数,(只考虑天数不考虑时间)
     * 例如:2017-01-25 23:59:59与 2017-01-26 00:00:00   返回1
     * 2017-01-25 与 2017-01-25   返回0
     * 2017-01-28 与 2017-01-25   返回-3
     *
     * @author terry.peng
     */
    public static int differDayQty(Date Date1, Date Date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(Date1);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);
        int year1 = calendar.get(Calendar.YEAR);
        calendar.setTime(Date2);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);
        int year2 = calendar.get(Calendar.YEAR);
        if (year1 == year2) {//同一年
            return day2 - day1;
        } else if (year1 < year2) {//Date1<Date2
            int days = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//闰年
                    days += 366;
                } else {
                    days += 365;
                }
            }
            return days + (day2 - day1);
        } else {//Date1>Date2
            int days = 0;
            for (int i = year2; i < year1; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    days += 366;
                } else {
                    days += 365;
                }
            }
            return 0 - days + (day2 - day1);
        }
    }

    public enum Format {
        /**
         * 日期 + 时间类型格式，到秒
         */
        SECOND("yyyy-MM-dd HH:mm:ss"),
        /**
         * 日期 + 时间类型格式，到分
         */
        MINUTE("yyyy-MM-dd HH:mm"),
        /**
         * 日期类型格式，到日
         */
        DATE("yyyy-MM-dd"),
        /**
         * 日期类型格式，到月
         */
        MONTH("yyyy-MM"),
        /**
         * 日期类型格式，到月
         */
        MONTH_CHINA("yyyy年MM月"),
        /**
         * 时间类型的格式
         */
        TIME("HH:mm:ss");
        // 格式化格式
        private final String value;

        Format(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
