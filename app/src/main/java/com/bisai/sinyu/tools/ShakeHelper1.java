package com.bisai.sinyu.tools;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by sinyu on 2017/3/2.
 */
public class ShakeHelper1 implements SensorEventListener {
    private Context mContext;
    private SensorManager sensorManager;
    private Sensor sensor;
    private long LastTime;
    private int mSpeed=3000;
    private int mInterval=100;
    private float LastX,LastY,LastZ;

    public ShakeHelper1(Context context)
    {
        this.mContext=context;
    }
    public void Start()
    {
        sensorManager= (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager!=null)
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor!=null)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);

    }
    public void Stop(){
        sensorManager.unregisterListener(this,sensor);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
       long NowTime= System.currentTimeMillis();
        if((NowTime-LastTime)<mInterval)
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
        double NowSpeed=Math.sqrt(DeltaX*DeltaX+DeltaY*DeltaY+DeltaZ*DeltaZ)/mInterval*10000;
        if (NowSpeed>=mSpeed)
        {
            Toast.makeText(mContext,"摇一摇1",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
