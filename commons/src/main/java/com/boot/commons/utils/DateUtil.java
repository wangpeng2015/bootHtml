package com.boot.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;


public class DateUtil {

    public static String FORMAT_MS = "yyyy-MM-dd HH:mm:ss.S";

    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";

    public static final String FORMAT_FOUR = "yyyyMMddHHmmss";

    public static final String FORMAT_FIVE = "MM-dd HH:mm";

    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";

    public static final String EIGHT_STYLE_DATE_FORMAT = "yyyyMMdd";

    public static final String SHORT_DATE_FORMAT = "MM-dd";

    public static final String LONG_TIME_FORMAT = "HH:mm:ss";

    public static final String MONTG_DATE_FORMAT = "yyyy-MM";

    public static final String MONTH_DATE_FORMAT_2 = "yyyy/MM";

    public static final int SUB_YEAR = Calendar.YEAR;

    public static final int SUB_MONTH = Calendar.MONTH;

    public static final int SUB_DAY = Calendar.DATE;

    public static final int SUB_HOUR = Calendar.HOUR;

    public static final int SUB_MINUTE = Calendar.MINUTE;

    public static final int SUB_SECOND = Calendar.SECOND;

    public static final String FORMAT_Five_New = "yyyyMMddHHmmssSS";

    @SuppressWarnings("unused")
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(FORMAT_ONE);

    public DateUtil() {}

    /**
     * 将时间转换为毫秒值
     */
    public static long getMilliseconds(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal.getTimeInMillis();
    }

    /**
     * 获取剩余时间
     * @param e_time 截止时间
     * @param s_time 开始时间
     * @return
     */
    public static String remainTime(Date e_time, Date s_time) {
        long stime = getMilliseconds(s_time);
        long etime = getMilliseconds(e_time);
        long sub_time = etime - stime;
        Integer day = (int) (sub_time / 86400000);// 天数
        Integer hour = (int) ((sub_time % 86400000) / 3600000);
        Integer min = (int) (((sub_time % 86400000) % 3600000) / 60000);
        Integer secound = (int) (((sub_time % 86400000) % 3600000) % 60000) / 1000;
        return day + "天" + hour + "小时" + min + "分钟" + secound + "秒";
    }

    /**
     * 求两个时间的时间差，返回秒
     * @param startTime
     * @param endTime
     * @return
     */
    public static long subTimeReturnSecound(Date startTime, Date endTime) {
        long stime = getMilliseconds(startTime);
        long etime = getMilliseconds(endTime);
        long sub_time = (etime - stime) / 1000;
        return sub_time;
    }

    /**
     * 将秒转换成页面展示的类型
     * @param secounds
     * @return
     */
    public static String getPageTime(Integer secounds) {
        Integer min = secounds / 60;
        Integer second = secounds % 60;
        return min + ":" + second;
    }

    public static Date parseDateFromString(String date) {
        return parseDateFromString(date, FORMAT_ONE);
    }

    public static Date parseDateFromString(String date, String format) {
        return parseDateFromString(date, format, Locale.US);
    }

    public static Date parseDateFromString(String date, String format, Locale locale) {
        SimpleDateFormat fm = new SimpleDateFormat(format, locale);
        ParsePosition pos = new ParsePosition(0);
        return fm.parse(date, pos);
    }

    public static Date formatDate(Date date, String format) {
        String time_str = formatDateTime(date, format);
        return toDate(time_str, format);
    }

    public static Date toDate(String dateStr) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(FORMAT_ONE);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }

    public static Date toDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }

    public static String formatDateTime(Date date) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(FORMAT_ONE);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }

    public static String formatDateTime(Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }

    public static String getCurrDate(String format) {
        return formatDateTime(new Date(), format);
    }

    public static String dateSub(int dateKind, String dateStr, int amount) {
        Date date = toDate(dateStr, FORMAT_ONE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return formatDateTime(calendar.getTime(), FORMAT_ONE);
    }

    public static long timeSub(String firstTime, String secTime) {
        long first = toDate(firstTime, FORMAT_ONE).getTime();
        long second = toDate(secTime, FORMAT_ONE).getTime();
        return (second - first) / 1000;
    }

    public static long timeSub(Date firstTime, Date secTime) {
        long first = firstTime.getTime();
        long second = secTime.getTime();
        return (second - first) / 1000;
    }

    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("1") || month.equals("3") || month.equals("5") || month.equals("7") || month.equals("8") || month.equals("10") || month.equals("12")) {
            days = 31;
        } else if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }
        return days;
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    public static int yearDiff(String before, String after) {
        Date beforeDay = toDate(before, LONG_DATE_FORMAT);
        Date afterDay = toDate(after, LONG_DATE_FORMAT);
        return getYear(afterDay) - getYear(beforeDay);
    }

    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = toDate(after, LONG_DATE_FORMAT);
        return getYear(beforeDay) - getYear(afterDay);
    }

    public static long dayDiffCurr(String before) {
        Date currDate = DateUtil.toDate(currDay(), LONG_DATE_FORMAT);
        Date beforeDate = toDate(before, LONG_DATE_FORMAT);
        return (currDate.getTime() - beforeDate.getTime()) / 86400000;

    }

    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // c.set(year, month - 1, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // c.set(year, month - 1,
                                                // getDaysOfMonth(year, month));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"
     * 
     * @return
     */
    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return formatDateTime(today.getTime(), FORMAT_ONE);
    }

    /**
     * 
     * 
     * @param birth
     *            YYYY-mm-dd
     * @return
     */
    public static String getAstro(String birth) {
        if (!isDate(birth)) {
            birth = "2000" + birth;
        }
        if (!isDate(birth)) {
            return "";
        }
        int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1, birth.lastIndexOf("-")));
        int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
        String s = "魔锟斤拷水瓶双锟斤拷牡锟斤拷锟脚Ｋ拷泳锟叫肥拷哟锟脚拷锟斤拷锟斤拷蝎锟斤拷锟斤拷魔锟斤拷";
        int[] arr = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
        int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
        return s.substring(start, start + 2) + "锟斤拷";
    }

    /**
     * 
     * @param date
     *            YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    public static Date nextMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public static Date nextDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.DAY_OF_YEAR, day);
        return cal.getTime();
    }

    public static String addDays(Date date, int amount, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, amount);
        if (null == format || "".equals(format))
            format = DateUtil.LONG_DATE_FORMAT;

        return DateUtil.formatDateTime(cal.getTime(), format);
    }

    public static String nextDay(int day, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, day);
        return formatDateTime(cal.getTime(), format);
    }

    public static Date nextWeek(Date date, int week) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.WEEK_OF_MONTH, week);
        return cal.getTime();
    }

    public static String currDay() {
        return DateUtil.formatDateTime(new Date(), DateUtil.LONG_DATE_FORMAT);
    }

    public static String currDays() {
        return DateUtil.formatDateTime(new Date(), DateUtil.FORMAT_ONE);
    }

    public static String befoDay() {
        return befoDay(DateUtil.LONG_DATE_FORMAT);
    }

    public static String befoDay(String format) {
        return DateUtil.formatDateTime(DateUtil.nextDay(new Date(), -1), format);
    }

    public static String befoWeek(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);
        return DateUtil.formatDateTime(cal.getTime(), format);
    }

    public static String befoMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        return DateUtil.formatDateTime(cal.getTime(), format);
    }

    public static String afterDay() {

        return DateUtil.formatDateTime(DateUtil.nextDay(new Date(), 1), DateUtil.LONG_DATE_FORMAT);
    }

    public static int getDayNum() {
        int daynum = 0;
        GregorianCalendar gd = new GregorianCalendar();
        Date dt = gd.getTime();
        GregorianCalendar gd1 = new GregorianCalendar(1900, 1, 1);
        Date dt1 = gd1.getTime();
        daynum = (int) ((dt.getTime() - dt1.getTime()) / (24 * 60 * 60 * 1000));
        return daynum;
    }

    public static Date getDateByNum(int day) {
        GregorianCalendar gd = new GregorianCalendar(1900, 1, 1);
        Date date = gd.getTime();
        date = nextDay(date, day);
        return date;
    }

    public static String getYmdDateCN(String datestr) {
        if (datestr == null)
            return "";
        if (datestr.length() < 10)
            return "";
        StringBuffer buf = new StringBuffer();
        buf.append(datestr.substring(0, 4)).append(datestr.substring(5, 7)).append(datestr.substring(8, 10));
        return buf.toString();
    }

    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        return formatDateTime(cal.getTime(), format);
    }

    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return formatDateTime(cal.getTime(), format);
    }

    public static Date getLastDayOfMonth(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static Date getStartOfDay(Date day) {
        return getStartOfDay(day, Calendar.getInstance());
    }

    public static Date getTodayStart() {
        return getStartOfDay(new Date());
    }

    public static Date getBeforeDate(Date mirror, int dayNum) {
        Calendar current = Calendar.getInstance();
        current.setTime(mirror);

        current.add(Calendar.HOUR, -24 * dayNum);
        return current.getTime();
    }

    public static Date getStartOfDay(Date day, Calendar cal) {
        if (day == null)
            day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getEndOfDay(Date day) {
        return getEndOfDay(day, Calendar.getInstance());
    }

    public static Date getEndOfDay(Date day, Calendar cal) {
        if (day == null)
            day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Long timeDiffForDay(Date pBeginTime, Date pEndTime) {
        try {
            Long beginL = pBeginTime.getTime();
            Long endL = pEndTime.getTime();
            Long day = (endL - beginL) / 86400000;
            return day;
        } catch (Exception e) {
            return null;
        }
    }

    public static Long timeDiffForHour(Date pBeginTime, Date pEndTime) throws ParseException {
        Long beginL = pBeginTime.getTime();
        Long endL = pEndTime.getTime();
        Long hour = ((endL - beginL) % 86400000) / 3600000;
        return hour;
    }

    public static Long timeDiffForMin(Date pBeginTime, Date pEndTime) throws ParseException {
        Long beginL = pBeginTime.getTime();
        Long endL = pEndTime.getTime();
        Long min = ((endL - beginL) % 86400000 % 3600000) / 60000;
        return min;
    }

    public static String getTime(Date time) {
        String result = null;
        Long temp = null;
        final Date currentDate = new Date();

        do {
            try {
                temp = timeDiffForDay(time, currentDate);
                if (temp > 0l) {
                    result = temp + "天前";
                    break;
                }
                temp = timeDiffForHour(time, currentDate);
                if (temp > 0l) {
                    result = temp + "小时前";
                    break;
                }
                temp = timeDiffForMin(time, currentDate);
                if (temp > 0l) {
                    result = temp + "分钟前";
                    break;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } while (false);

        return result == null ? "锟秸诧拷" : result;
    }



    public static Date getMonthDelay(Date currentDay, int month) {
        Calendar current = Calendar.getInstance();
        current.setTime(currentDay);

        current.add(Calendar.MONTH, month);
        current.add(Calendar.HOUR, -24);
        return current.getTime();
    }

    public static Date getStartOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    public static Date getStartOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getEndOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static Date getMinEndOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static int getDaysOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getWorkTime(String starttime, String endtime) {
        // 璁剧疆鏃堕棿鏍煎紡
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT);
        // 锟�锟斤拷鏃ユ湡
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = dateFormat.parse(starttime);
            dateTo = dateFormat.parse(endtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int workdays = 0;
        Calendar cal = null;
        while (dateFrom.before(dateTo) || dateFrom.equals(dateTo)) {
            cal = Calendar.getInstance();
            // 璁剧疆鏃ユ湡
            cal.setTime(dateFrom);
            if ((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) && (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
                // 杩涜姣旇緝锛屽鏋滄棩鏈熶笉绛変簬鍛ㄥ叚涔熶笉绛変簬鍛ㄦ棩锛屽伐浣滄棩+1
                workdays++;
            }
            // 鏃ユ湡锟�
            cal.add(Calendar.DAY_OF_MONTH, 1);
            dateFrom = cal.getTime();
        }
        return workdays;
    }

    public static int getYearDays(Calendar cal) {
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.roll(Calendar.DAY_OF_YEAR, -1);
        int yearDays = cal.get(Calendar.DAY_OF_YEAR);
        return yearDays;
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());// Monday
        return cal.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6);// Sunday
        return cal.getTime();
    }

    public static String getNextMonth0fDay(Date date, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 2);
        cal.add(Calendar.DATE, -1);
        return formatDateTime(cal.getTime(), format);

    }



    /**
     * 指锟斤拷锟斤拷date时锟斤拷锟叫∈憋拷锟斤拷锟斤拷雍锟斤拷锟斤拷锟斤拷息锟斤拷
     * 
     * @param date
     *            指锟斤拷锟斤拷锟斤拷时锟斤拷
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date setTime(Date date, int hour, int minute, int second) {
        return setTime(date, hour, minute, second, -1);
    }

    public static Date setTime(Date date, int hour, int minute, int second, int millSecond) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (hour > -1) {
            cal.set(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute > -1) {
            cal.set(Calendar.MINUTE, minute);
        }
        if (second > -1) {
            cal.set(Calendar.SECOND, second);
        }
        if (millSecond > -1) {
            cal.set(Calendar.MILLISECOND, millSecond);
        }
        return cal.getTime();
    }

    /**
     * 锟斤拷时锟斤拷转锟斤拷锟缴猴拷锟斤拷值
     * 
     * @param date
     *            时锟斤拷
     * @param format
     *            锟斤拷式
     * @return
     */
    public static long DateToLong(Date date, String format) {
        if (date == null) {
            date = new Date(0);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date_str = sdf.format(date);
        try {
            long millionSeconds = sdf.parse(date_str).getTime();
            return millionSeconds;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @return
     */
    public static long getCurrentStamp() {
        return getCurrentStamp(DateUtil.FORMAT_ONE);
    }

    /**
     * 锟斤拷取锟斤拷前时锟斤拷锟绞憋拷锟斤拷
     * @return
     */
    public static long getCurrentStamp(String format) {
        return DateToLong(new Date(), format);
    }

    /**
     * 锟斤拷锟斤拷转锟斤拷锟斤拷时锟斤拷
     * @param mills
     * @param format
     * @return
     */
    public static String convertMsToDateStr(Long ms, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        return formatter.format(calendar.getTime());
    }

    public static Date addTimeForMinute(Date date, int value) {
        return addTime(date, Calendar.MINUTE, value);
    }

    public static Date addTimeForHour(Date date, int value) {
        return addTime(date, Calendar.HOUR, value);
    }

    public static Date addTimeForDay(Date date, int value) {
        return addTime(date, Calendar.DATE, value);
    }

    public static Date addTimeForMonth(Date date, int value) {
        return addTime(date, Calendar.MONTH, value);
    }

    public static Date addTimeForWeek(Date date, int value) {
        return addTime(date, Calendar.WEEK_OF_MONTH, value);
    }

    public static Date addTime(Date date, int field, int value) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, value);
        return cal.getTime();
    }

    /**
     * 灏嗘绉掓暟鎹㈢畻鎴恱澶﹛鏃秞鍒唜绉抶姣
     *
     * @param ms
     * @return
     */
    public static String format(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        // String strDay = day < 10 ? "0" + day : "" + day;

        // String strHour = hour < 10 ? "0" + hour : "" + hour;
        long hours = day * 24 + hour;

        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        return hours + ":" + strMinute + "'" + strSecond + "\"";
    }

    public static Date getDateFromMilliseconds(long milliseconds) {
        Date dd = new Date(Long.valueOf("1414125743480"));
        return dd;
    }

    public String getDateStrFromMilliseconds(long milliseconds, String formate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        return sdf.format(milliseconds);
    }

    public static long printDifference(Date startDate, Date endDate) {
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long minutes = elapsedDays * 24 * 60 + elapsedHours * 60 + elapsedMinutes;

        return minutes;
    }

    public static void main(String args[]) {
        Calendar c = Calendar.getInstance();
        long milliseconds = c.getTimeInMillis();
        System.out.println("当前时间毫秒值:" + milliseconds);
        // 当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("直接格式化毫秒值输出:" + sdf.format(milliseconds));
        // 2011-08-20 04:27:16
        Date d = new Date(milliseconds);
        // 转换成Date对象
        System.out.println("Date对象输出时间:" + sdf.format(d));

        Date dd = new Date(Long.valueOf("1418628155219"));
        System.out.println("Date对象输出时间:" + sdf.format(dd));

        Date _dd = DateUtil.getDateFromMilliseconds(1414557181221L);
        System.out.println("Date对象输出时间---:" + sdf.format(_dd));


        Date _da = DateUtil.toDate("2015-03-09 15:48:16", DateUtil.FORMAT_ONE);
        long minutes = DateUtil.printDifference(_da, new Date());
        System.out.println(minutes);
    }
}
