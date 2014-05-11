package com.gsu.photolauncher;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * Created by VeronaHamster on 03.05.14.
 */
public class RebootBroadcastReceiver extends BroadcastReceiver {
    private static final int NOTIFY_ID = 101;


    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context);
    }

    public void createNotification( Context context)  {
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0,
                null,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Resources res = context.getResources();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_launcher)
                // текст в строке состояния
                .setTicker("Run PhotoUploader")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                //Заголовок уведомления
                .setContentTitle("Upload photo")
                // Текст уведомленимя
                .setContentText("This application uploads the photo to the server");

        Notification n = builder.build();
        n.flags |= Notification.FLAG_NO_CLEAR;
        n.flags |= Notification.FLAG_ONGOING_EVENT;

        nm.notify(NOTIFY_ID, n);
    }
}
