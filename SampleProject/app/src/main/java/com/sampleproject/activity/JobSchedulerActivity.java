package com.sampleproject.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.sampleproject.R;
import com.sampleproject.service.JobSchedulerService;

public class JobSchedulerActivity extends Activity {

    public  static final int JOB_ID = 1;


    JobScheduler jobScheduler;
    JobInfo jobInfo;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobschedular);

        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        JobInfo.Builder builder = new JobInfo.Builder(1,
                new ComponentName(getPackageName(), JobSchedulerService.class.getName()));

//        builder.setMinimumLatency(1 * 1000); // wait at least
//        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // require  network
//        builder.setRequiresDeviceIdle(true); // device should be idle
        builder.setRequiresCharging(false); // we don't care if the device is charging or not
//         builder.setOverrideDeadline(50000); //the job will be executed anyway after 50 sec
        builder.setPersisted(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMinimumLatency(5000);
            builder.setPeriodic(15 * 60 * 1000, 5 * 60 *1000);  //15minutes interval 5 mins flex
        } else {
            builder.setPeriodic(5000);
        }


        jobInfo = builder.build();

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob(View view) {
          jobScheduler.schedule(jobInfo);
            Toast.makeText(getApplicationContext(),"Job Scheduled..",Toast.LENGTH_LONG).show();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void cancelJob(View view) {
            jobScheduler.cancel(JOB_ID);

        Toast.makeText(getApplicationContext(),"Job Cancelled..",Toast.LENGTH_LONG).show();
    }

}



