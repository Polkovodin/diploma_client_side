package com.gsu.photolauncher;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;

public class ServiceToUploadImage extends Service {
    public String url = "http://192.168.1.2:8080/diploma_server_side/UploadServlet";
    public String urlPhoto;
    public String photoName;
    public Uri uri;

    @Override
    public void onCreate() {
        super.onCreate();
        GetImage();
    }

    private void GetImage()
    {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.ImageColumns.ORIENTATION}, MediaStore.Images.Media.DATE_ADDED, null, "date_added ASC");
        if(cursor != null && cursor.moveToFirst())
        {
            do {
                uri = Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                urlPhoto = uri.toString();
            }while(cursor.moveToNext());
            cursor.close();
        }

        int lastSep = urlPhoto.lastIndexOf("/");
        photoName = urlPhoto.substring(lastSep, urlPhoto.length());

        SendHttpRequestTask t = new SendHttpRequestTask();
        String[] params = new String[]{url, urlPhoto, photoName };
        t.execute(params);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}