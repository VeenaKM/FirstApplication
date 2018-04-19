package com.sampleproject.adapter;

/**
 * Created by apple on 17/04/18.
 */

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sampleproject.R;
import com.sampleproject.dataModel.ProductDataModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    ArrayList<ProductDataModel> subList = new ArrayList<>();
    Context context;


    public ProductDetailsAdapter(ArrayList<ProductDataModel> subList, Context context) {
        this.subList = subList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productImage)
        ImageView iv_productImage;
        @BindView(R.id.productType)
        TextView tv_productType;
        @BindView(R.id.productName)
        TextView tv_productName;
        @BindView(R.id.ic_air)
        ImageView iv_airImage;
        @BindView(R.id.mdmProductName)
        TextView tv_mdmProductName;
        @BindView(R.id.mdmDetails)
        TextView tv_mdmDetails;
        @BindView(R.id.layout)
        RelativeLayout relativelayout;
        @BindView(R.id.detail1)
        TextView tv_details1;
        @BindView(R.id.detail2)
        TextView tv_detail2;
        @BindView(R.id.btn_view)
        Button btnView;
        @BindView(R.id.btn_support)
        Button btnSupport;
        @BindView(R.id.btn_cost)
        Button btnCost;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.productImage)
        ImageView iv_productImage;
        @BindView(R.id.productName)
        TextView tv_productName;
        @BindView(R.id.reviewPolicy)
        TextView tv_reviewPolicy;
        @BindView(R.id.layout)
        RelativeLayout layout;
        @BindView(R.id.cb3)
        CheckBox chk3;
        @BindView(R.id.snapPicture)
        Button btnSnapPicture;
        @BindView(R.id.detailsLayout)
        LinearLayout detailsLayout;
        @BindView(R.id.boydLayout)
        RelativeLayout boydLayout;
        @BindView(R.id.card_view)
        CardView cardView;

        DefaultViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.productdetails_item_layout, parent, false);
            return new MyViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.boyd_item_layout, parent, false);
            return new DefaultViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder1, final int position) {
        ProductDataModel productDataModel = subList.get(position);


        if (holder1 instanceof MyViewHolder) {

            MyViewHolder holder = (MyViewHolder) holder1;
            holder.tv_productType.setText(productDataModel.getProductType());
            holder.tv_productName.setText(productDataModel.getProductName());

            switch (productDataModel.getProductType()) {
                case "WireLess":
                    holder.iv_productImage.setImageResource(R.drawable.ic_wifi);
                    holder.tv_details1.setText("Phone number:+1(463)628-489");
                    holder.tv_detail2.setText("Model:iPhone 7 plus");
                    holder.btnCost.setVisibility(View.VISIBLE);
                    holder.tv_mdmProductName.setVisibility(View.GONE);
                    holder.tv_mdmDetails.setVisibility(View.GONE);
                    holder.iv_airImage.setVisibility(View.GONE);
                    break;

                case "MDM":
                    holder.tv_productName.setVisibility(View.GONE);
                    holder.relativelayout.setVisibility(View.VISIBLE);
                    holder.tv_mdmProductName.setText(productDataModel.getProductName());
                    holder.tv_mdmDetails.setText(productDataModel.getDetails());
                    holder.iv_productImage.setImageResource(R.drawable.ic_phone_mdm);
                    holder.tv_details1.setText("IMEI:6738389939");
                    holder.tv_detail2.setText("Serial number:GHS3883H93");
                    holder.btnCost.setVisibility(View.GONE);
                    break;

                case "SmartPhone":
                    holder.iv_productImage.setImageResource(R.drawable.ic_phone);
                    holder.tv_productName.setText(productDataModel.getDetails());
                    holder.tv_details1.setText("IMEI:6738389939");
                    holder.tv_detail2.setText("Serial number:GHS3883H93");
                    holder.btnCost.setVisibility(View.GONE);
                    holder.tv_mdmProductName.setVisibility(View.GONE);
                    holder.tv_mdmDetails.setVisibility(View.GONE);
                    holder.iv_airImage.setVisibility(View.GONE);
                    break;
            }
        } else {

            DefaultViewHolder holder = (DefaultViewHolder) holder1;
            holder.tv_productName.setText("BOYD");
            holder.tv_reviewPolicy.setPaintFlags(holder.tv_reviewPolicy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.tv_reviewPolicy.setTextColor(context.getResources().getColor(R.color.blue));
        }


    }

    @Override
    public int getItemViewType(int position) {
        switch (subList.get(position).getProductType()) {
            case "WireLess":
            case "SmartPhone":
            case "MDM":
                return 1;
            default:
                return 2;

        }
    }

    @Override
    public int getItemCount() {
        return subList.size();
    }


}

