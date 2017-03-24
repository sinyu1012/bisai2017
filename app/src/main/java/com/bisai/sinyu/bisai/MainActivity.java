package com.bisai.sinyu.bisai;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager vp_dh;
    View v1,v2,v3;
    List<View> listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        vp_dh=(ViewPager)findViewById(R.id.vp_dh);

        LayoutInflater li=LayoutInflater.from(this);
        v1=li.inflate(R.layout.viewpager_1,null);
        v2=li.inflate(R.layout.viewpager_2,null);

        listview=new ArrayList<View>();
        listview.add(v1);
        listview.add(v2);

        MPagerAdapter adapter=new MPagerAdapter(listview,this);
        vp_dh.setAdapter(adapter);


    }
}
