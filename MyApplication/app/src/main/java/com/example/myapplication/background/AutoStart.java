package com.example.myapplication.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoStart extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction() != null){

            context.startService(new Intent(context, MyService.class));
        }
    }
}