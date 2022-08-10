package com.android.uv;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DataUtils {
    private static final String url =
            //Australia
            "https://api.openweathermap.org/data/3.0/onecall?lat=-37.81&lon=145.03&exclude=hourly,daily,minutely&appid=30b5da8e70056c4572570b6d7f5fa8f5";//Melbourne
            //Shanghai
            //"https://api.openweathermap.org/data/3.0/onecall?lat=31.2304&lon=121.4737&exclude=hourly,daily,minutely&appid=30b5da8e70056c4572570b6d7f5fa8f5";//shanghai
            //Singapore
            //"https://api.openweathermap.org/data/3.0/onecall?lat=1.3521&lon=103.8198&exclude=hourly,daily,minutely&appid=30b5da8e70056c4572570b6d7f5fa8f5";//shanghai

    /**
     * 进行查询
     */
    public static String doQuery(Activity activity,ProgressDialog progressDialog){
        //获取InputStream对象
        String result = "";
        String temp = "";
        InputStream is = getStreamByUrl(url);
        byte[] buffer = new byte[4096];//开辟缓冲区,大小4K
        try {
            Thread.sleep(20);
            is.read(buffer);
            String json  = new String(buffer,"utf-8");
            //获取Json对象
            JSONObject obj = new JSONObject(json);

            if(obj.has("current")) {
                temp = replaceJson(obj.getJSONObject("current").getString("temp"));
                result = replaceJson(obj.getJSONObject("current").getString("uvi"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
        }
        return result;
    }

    public static String doQuerytimecoon(Activity activity,ProgressDialog progressDialog){
        //获取InputStream对象
        String result = "";
        String temp = "";
        InputStream is = getStreamByUrl(url);
        byte[] buffer = new byte[4096];//开辟缓冲区,大小4K
        try {
            Thread.sleep(20);
            is.read(buffer);
            String json  = new String(buffer,"utf-8");
            //获取Json对象
            JSONObject obj = new JSONObject(json);

            if(obj.has("timezone")) {
                result = replaceJson(obj.getString("timezone"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
        }
        return result;
    }

    private static String replaceJson(String value)
    {
        value=value.replace(",", "\n");
        value=value.replace("\"", "");
        value=value.replace("[", "");
        value=value.replace("]", "");
        return value;
    }

    public static InputStream getStreamByUrl(String _url)
    {
        URL url;
        InputStream inputStream=null;
        try {
            url = new URL(_url);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection)connection; //发送网络请求服务
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                inputStream = httpConnection.getInputStream();
            }
        } catch (Exception e) {
            return null;
        }
        return inputStream;
    }
}
