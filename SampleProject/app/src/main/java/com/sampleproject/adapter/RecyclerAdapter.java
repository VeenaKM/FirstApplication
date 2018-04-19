package com.sampleproject.adapter;

/**
 * Created by apple on 17/04/18.
 */

import android.content.Context;
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


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


     ArrayList<DataModel> mainList = new ArrayList<>();
    DashboardFragment dashboardFragment;
    private RecyclerView.ViewHolder holder;
    Context context;


    public RecyclerAdapter(ArrayList<DataModel> mainList, DashboardFragment dashboardFragment,Context context) {
        this.mainList = mainList;
        this.dashboardFragment = dashboardFragment;
        this.context=context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        LinearLayout root;
        TextView txtviewTitle,txtViewNumber,txtSmartPhoneName,txtSmartPhoneDetail;
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager layoutManager;
        LinearLayout subLayout,mdmLayout,wirelessLayout;
        TextView txtviewProductName,txtViewDetail,txtMdmProductName,txtMdmDetails;
        ImageView imageViewIcon;
        View viewLine1,viewLine2,viewLine3;
        LinearLayout smartPhoneLayout;

        public MyViewHolder(View view) {
            super(view);
            txtviewTitle = (TextView) view.findViewById(R.id.title);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            txtViewNumber = (TextView) view.findViewById(R.id.number);
            root = (LinearLayout) view.findViewById(R.id.root);

            txtviewProductName = (TextView) view.findViewById(R.id.productName);
            txtViewDetail = (TextView) view.findViewById(R.id.productDetail);
            viewLine1 = (View) view.findViewById(R.id.viewLine1);
            viewLine2 = (View) view.findViewById(R.id.viewLine2);
            viewLine3 = (View) view.findViewById(R.id.viewLine3);
            imageViewIcon = (ImageView) view.findViewById(R.id.imageViewIcon);
            wirelessLayout = (LinearLayout) view.findViewById(R.id.wirelessLayout);
            subLayout = (LinearLayout) view.findViewById(R.id.subLayout);
            smartPhoneLayout = (LinearLayout) view.findViewById(R.id.subLayout);
            mdmLayout= (LinearLayout) view.findViewById(R.id.mdmLayout);
            txtMdmProductName = (TextView) view.findViewById(R.id.txtMdmProductName);
            txtMdmDetails = (TextView) view.findViewById(R.id.txtMdmDetails);

            txtSmartPhoneName= (TextView) view.findViewById(R.id.smartPhoneName);
            txtSmartPhoneDetail= (TextView) view.findViewById(R.id.smartPhoneDetail);


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item_layout, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        DataModel dataModel = mainList.get(position);
        holder.txtviewTitle.setText(dataModel.getTitle());
        holder.txtViewNumber.setText(dataModel.getNumber()+"/"+mainList.size());

        displayProductDeails(holder,dataModel,position);

    }

    private void displayProductDeails(MyViewHolder holder, DataModel dataModel, int position) {
        ArrayList<ProductDataModel> subList=dataModel.getProduct();
        Log.e(" product=",""+dataModel.getProduct());


        for (int i=0;i<subList.size();i++) {
            ProductDataModel productModel = subList.get(i);

            // show views depending on data
            switch (dataModel.getNumber()) {

                case 1:
                    holder.wirelessLayout.setVisibility(View.VISIBLE);
                    holder.viewLine1.setVisibility(View.VISIBLE);
                    holder.smartPhoneLayout.setVisibility(View.VISIBLE);
                    holder.viewLine2.setVisibility(View.VISIBLE);
                    holder.mdmLayout.setVisibility(View.VISIBLE);
                    holder.imageViewIcon.setVisibility(View.VISIBLE);
                    holder.subLayout.setVisibility(View.GONE);
                    break;
                case 2:
                    holder.wirelessLayout.setVisibility(View.GONE);
                    holder.viewLine1.setVisibility(View.GONE);
                    holder.smartPhoneLayout.setVisibility(View.VISIBLE);
                    holder.viewLine2.setVisibility(View.VISIBLE);
                    holder.mdmLayout.setVisibility(View.VISIBLE);
                    holder.imageViewIcon.setVisibility(View.VISIBLE);
                    holder.subLayout.setVisibility(View.GONE);
                    break;
                case 3:
                    holder.wirelessLayout.setVisibility(View.GONE);
                    holder.viewLine1.setVisibility(View.GONE);
                    holder.smartPhoneLayout.setVisibility(View.VISIBLE);
                    holder.viewLine2.setVisibility(View.VISIBLE);
                    holder.mdmLayout.setVisibility(View.VISIBLE);
                    holder.imageViewIcon.setVisibility(View.VISIBLE);
                    holder.viewLine3.setVisibility(View.VISIBLE);
                    holder.subLayout.setVisibility(View.VISIBLE);
                    break;
                default:
                    holder.imageViewIcon.setVisibility(View.GONE);
                    holder.mdmLayout.setVisibility(View.GONE);
                    holder.txtViewDetail.setVisibility(View.GONE);
                    holder.subLayout.setVisibility(View.VISIBLE);
                    break;
//
            }

            // show data on textviews
            switch (productModel.getProductType()) {

                case "WireLess":
                    holder.txtviewProductName.setText(productModel.getProductName());
                    holder.txtViewDetail.setText(productModel.getDetails());

                    break;
                case "MDM":
                    holder.txtMdmProductName.setText(productModel.getProductName());
                    holder.txtMdmDetails.setText(productModel.getDetails());

                    break;
                case "SmartPhone":
                    holder.txtSmartPhoneName.setText(productModel.getProductName());
                    holder.txtSmartPhoneDetail.setText(productModel.getDetails());

                    break;
            }

        }



        }





    @Override
    public int getItemCount()
    {
        return mainList.size();
    }
}
