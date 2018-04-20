package com.sampleproject.service;

import android.app.Notification;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.sampleproject.asyncTask.DoItTask;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {

    JobParameters params;
    DoItTask doIt;

    @Override
    public boolean onStartJob(final JobParameters params) {
        this.params = params;
        Log.d("Start", "Background task...");

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

    @Override
    public boolean onStopJob(JobParameters params) {

        if (doIt != null)
            doIt.cancel(true);
        return false;  // to reschedule job return true
    }



}
