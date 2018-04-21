package com.sampleproject.service;

import android.app.Notification;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sampleproject.asyncTask.DoItTask;

import static com.sampleproject.Connectivity.isConnected;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {

    JobParameters params;
    DoItTask doIt;
    public static Bitmap mBitmap;

    @Override
    public boolean onStartJob(final JobParameters params) {
        this.params = params;
        Log.d("Start", "Background task...");
        mBitmap=null;
        startDownloadingImage();
        // start background long task here run it on different thread
//        doIt = new DoItTask()
//        {
//            @Override
//            protected void onPostExecute(String s) {
//                Log.d("DoItTask", "Clean up the task here and call jobFinished...");
//                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
//                jobFinished(params, false);  // call job finish once background task is done.
//                super.onPostExecute(s);
//            }
//        };
//        doIt.execute();
        return true;   // return false if not different thread
    }
    private void startDownloadingImage() {
        if (isConnected(getApplicationContext())) {
            Glide.with(getApplicationContext())
                    .load("https://api.androidhive.info/images/glide/medium/deadpool.jpg")
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(200, 200) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            // Do something with bitmap here.
                            mBitmap = bitmap;
                            Toast.makeText(getApplicationContext(), "downloaded image complete", Toast.LENGTH_LONG).show();
                            Log.d("imageTask", "Clean up the task here and call jobFinished...");

                            sendBroadcast();

                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            super.onLoadFailed(e, errorDrawable);
                            Log.d("DoItTask", "failed..." + e.getMessage());

                            sendBroadcast();

                        }
                    });
        }else {
            Log.d("imageTask", "No internet connection available..");

        }
    }
    @Override
    public boolean onStopJob(JobParameters params) {

        if (doIt != null)
            doIt.cancel(true);
        return false;  // to reschedule job return true
    }

    private void sendBroadcast (){
        //Convert to byte array

        Intent intent = new Intent("message"); //put the same message as in the filter you used in the activity when registering the receiver
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
