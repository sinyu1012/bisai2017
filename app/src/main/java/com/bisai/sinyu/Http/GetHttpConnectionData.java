package com.bisai.sinyu.Http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sinyu on 2017/3/2.
 */
public class GetHttpConnectionData {
    public static String getData(String url) {
        Log.d("http:", "getData: 0");
        String res="";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL urlcon = new URL(url);
            Log.d("http:", "getData: 1");
            connection= (HttpURLConnection) urlcon.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
          //  connection.connect();
            String data="{'CarId':1}";
           // String data="email=123&content=456";
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            byte[] content = data.getBytes("utf-8");

            out.write(content, 0, content.length);
            out.flush();
            out.close(); // flush and close

//            connection.setDoOutput(true);
//            OutputStream out=connection.getOutputStream();
//
//            out.write(data.getBytes());
           //0 data.writeBytes();

            Log.d("http:", "getData: 3");

            InputStream in=connection.getInputStream();
            //读取
            reader=new BufferedReader(new InputStreamReader(in));
            StringBuilder response=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null)
            {
                Log.d("http:", "getData: 4");
                response.append(line);
            }
            res=response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader!=null)
            {
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (connection!=null)
            {
                connection.disconnect();
            }
        }


        return res;
    }
}
