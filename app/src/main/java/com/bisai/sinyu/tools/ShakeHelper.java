package com.bisai.sinyu.tools;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.widget.Toast;

import com.bisai.sinyu.bisai.QRcodeActivity;

/**
 * Created by sinyu on 2017/3/1.
 */
public class ShakeHelper implements SensorEventListener {
    private Context mContext;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int mSpeend=3000;
    private  int mInterval=100;
    private  long LastTime;
    private  float LastX,LastY,LastZ;
    public  ShakeHelper(Context mContext)
    {
        this.mContext=mContext;
        Start();
    }
    public  void Start()
    {
        mSensorManager=(SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager!=null)
            mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (mSensor!=null)
            mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_GAME);//注册
    }

    public void Stop()
    {
        mSensorManager.unregisterListener(this);
    }




    @Override
    public void onSensorChanged(SensorEvent event) {
        long NowTime= System.currentTimeMillis();
        if ((NowTime-LastTime)<mInterval)
            return;

        LastTime=NowTime;
        float NowX=event.values[0];
        float NowY=event.values[1];
        float NowZ=event.values[2];
        float DeltaX=NowX-LastX;
        float DeltaY=NowY-LastY;
        float DeltaZ=NowZ-LastZ;
        LastX=NowX;
        LastY=NowY;
        LastZ=NowZ;
        double NowSpeed= Math.sqrt(DeltaX * DeltaX + DeltaY * DeltaY + DeltaZ * DeltaZ)/mInterval * 10000;
        if (NowSpeed>=mSpeend)
        {
            Toast.makeText(mContext,"摇一摇",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(mContext,QRcodeActivity.class);
            mContext.startActivity(intent);
            Stop();
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
