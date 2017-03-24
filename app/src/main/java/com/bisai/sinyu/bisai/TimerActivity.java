package com.bisai.sinyu.bisai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener{

    public static TextView tv1,tv2;
    private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        btn_start= (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if (v.getId()==R.id.btn_start)
        {
            Intent intent=new Intent(this,MyService.class);
            if(btn_start.getText().equals("开始停车"))
            {
                btn_start.setText("结束停车");

                startService(intent);
            }else {

                stopService(intent);
                btn_start.setText("开始停车");

            }

        }
    }
}
