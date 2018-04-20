package com.sampleproject.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sampleproject.R;
import com.sampleproject.adapter.ProductDetailsAdapter;
import com.sampleproject.dataModel.DataModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by apple on 17/04/18.
 */

public class DetailsActivity extends Activity {


    RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    String jsonItemObj;
    DataModel dataModelObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        // fetch data from intent deserialize class object
        jsonItemObj = getIntent().getStringExtra("itemObj");
        dataModelObj = new Gson().fromJson(jsonItemObj, DataModel.class);

        //initialize views
        initViews();

        initListeners();

        showData(); // display fetched data

    }

    private void initViews() {
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initListeners() {
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(DetailsActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    gotoJobSchedulerActivity();

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void gotoJobSchedulerActivity() {
        Intent intent = new Intent(DetailsActivity.this, FirebaseJobActivity.class);
        startActivity(intent);
    }



    private void showData() {

        tvTitle.setText(dataModelObj.getTitle());
        ProductDetailsAdapter adapter = new ProductDetailsAdapter(dataModelObj.getProduct(), this);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
