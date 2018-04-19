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
        builder.setPeriodic(5000);

//        builder.setMinimumLatency(1 * 1000); // wait at least
//        builder.setOverrideDeadline(3 * 1000); // maximum delay
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not

        jobInfo = builder.build();


    }



//    @Override
//    protected void onStop() {
//        // A service can be "started" and/or "bound". In this case, it's "started" by this Activity
//        // and "bound" to the JobScheduler (also called "Scheduled" by the JobScheduler). This call
//        // to stopService() won't prevent scheduled jobs to be processed. However, failing
//        // to call stopService() would keep it alive indefinitely.
//        stopService(new Intent(this, JobSchedulerService.class));
//        super.onStop();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // Start service and provide it a way to communicate with this class.
//        Intent startServiceIntent = new Intent(this, JobSchedulerService.class);
////        Messenger messengerIncoming = new Messenger(mHandler);
////        startServiceIntent.putExtra(MESSENGER_INTENT_KEY, messengerIncoming);
//        startService(startServiceIntent);
//    }

    public void scheduleJob(View view) {

        jobScheduler.schedule(jobInfo);

        Toast.makeText(getApplicationContext(),"Job Scheduled..",Toast.LENGTH_LONG).show();
    }

    public void cancelJob(View view) {

        jobScheduler.cancel(JOB_ID);
        Toast.makeText(getApplicationContext(),"Job Cancelled..",Toast.LENGTH_LONG).show();
    }
}
