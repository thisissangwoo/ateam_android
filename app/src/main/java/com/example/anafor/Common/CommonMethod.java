package com.example.anafor.Common;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class CommonMethod {
    //공통으로 사용할 메소드들을 만들어서 사용
    static InputStream in = null;
    public static InputStreamReader executeAskGet(AsyncTask<String , String , InputStream> asktask){
        try {
           in = asktask.execute().get();
           return new InputStreamReader(in);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String AddDate(String strDate, int year, int month, int day) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();

        Date nowDate = format.parse(strDate);
        cal.setTime(nowDate);

        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE, day);

        return format.format(cal.getTime());
    }
}


