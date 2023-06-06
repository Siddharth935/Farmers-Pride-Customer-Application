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
import com.example.shetkari.Model.CropInformationModel;
import com.example.shetkari.R;

import java.util.ArrayList;

public class CropInformationAdapter extends RecyclerView.Adapter<CropInformationAdapter.MyViewHolder> {

    Context context;
    ArrayList<CropInformationModel> cropInformationsmodel;

    public CropInformationAdapter(Context context, ArrayList<CropInformationModel> cropInformationsmodel) {
        this.context = context;
        this.cropInformationsmodel = cropInformationsmodel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vegitable_load_layout_templete,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CropInformationModel cropInformationModel = cropInformationsmodel.get(position);
        Glide.with(context).load(cropInformationModel.getCropImage()).into(holder.CropImage);
        holder.CropName.setText(cropInformationModel.getCropName());
        holder.CropRate.setText(cropInformationModel.getRate());
        holder.Location.setText(cropInformationModel.getLocation());
        holder.farmerName.setText(cropInformationModel.getFarmerName());
        holder.farmerMobileNumber.setText(cropInformationModel.getFarmerMobileNumber());
        holder.farmerMobileNumberTwo.setText(cropInformationModel.getFarmerMobileNumberTwo());
        holder.farmerEmail.setText(cropInformationModel.getFarmerEmail());
        holder.CropKG.setText(cropInformationModel.getCropKG());
        holder.clickHereFullViewInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullScreenDetailInfoAboutCrop.class);
                intent.putExtra("CropImage",cropInformationModel.getCropImage());
                intent.putExtra("CropName",cropInformationModel.getCropName());
                intent.putExtra("CropRate",cropInformationModel.getRate());
                intent.putExtra("Location",cropInformationModel.getLocation());
                intent.putExtra("farmerName",cropInformationModel.getFarmerName());
                intent.putExtra("farmerMobileNumber",cropInformationModel.getFarmerMobileNumber());
                intent.putExtra("farmerMobileNumberTwo",cropInformationModel.getFarmerMobileNumberTwo());
                intent.putExtra("farmerEmail",cropInformationModel.getFarmerEmail());
                intent.putExtra("CropKG",cropInformationModel.getCropKG());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cropInformationsmodel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView CropName,CropRate,Location,farmerName,farmerMobileNumber,farmerMobileNumberTwo,farmerEmail, clickHereFullViewInformation,CropKG;
        ImageView CropImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            CropImage = itemView.findViewById(R.id.CropImage);
            CropName = itemView.findViewById(R.id.CropName);
            CropRate = itemView.findViewById(R.id.CropRate);
            Location = itemView.findViewById(R.id.Location);
            farmerName = itemView.findViewById(R.id.farmerName);
            farmerMobileNumber = itemView.findViewById(R.id.farmerMobileNumber);
            farmerMobileNumberTwo = itemView.findViewById(R.id.farmerMobileNumberTwo);
            farmerEmail = itemView.findViewById(R.id.farmerEmail);
            clickHereFullViewInformation = itemView.findViewById(R.id.clickHereFullViewInformation);
            CropKG = itemView.findViewById(R.id.CropKG);
        }
    }
}
