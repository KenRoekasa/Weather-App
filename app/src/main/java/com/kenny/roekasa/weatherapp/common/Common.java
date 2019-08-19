package com.kenny.roekasa.weatherapp.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static String API_KEY = "32d3bd8eb2580cd43e9c3e9d5176fd7f";
    public static String APi_LINK = "http://api.openweathermap.org/data/2.5/weather";


    public static String apiRequest(String city) {
        StringBuilder sb = new StringBuilder(APi_LINK);
//        sb.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric", lat, lng, API_KEY));
        sb.append(String.format("?q=%s&APPID=%s&units=metric", city, API_KEY));
        return sb.toString();
    }




    public static String unixTimeStampToDateTime(double unixTimeStamp) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long) unixTimeStamp*1000);
        return dateFormat.format(date);
    }

    public static String getImage(String icon) {
        return String.format("http://openweathermap.org/img/w/%s.png", icon);

    }

    public static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH::mm");
        Date date = new Date();
        return dateFormat.format(date);
    }




}
