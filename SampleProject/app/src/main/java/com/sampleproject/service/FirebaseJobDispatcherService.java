package com.sampleproject.service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.sampleproject.asyncTask.DoItTask;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

import static com.sampleproject.Connectivity.isConnected;

public class FirebaseJobDispatcherService extends JobService {
    DoItTask doIt;
    public static Bitmap mBitmap;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        Log.d("Start", "Background task...");

        // start background long task here run it on different thread

        mBitmap=null;


        startDownloadingImage();


//        doIt = new DoItTask()
//        {
//            @Override
//            protected void onPostExecute(Bitmap s) {
//                Log.d("DoItTask", "Clean up the task here and call jobFinished..."+System.currentTimeMillis()/1000);
//                Toast.makeText(getApplicationContext(),"downloaded image",Toast.LENGTH_LONG).show();
//                jobFinished(jobParameters, false);
//                sendBroadcast(s, "my_image.png");
//                super.onPostExecute(s);
//            }
//        };
//        doIt.execute("https://www.google.co.in/search?q=use+glide+in+service&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjEw5XPzcjaAhWHK48KHZoIDekQ_AUICygC&biw=1275&bih=655#imgrc=7lsHi2ukQrjBzM:");
        return true;
    }

    private void startDownloadingImage() {
        if (isConnected(getApplicationContext())) {
            Glide.with(getApplicationContext())
                    .load("http://fullhdwall.com/wp-content/uploads/2017/08/Animated-3D-Android.png")
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
    public boolean onStopJob(JobParameters job) {
        if (doIt != null) {
            doIt.cancel(true);
        }
        Log.i("TAG", "onStopJob");
        /* true means, we're not done, please reschedule */
        return true;
    }

    private void sendBroadcast (){
        //Convert to byte array

        Intent intent = new Intent("message"); //put the same message as in the filter you used in the activity when registering the receiver
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
