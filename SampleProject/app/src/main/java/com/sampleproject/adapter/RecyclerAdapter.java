package com.sampleproject.adapter;

/**
 * Created by apple on 17/04/18.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sampleproject.R;
import com.sampleproject.dataModel.DataModel;
import com.sampleproject.dataModel.ProductDataModel;
import com.sampleproject.fragment.DashboardFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    ArrayList<DataModel> mainList = new ArrayList<>();
    DashboardFragment dashboardFragment;

    private RecyclerView.ViewHolder holder;
    Context context;


    public RecyclerAdapter(ArrayList<DataModel> mainList, DashboardFragment dashboardFragment, Context context) {
        this.mainList = mainList;
        this.dashboardFragment = dashboardFragment;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.number)
        TextView tv_number;
        @BindView(R.id.title)
        TextView tv_title;
        @BindView(R.id.productName)
        TextView tv_productName;
        @BindView(R.id.imageViewIcon)
        ImageView iv_tickIcon;
        @BindView(R.id.productDetail)
        TextView tv_productDetail;
        @BindView(R.id.wirelessLayout)
        LinearLayout wirelessLayout;
        @BindView(R.id.viewLine1)
        View viewLine1;
        @BindView(R.id.smartPhoneName)
        TextView tv_smartPhoneName;
        @BindView(R.id.smartPhoneDetail)
        TextView tv_smartPhoneDetail;
        @BindView(R.id.smartPhoneLayout)
        LinearLayout smartPhoneLayout;
        @BindView(R.id.viewLine2)
        View viewLine2;
        @BindView(R.id.mdmProductName)
        TextView tv_mdmProductName;
        @BindView(R.id.mdmDetails)
        TextView tv_mdmDetails;
        @BindView(R.id.mdmLayout)
        LinearLayout mdmLayout;
        @BindView(R.id.viewLine3)
        View viewLine3;
        @BindView(R.id.boydLayout)
        LinearLayout boydLayout;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item_layout, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        DataModel dataModel = mainList.get(position);
        holder.tv_title.setText(dataModel.getTitle());
        holder.tv_number.setText(dataModel.getNumber() + "/" + mainList.size());

        displayProductDeails(holder, dataModel, position);

    }

    private void displayProductDeails(MyViewHolder holder, DataModel dataModel, int position) {
        ArrayList<ProductDataModel> subList = dataModel.getProduct();
        Log.e(" product=", "" + dataModel.getProduct());


        for (int i = 0; i < subList.size(); i++) {
            ProductDataModel productModel = subList.get(i);

            // show views depending on data
            switch (dataModel.getNumber()) {

                case 1:
                    holder.wirelessLayout.setVisibility(View.VISIBLE);
                    holder.viewLine1.setVisibility(View.VISIBLE);
                    holder.smartPhoneLayout.setVisibility(View.VISIBLE);
                    holder.viewLine2.setVisibility(View.VISIBLE);
                    holder.mdmLayout.setVisibility(View.VISIBLE);
                    holder.boydLayout.setVisibility(View.GONE);
                    break;
                case 2:
                    holder.wirelessLayout.setVisibility(View.GONE);
                    holder.viewLine1.setVisibility(View.GONE);
                    holder.smartPhoneLayout.setVisibility(View.VISIBLE);
                    holder.viewLine2.setVisibility(View.VISIBLE);
                    holder.mdmLayout.setVisibility(View.VISIBLE);
                    holder.boydLayout.setVisibility(View.GONE);
                    break;
                case 3:
                    holder.wirelessLayout.setVisibility(View.GONE);
                    holder.viewLine1.setVisibility(View.GONE);
                    holder.smartPhoneLayout.setVisibility(View.VISIBLE);
                    holder.viewLine2.setVisibility(View.VISIBLE);
                    holder.mdmLayout.setVisibility(View.VISIBLE);
                    holder.viewLine3.setVisibility(View.VISIBLE);
                    holder.boydLayout.setVisibility(View.VISIBLE);
                    break;
                default:
                    holder.mdmLayout.setVisibility(View.GONE);
                    holder.iv_tickIcon.setVisibility(View.GONE);
                    holder.boydLayout.setVisibility(View.VISIBLE);
                    break;
//
            }

            // show data on textviews
            switch (productModel.getProductType()) {

                case "WireLess":
                    holder.tv_productName.setText(productModel.getProductName());
                    holder.tv_productDetail.setText(productModel.getDetails());

                    break;
                case "MDM":
                    holder.tv_mdmProductName.setText(productModel.getProductName());
                    holder.tv_mdmDetails.setText(productModel.getDetails());

                    break;
                case "SmartPhone":
                    holder.tv_smartPhoneName.setText(productModel.getProductName());
                    holder.tv_smartPhoneDetail.setText(productModel.getDetails());

                    break;
            }

        }


    }


    @Override
    public int getItemCount() {
        return mainList.size();
    }
}
