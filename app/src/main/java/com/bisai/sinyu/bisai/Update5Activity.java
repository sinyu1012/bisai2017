package com.bisai.sinyu.bisai;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class Update5Activity extends AppCompatActivity implements View.OnClickListener {
    private Switch sw_open;
    private Boolean isOpen=false;
    private TextView tv1;
    private static final String TAG = "Update5Activity";
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update5);
        timer=new Timer();

        tv1= (TextView) findViewById(R.id.tv1);
        sw_open= (Switch) findViewById(R.id.sw_open);
        sw_open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){//开
                    TimeTask();
                    Log.d(TAG,"kai");
                }else {
                    timer.cancel();
                    Log.d(TAG,"guan");
                }
            }
        });
      //  sw_open.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpPost();
            }
        }).start();
    }

    private void TimeTask(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                HttpPost();
            }
        },0,5000);
    }
    private void HttpPost(){
        HttpURLConnection conn;
        BufferedReader reader;
        URL url;
        try {
            url=new URL("http://192.168.1.103:8080/transportservice/type/jason/action/GetAllSense.do");//"http://192.168.1.103:8080/transportservice/type/jason/action/GetCarSpeed.do"
            conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(8000);
           // conn.setDoOutput(true);
//            conn.setRequestProperty("Content-Type","application/json");
//            String data="{'CarId':1}";
//            byte[] content=data.getBytes("utf-8");
//            DataOutputStream out=new DataOutputStream(conn.getOutputStream());
//            out.write(content,0,content.length);
//            out.flush();
//            out.close();
            InputStream in=conn.getInputStream();

            reader=new BufferedReader(new InputStreamReader(in));
            StringBuilder str=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                str.append(line);
            }
            Message msg=new Message();
            msg.what=1;
            msg.obj=str.toString();
            handler.sendMessage(msg);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1)
            {
                String str=msg.obj.toString();
                String pm="";
                try {
                    JSONObject json1=new JSONObject(str);
                    String serverinfo=json1.getString("serverinfo");
                    JSONObject json2=new JSONObject(serverinfo);
                    pm=json2.getString("pm2.5")+"";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,str);
                tv1.setText("PM2.5:  "+pm);
            }
        }
    };
    @Override
    public void onClick(View v) {

    }
}
