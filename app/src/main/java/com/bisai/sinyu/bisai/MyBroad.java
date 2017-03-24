package com.bisai.sinyu.bisai;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by sinyu on 2017/3/4.
 */
public class MyBroad extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("www.sinyu1012.cn"))
        {
            String time=intent.getStringExtra("time");
            TimerActivity.tv1.setText("时间："+time);
            String[] temp=time.split(":");
            int h=Integer.parseInt(temp[0]);
            int m=Integer.parseInt(temp[1]);
            if (m>=30)
                h++;
            TimerActivity.tv2.setText("费用："+h*5);

        }
    }
}
