package com.example.shetkari.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shetkari.CropActivity.FullScreenDetailInfoAboutCrop;
import com.example.shetkari.Model.RightScrollModel;
import com.example.shetkari.R;

import java.util.ArrayList;

public class RightScrollRecyclerAdapter extends RecyclerView.Adapter<RightScrollRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<RightScrollModel> rightScrollModelArrayList;


    public RightScrollRecyclerAdapter(Context context, ArrayList<RightScrollModel> rightScrollModelArrayList) {
        this.context = context;
        this.rightScrollModelArrayList = rightScrollModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.more_right_scroll, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        RightScrollModel rightScrollModel = rightScrollModelArrayList.get(position);
//        Glide.with(context).load(rightScrollModel.getCropImage()).into(holder.CropImage);
//        holder.CropName.setText(rightScrollModel.getCropName());
//        holder.CropRate.setText(rightScrollModel.getRate());
//        holder.Location.setText(rightScrollModel.getLocation());
//        holder.farmerName.setText(rightScrollModel.getFarmerName());
//        holder.farmerMobileNumber.setText(rightScrollModel.getFarmerMobileNumber());
//        holder.farmerMobileNumberTwo.setText(rightScrollModel.getFarmerMobileNumberTwo());
//        holder.farmerEmail.setText(rightScrollModel.getFarmerEmail());
//        holder.CropKG.setText(rightScrollModel.getCropKG());
//
//        holder.Read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, FullScreenDetailInfoAboutCrop.class);
//                intent.putExtra("CropImage",rightScrollModel.getCropImage());
//                intent.putExtra("CropName",rightScrollModel.getCropName());
//                intent.putExtra("CropRate",rightScrollModel.getRate());
//                intent.putExtra("Location",rightScrollModel.getLocation());
//                intent.putExtra("farmerName",rightScrollModel.getFarmerName());
//                intent.putExtra("farmerMobileNumber",rightScrollModel.getFarmerMobileNumber());
//                intent.putExtra("farmerMobileNumberTwo",rightScrollModel.getFarmerMobileNumberTwo());
//                intent.putExtra("farmerEmail",rightScrollModel.getFarmerEmail());
//                intent.putExtra("CropKG",rightScrollModel.getCropKG());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return rightScrollModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView CropName,CropRate,Location,farmerName,farmerMobileNumber,farmerMobileNumberTwo,farmerEmail, Read,CropKG;
        ImageView CropImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            CropImage = itemView.findViewById(R.id.CropImageRight);
//            CropName = itemView.findViewById(R.id.CropNameRight);
//            CropRate = itemView.findViewById(R.id.CropRateRight);
//            Location = itemView.findViewById(R.id.LocationRight);
//            farmerName = itemView.findViewById(R.id.farmerNameRight);
//            farmerMobileNumber = itemView.findViewById(R.id.farmerMobileNumberRight);
//            farmerMobileNumberTwo = itemView.findViewById(R.id.farmerMobileNumberTwoRight);
//            farmerEmail = itemView.findViewById(R.id.farmerEmailRight);
//            Read = itemView.findViewById(R.id.Read);
//            CropKG = itemView.findViewById(R.id.CropKGRight);
        }
    }
}
