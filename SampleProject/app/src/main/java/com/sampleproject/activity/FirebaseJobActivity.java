package com.sampleproject.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import com.sampleproject.R;
import com.sampleproject.service.FirebaseJobDispatcherService;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.sampleproject.service.FirebaseJobDispatcherService.mBitmap;

public class FirebaseJobActivity extends Activity {


    private static String REMINDER_JOB_TAG = "reminder";

    FirebaseJobDispatcher firebaseJobDispatcher;
    Job myJob;

    @BindView(R.id.image)
    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobschedular);
        ButterKnife.bind(this);

        Driver driver = new GooglePlayDriver(this);
        firebaseJobDispatcher = new FirebaseJobDispatcher(driver);

        myJob = firebaseJobDispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(FirebaseJobDispatcherService.class)
                // uniquely identifies the job
                .setTag(REMINDER_JOB_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                // don't persist past a device reboot
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                // start between 0 and 60 seconds from now
                .setTrigger(Trigger.executionWindow(60, 120))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                .build();

    }


    public void scheduleJob(View view) {
        firebaseJobDispatcher.schedule(myJob);
        Toast.makeText(getApplicationContext(), "Job Scheduled..", Toast.LENGTH_LONG).show();
    }

    public void cancelJob(View view) {
        firebaseJobDispatcher.cancel(REMINDER_JOB_TAG);
        Toast.makeText(getApplicationContext(), "Job Cancelled..", Toast.LENGTH_LONG).show();
    }

    private BroadcastReceiver bReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            //put here whaterver you want your activity to do with the intent received
            // Here you have the received broadcast
            // And if you added extras to the intent get them here too
            // this needs some null checks


            if (mBitmap!=null)
                iv_image.setImageBitmap(mBitmap);
           else
               Toast.makeText(FirebaseJobActivity.this,"Failed to load",Toast.LENGTH_LONG).show();

            Log.e("image download finished","");
        }
    };

    protected void onResume(){
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(bReceiver, new IntentFilter("message"));
    }

    protected void onPause (){
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bReceiver);
    }
}
