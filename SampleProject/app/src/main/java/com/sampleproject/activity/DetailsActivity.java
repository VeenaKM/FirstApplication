package com.sampleproject.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        showData(); // display fetched data

    }

    private void initViews() {
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
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
