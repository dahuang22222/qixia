package com.changjianghuyu.qixia.web.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFormateUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DataFormateUtils.class);

    //将时间转换为时间戳(10位数的）
    public static long getStringTimestamp(String createTime) {
        long timeStamp = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long time = sdf.parse(createTime).getTime();
            timeStamp = time / 1000;
        } catch (ParseException e) {
            LOG.error("系统错误"+e.getMessage());
        }
        return timeStamp;
    }
    //将时间戳转换为时间
    public static String stampToTime(String timeStamp) {
        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long createTime = new Long(timeStamp);

        //将时间戳转换为时间
        Date date = new Date(createTime*1000);
        //将时间调整为yyyy-MM-dd HH:mm:ss时间样式
        time = simpleDateFormat.format(date);
        return time;
    }
    //时间转化程string
    public static String dateToString(Date clockTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(clockTime);
        return dateString;
    }


    //时间转化程string(其中只返回年月日）
    public static String dateToSimpleYearMonthDay(Date clockTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(clockTime);
        return dateString;
    }
    /*
    *
    * 时间转化程string(其中只返回年月）  文件夹专用
     */
    public static String dateToSimpleYearMonth(Date clockTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String dateString = sdf.format(clockTime);
        return dateString;
    }


    /**
     * 按照天，增加时间
     * @param time
     * @param days
     * @return
     */
    public static Date timeAddition(Date time , int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DATE, days);// 24小时制
        time = cal.getTime();
        cal = null;
        return time;
    }


    /**
     * 按照天，减少时间
     * @param time
     * @param days
     * @return
     */
    public static Date timeReduce(Date time , int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DATE, -days);// 24小时制
        time = cal.getTime();
        cal = null;
        return time;
    }

    /**
     * 按照小时，减少时间
     * @param time
     * @param hours,time
     * @return
     */
    public static Date timeReduceByHours(Date time , int hours){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.HOUR, -hours);// 24小时制
        time = cal.getTime();
        cal = null;
        return time;
    }

    /**
     * 按照小时，增加时间
     * @param time
     * @param hours,time
     * @return
     */
    public static Date timeAdditionByHours(Date time , int hours){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.HOUR, hours);// 24小时制
        time = cal.getTime();
        cal = null;
        return time;
    }

    //时间转化成当日零时时间
    public static Date getDayBeginOfDate(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        Date beginOfDate = calendar1.getTime();
        return beginOfDate;
    }

    //时间转化成当日24时时间
    public static Date getDayEndOfDate(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        Date endOfDate = calendar1.getTime();
        return endOfDate;
    }
    /**
     * 手机号验证
     * @param phone
     * @return
     */
    public static int phoneToFormate(String phone){
        String regex = "^((13)|(14)|(15)|(16)|(17)|(18))\\d{9}$";
        if(phone.length() != 11){
            //手机号位数不符合
            return -1;
        }else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                //校验成功
                return 1;
            } else {
                //格式错误
                return -2;
        }
        }
    }

    public static void main(String[] args) {

        for (int i = 0;i<10;i++){

            if(i == 3){
                System.out.println(i+"break");
                break;
            }
            System.out.println(i+"break外部");
        }

        for (int i = 0;i<10;i++){

            if(i == 3) {
                System.out.println(i+"continue");
                continue;
            }System.out.println(i+"continue外部");
        }
    }
}
