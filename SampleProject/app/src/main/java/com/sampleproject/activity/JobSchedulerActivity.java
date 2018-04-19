package com.sampleproject.activity;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobschedular);

        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        JobInfo.Builder builder = new JobInfo.Builder(1,
                new ComponentName(getPackageName(),JobSchedulerService.class.getName()));
//        builder.setPeriodic(5000);

//        builder.setMinimumLatency(1 * 1000); // wait at least
//        builder.setOverrideDeadline(3 * 1000); // maximum delay
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // require  network
//        builder.setRequiresDeviceIdle(true); // device should be idle
        builder.setRequiresCharging(true); // we don't care if the device is charging or not

        jobInfo = builder.build();


    }

    public void scheduleJob(View view) {

        jobScheduler.schedule(jobInfo);

        Toast.makeText(getApplicationContext(),"Job Scheduled..",Toast.LENGTH_LONG).show();
    }

    public void cancelJob(View view) {

        jobScheduler.cancel(JOB_ID);
        Toast.makeText(getApplicationContext(),"Job Cancelled..",Toast.LENGTH_LONG).show();
    }
}
