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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sampleproject.R;
import com.sampleproject.dataModel.ProductDataModel;

import java.util.ArrayList;



public class ProductDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    ArrayList<ProductDataModel> subList = new ArrayList<>();
    Context context;


    public ProductDetailsAdapter(ArrayList<ProductDataModel> subList, Context context) {
        this.subList = subList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView card_view;
        TextView txtProductType;
        TextView txtviewProductName,txtViewDetail1,txtViewDetail2,txtReviewPolicy;
        ImageView imageView_product,image_air;
        Button btnView,btnSupport,btnCost;
        LinearLayout detailsLayout;
        RelativeLayout relativeLayout,boydLayout,wirelessLayout;
        TextView txtMdmProductName,txtMdmDetails;


        public MyViewHolder(View view) {
            super(view);
            txtviewProductName = (TextView) view.findViewById(R.id.productName);
            txtViewDetail1 = (TextView) view.findViewById(R.id.detail1);
            imageView_product = (ImageView) view.findViewById(R.id.productImage);
            txtProductType = (TextView) view.findViewById(R.id.productType);
            txtViewDetail2 = (TextView) view.findViewById(R.id.detail2);
            btnCost = (Button) view.findViewById(R.id.btn_cost);
            btnSupport = (Button) view.findViewById(R.id.btn_support);
            btnView = (Button) view.findViewById(R.id.btn_view);
            relativeLayout= (RelativeLayout) view.findViewById(R.id.layout);
            txtMdmProductName = (TextView) view.findViewById(R.id.txtMdmProductName);
            txtMdmDetails = (TextView) view.findViewById(R.id.txtMdmDetails);
            detailsLayout = (LinearLayout) view.findViewById(R.id.detailsLayout);
            image_air = (ImageView) view.findViewById(R.id.ic_air);
            card_view = (CardView) view.findViewById(R.id.card_view);
            txtReviewPolicy = (TextView) view.findViewById(R.id.reviewPolicy);
            boydLayout=(RelativeLayout) view.findViewById(R.id.boydLayout);
            wirelessLayout = (RelativeLayout) view.findViewById(R.id.wirelessLayout);
        }
    }

    public class DefaultViewHolder extends RecyclerView.ViewHolder {

        TextView txtviewProductName,txtViewDetail1,txtReviewPolicy;
        ImageView imageView_product;
        RelativeLayout boydLayout;

        public DefaultViewHolder(View view) {
            super(view);
            txtviewProductName = (TextView) view.findViewById(R.id.productName);
            txtViewDetail1 = (TextView) view.findViewById(R.id.detail1);
            imageView_product = (ImageView) view.findViewById(R.id.productImage);
            txtReviewPolicy = (TextView) view.findViewById(R.id.reviewPolicy);
            boydLayout=(RelativeLayout) view.findViewById(R.id.boydLayout);

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.productdetails_item_layout, parent, false);
            return new MyViewHolder(itemView);
        }else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.boyd_item_layout, parent, false);
            return new DefaultViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder1, final int position) {
        ProductDataModel productDataModel = subList.get(position);



        if (holder1 instanceof MyViewHolder) {

            MyViewHolder holder = (MyViewHolder) holder1;
            holder.txtProductType.setText(productDataModel.getProductType());
            holder.txtviewProductName.setText(productDataModel.getProductName());

            switch (productDataModel.getProductType()) {
                case "WireLess":
                    holder.imageView_product.setImageResource(R.drawable.ic_wifi);
                    holder.txtViewDetail1.setText("Phone number:+1(463)628-489");
                    holder.txtViewDetail2.setText("Model:iPhone 7 plus");
                    holder.btnCost.setVisibility(View.VISIBLE);
                    holder.txtMdmProductName.setVisibility(View.GONE);
                    holder.txtMdmDetails.setVisibility(View.GONE);
                    holder.image_air.setVisibility(View.GONE);

                    break;
                case "MDM":

                    holder.txtviewProductName.setVisibility(View.GONE);
                    holder.relativeLayout.setVisibility(View.VISIBLE);
                    holder.txtMdmProductName.setText(productDataModel.getProductName());
                    holder.txtMdmDetails.setText(productDataModel.getDetails());
                    holder.imageView_product.setImageResource(R.drawable.ic_phone_mdm);
                    holder.txtViewDetail1.setText("IMEI:6738389939");
                    holder.txtViewDetail2.setText("Serial number:GHS3883H93");
                    holder.btnCost.setVisibility(View.GONE);
                    break;
                case "SmartPhone":
                    holder.imageView_product.setImageResource(R.drawable.ic_phone);
                    holder.txtviewProductName.setText(productDataModel.getDetails());
                    holder.txtViewDetail1.setText("IMEI:6738389939");
                    holder.txtViewDetail2.setText("Serial number:GHS3883H93");
                    holder.btnCost.setVisibility(View.GONE);
                    holder.txtMdmProductName.setVisibility(View.GONE);
                    holder.txtMdmDetails.setVisibility(View.GONE);
                    holder.image_air.setVisibility(View.GONE);
                    break;
            }
        }else {
            DefaultViewHolder holder = (DefaultViewHolder) holder1;
            holder.txtviewProductName.setText("BOYD");

            holder.txtReviewPolicy.setPaintFlags(holder.txtReviewPolicy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.txtReviewPolicy.setTextColor(context.getResources().getColor(R.color.blue));
        }


    }

    @Override
    public int getItemViewType(int position) {
        switch (subList.get(position).getProductType())
        {
            case "WireLess":
            case "SmartPhone":
            case "MDM":
                return 1;
            default:
                   return 2;

        }
    }

    @Override
    public int getItemCount()
    {
        return subList.size();
    }
}

