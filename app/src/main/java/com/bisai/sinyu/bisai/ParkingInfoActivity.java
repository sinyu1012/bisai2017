package com.bisai.sinyu.bisai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bisai.sinyu.mykongjian.MySeekbar;
import com.bisai.sinyu.tools.ShakeHelper;
import com.bisai.sinyu.tools.ShakeHelper1;

public class ParkingInfoActivity extends AppCompatActivity {
    private TextView txt_value;
    private MySeekbar seekBar;
    private int Total=100;
    private int keyong=90;
    private ShakeHelper1 shakeHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);
        seekBar=(MySeekbar) findViewById(R.id.seekbar0);
        txt_value=(TextView) findViewById(R.id.txt_value);
        txt_value.setText(keyong+"/"+Total);
        seekBar.setMax(Total);
        seekBar.setProgress(keyong);
        shakeHelper=new ShakeHelper1(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        shakeHelper.Start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shakeHelper.Stop();
    }
}
