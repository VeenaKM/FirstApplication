package com.sampleproject.service;

import android.app.Notification;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


public class JobSchedulerService extends JobService {

    JobParameters params;
    DoItTask doIt;

    @Override
    public boolean onStartJob(final JobParameters params) {
        this.params = params;

        // start background long task here
        doIt = new DoItTask()
        {
            @Override
            protected void onPostExecute(String s) {
                Log.d("DoItTask", "Clean up the task here and call jobFinished...");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                jobFinished(params, false);  // call job finish one background task is done.
                super.onPostExecute(s);
            }
        };
        doIt.execute();
        return true;   // return false if not background task
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        if (doIt != null)
            doIt.cancel(true);
        return false;  // to reschedule job return true
    }

    private class DoItTask extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {
            Log.d("DoItTask", "Working here...");
            return "Background Long Running Task Finishes.. ";
        }
    }

}
