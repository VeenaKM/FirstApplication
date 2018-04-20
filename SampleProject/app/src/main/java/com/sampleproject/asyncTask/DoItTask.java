package com.sampleproject.asyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

//public class DoItTask extends AsyncTask<Void, Void, String> {
//
//
//    @Override
//    protected String doInBackground(Void... params) {
//        Log.d("DoItTask", "Working here...");
//        return "Background Long Running Task Finishes.. ";
//    }
//}

public class DoItTask extends AsyncTask<String, Void, Bitmap> {
    private String TAG = "DownloadImage";
    private Bitmap downloadImageBitmap(String sUrl) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
            bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
            inputStream.close();
        } catch (Exception e) {
            Log.d(TAG, "Exception 1, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return downloadImageBitmap(params[0]);
    }

}