package com.bisai.sinyu.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootConpleteReceiver extends BroadcastReceiver {
    public BootConpleteReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"开机",Toast.LENGTH_LONG).show();
       // throw new UnsupportedOperationException("Not yet implemented");
        //开机启动

    }
}
