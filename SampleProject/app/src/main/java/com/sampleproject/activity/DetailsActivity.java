package com.sampleproject.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sampleproject.R;
import com.sampleproject.adapter.ProductDetailsAdapter;
import com.sampleproject.dataModel.DataModel;

import java.util.ArrayList;

/**
 * Created by apple on 17/04/18.
 */

public class DetailsActivity extends Activity {

    TextView txtTitle;
    RecyclerView mRecyclerView;
    FloatingActionButton mFloatingActionButton;
    RecyclerView.LayoutManager layoutManager;
    String jsonItemObj;
    DataModel dataModelObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
          // fetch data from intent deserialize class object
        jsonItemObj = getIntent().getStringExtra("itemObj");
        dataModelObj = new Gson().fromJson(jsonItemObj, DataModel.class);

        //initialize views
        initViews();
        showData(); // display fetched data

    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        txtTitle = (TextView)findViewById(R.id.title);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    private void showData() {

        txtTitle.setText(dataModelObj.getTitle());
        ProductDetailsAdapter adapter = new ProductDetailsAdapter(dataModelObj.getProduct(),this);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
