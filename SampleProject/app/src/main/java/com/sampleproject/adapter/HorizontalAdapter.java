//package com.sampleproject.adapter;
//
///**
// * Created by apple on 17/04/18.
// */
//
//import android.content.Context;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.sampleproject.R;
//import com.sampleproject.dataModel.ProductDataModel;
//
//import java.util.ArrayList;
//
//
//public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {
//
//
//    ArrayList<ProductDataModel> subList = new ArrayList<>();
//    Context context;
//    private RecyclerView.ViewHolder holder;
//
//
//    public HorizontalAdapter(ArrayList<ProductDataModel> subList, Context context) {
//        this.subList = subList;
//        this.context = context;
//    }
//
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//
//        LinearLayout subLayout,mdmLayout,mainLayout;
//        TextView txtviewProductName,txtViewDetail,txtMdmProductName,txtMdmDetails;
//        ImageView imageViewIcon;
//        View viewLine;
//
//
//        public MyViewHolder(View view) {
//            super(view);
//            txtviewProductName = (TextView) view.findViewById(R.id.productName);
//            txtViewDetail = (TextView) view.findViewById(R.id.productDetail);
//            viewLine = (View) view.findViewById(R.id.viewLine);
//            imageViewIcon = (ImageView) view.findViewById(R.id.imageViewIcon);
//            mainLayout = (LinearLayout) view.findViewById(R.id.layou1);
//            subLayout = (LinearLayout) view.findViewById(R.id.subLayout);
//            mdmLayout= (LinearLayout) view.findViewById(R.id.mdmLayout);
//            txtMdmProductName = (TextView) view.findViewById(R.id.txtMdmProductName);
//            txtMdmDetails = (TextView) view.findViewById(R.id.txtMdmDetails);
//
//        }
//    }
//
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
//
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//
//        ProductDataModel dataModel = subList.get(position);
//
//        holder.txtviewProductName.setText(dataModel.getProductName());
//        holder.txtViewDetail.setText(dataModel.getDetails());
//
//        if (position==(subList.size()-1))
//        {
//            holder.viewLine.setVisibility(View.GONE);
//
//        }
//
//        switch (dataModel.getProductType()) {
//            case "WireLess":
//                holder.mainLayout.setVisibility(View.VISIBLE);
//                holder.mdmLayout.setVisibility(View.GONE);
//                holder.imageViewIcon.setVisibility(View.VISIBLE);
//                holder.subLayout.setVisibility(View.GONE);
//                break;
//            case "MDM":
//                holder.mainLayout.setVisibility(View.GONE);
//                holder.mdmLayout.setVisibility(View.VISIBLE);
//                holder.txtMdmProductName.setText(dataModel.getProductName());
//                holder.txtMdmDetails.setText(dataModel.getDetails());
//                holder.subLayout.setVisibility(View.GONE);
//
//                break;
//            case "SmartPhone":
//                holder.mainLayout.setVisibility(View.VISIBLE);
//                holder.mdmLayout.setVisibility(View.GONE);
//                holder.imageViewIcon.setVisibility(View.GONE);
//                holder.subLayout.setVisibility(View.GONE);
//                break;
//            default:
//                holder.imageViewIcon.setVisibility(View.GONE);
//                holder.mdmLayout.setVisibility(View.GONE);
//                holder.txtViewDetail.setVisibility(View.GONE);
//                holder.subLayout.setVisibility(View.VISIBLE);
//                break;
//
//        }
//
//
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        return subList.size();
//    }
//}
