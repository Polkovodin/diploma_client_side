package com.gsu.photolauncher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;

/**
 * Created by user on 11.05.2014.
 */
public class SendHttpRequestTask extends AsyncTask<String, Void, String> {
    @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String urlPhoto = params[1];
            String photoName = params[2];

            Bitmap b = BitmapFactory.decodeFile(urlPhoto);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 90, baos);

            try {
                HttpClient client = new HttpClient(url);
                client.connectForMultipart();
                client.addFormPart("param1", "urlPhoto");
                client.addFormPart("param2", "photoName");
                client.addFilePart("file", photoName, baos.toByteArray());
                client.finishMultipart();
                String data = client.getResponse();
            }
            catch(Throwable t) {
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String data) {

        }
}
