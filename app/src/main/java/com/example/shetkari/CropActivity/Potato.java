package com.example.shetkari.CropActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shetkari.Adapters.CropInformationAdapter;
import com.example.shetkari.Model.CropInformationModel;
import com.example.shetkari.databinding.ActivityInformationAboutCropBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Potato extends AppCompatActivity {

    private ActivityInformationAboutCropBinding binding;
    private CropInformationAdapter cropInformationAdapter;
    private ArrayList<CropInformationModel> cropInformationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInformationAboutCropBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AllVegetable").child("Potato");
        binding.cropInformationRecyclerView.setLayoutManager(new LinearLayoutManager(Potato.this));
        binding.shimmerCropInformation.startShimmer();

        cropInformationModelArrayList = new ArrayList<>();
        cropInformationAdapter = new CropInformationAdapter(Potato.this, cropInformationModelArrayList);
        binding.cropInformationRecyclerView.setAdapter(cropInformationAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.shimmerCropInformation.stopShimmer();
                binding.shimmerCropInformation.setVisibility(View.GONE);
                binding.cropInformationRecyclerView.setVisibility(View.VISIBLE);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CropInformationModel cropInformationModel = dataSnapshot.getValue(CropInformationModel.class);
                    cropInformationModelArrayList.add(cropInformationModel);
                }
                cropInformationAdapter.notifyItemInserted(cropInformationModelArrayList.size() - 1);
                cropInformationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Potato.this, "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }
}