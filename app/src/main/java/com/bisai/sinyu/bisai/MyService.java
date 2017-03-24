package com.bisai.sinyu.bisai;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    String time="00:29:30";
    Timer timer;

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent();
                intent.setAction("www.sinyu1012.cn");
                intent.putExtra("time",time);
                sendBroadcast(intent);

                time=getTime(time);
            }
        },0,1000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }


    public String getTime(String time) {
        String[] temp=time.split(":");
        int h=Integer.parseInt(temp[0]);
        int m=Integer.parseInt(temp[1]);
        int s=Integer.parseInt(temp[2]);
        if (s<59)
        {
            s++;
        }else {
            s=0;
            if (m<59)
            {
                m++;
            }else {
                m=0;
                h++;
            }
        }
        String res=String.format("%d",h)+":"+String.format("%02d",m)+":"+String.format("%02d",s);



        return res;
    }
}
