package com.gsu.photolauncher;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;

@SuppressWarnings("ALL")
public class MainActivity extends Service {

    private static final int NOTIFY_ID = 101;


    public void onCreate() {
        super.onCreate();
        createNotification();
    }

    public void createNotification()  {

        Context context = getApplicationContext();

       // Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0,
                //notificationIntent,
                null,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_launcher)
                        // большая картинка
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher))
                        //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker("Последнее китайское предупреждение!")
                .setWhen(System.currentTimeMillis()) // java.lang.System.currentTimeMillis()
                .setAutoCancel(true)
                        //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle("Напоминание")
                        //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Пора покормить кота"); // Текст уведомленимя

        Notification n = builder.getNotification();
        n.flags |= Notification.FLAG_NO_CLEAR;
        n.flags |= Notification.FLAG_ONGOING_EVENT;

        nm.notify(NOTIFY_ID, n);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
