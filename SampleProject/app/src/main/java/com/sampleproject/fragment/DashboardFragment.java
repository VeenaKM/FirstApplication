package com.sampleproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sampleproject.R;
import com.sampleproject.activity.DetailsActivity;
import com.sampleproject.adapter.RecyclerAdapter;
import com.sampleproject.dataModel.DataModel;
import com.sampleproject.dataModel.ProductDataModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by apple on 17/04/18.
 */

public class DashboardFragment extends Fragment {

    View dashboardView;
    RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    Unbinder unbinder;

    ArrayList<DataModel> mainList = new ArrayList<>();

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        // Inflate the layout for this fragment
        dashboardView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        unbinder = ButterKnife.bind(this, dashboardView);

        initViews();

        initListeners();

        showData();

        return dashboardView;
    }
    private void initViews() {
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initListeners() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && floatingActionButton.getVisibility() == View.VISIBLE) {
                    floatingActionButton.hide();
                } else if (dy < 0 && floatingActionButton.getVisibility() != View.VISIBLE) {
                    floatingActionButton.show();
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

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

                    DataModel dataModel = mainList.get(position);
                    gotToDetailsFragment(dataModel);

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


    private void showData() {

        mainList = new ArrayList<>();
        ArrayList<ProductDataModel> productList = new ArrayList<>();

        ProductDataModel productModel = new ProductDataModel();
        productModel.setProductName("verizon");
        productModel.setDetails("+1(218)485-749");
        productModel.setProductType("WireLess");
        productList.add(productModel);

        productModel = new ProductDataModel();
        productModel.setProductName("Apple ");
        productModel.setDetails("IPhone 7 Plus");
        productModel.setProductType("SmartPhone");
        productList.add(productModel);

        productModel = new ProductDataModel();
        productModel.setProductName("airwatch");
        productModel.setDetails("by vmWare");
        productModel.setProductType("MDM");
        productList.add(productModel);


        DataModel dataModel = new DataModel();
        dataModel.setTitle("Corporate Wireless Service");
        dataModel.setNumber(1);
        dataModel.setProduct(productList);
        mainList.add(dataModel);

        productList = new ArrayList<>();
        productModel = new ProductDataModel();
        productModel.setProductName("Apple ");
        productModel.setDetails("IPad pro");
        productModel.setProductType("SmartPhone");
        productList.add(productModel);
        productModel = new ProductDataModel();
        productModel.setProductName("airwatch");
        productModel.setDetails("by vmWare");
        productModel.setProductType("MDM");
        productList.add(productModel);
        dataModel = new DataModel();
        dataModel.setTitle("Corporate Wifi Service");
        dataModel.setNumber(2);
        dataModel.setProduct(productList);
        mainList.add(dataModel);

        productList = new ArrayList<>();
        productModel = new ProductDataModel();
        productModel.setProductName("Apple ");
        productModel.setDetails("IPad pro");
        productModel.setProductType("SmartPhone");
        productList.add(productModel);

        productModel = new ProductDataModel();
        productModel.setProductName("airwatch");
        productModel.setDetails("by vmWare");
        productModel.setProductType("MDM");
        productList.add(productModel);

        productModel = new ProductDataModel();
        productModel.setProductName("Stiphend");
        productModel.setDetails("");
        productModel.setProductType("");
        productList.add(productModel);

        dataModel = new DataModel();
        dataModel.setTitle("BYOD");
        dataModel.setNumber(3);
        dataModel.setProduct(productList);
        mainList.add(dataModel);
        RecyclerAdapter adapter = new RecyclerAdapter(mainList, DashboardFragment.this, getActivity());
        mRecyclerView.setAdapter(adapter);

    }

    public void gotToDetailsFragment(DataModel dataModel) {
        String jsonString = new Gson().toJson(dataModel);

        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("itemObj", jsonString);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
