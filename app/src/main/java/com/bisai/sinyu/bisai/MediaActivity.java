package com.bisai.sinyu.bisai;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MediaActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_play,btn_pause,btn_stop;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        btn_play= (Button) findViewById(R.id.btn_play);
        btn_pause= (Button) findViewById(R.id.btn_pause);
        btn_stop= (Button) findViewById(R.id.btn_stop);
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

            initMediaPlayer();
            Log.d("media", "onCreate: 1");



    }

    private void initMediaPlayer() {
        try{
            mediaPlayer=MediaPlayer.create(this,R.raw.music);
           // File file=new File(Environment.getExternalStorageDirectory(),R.raw.music);

           // mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
            Log.d("media", "onCreate: 2");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_play:
                Log.d("media", "onCreate: 3");
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
                break;
            case R.id.btn_pause:
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            case R.id.btn_stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;

        }
    }
}
