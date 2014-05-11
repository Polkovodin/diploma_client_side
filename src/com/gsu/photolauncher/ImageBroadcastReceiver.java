package com.gsu.photolauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by user on 11.05.2014.
 */
public class ImageBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, ServiceToUploadImage.class);
        context.startService(service);
    }
}