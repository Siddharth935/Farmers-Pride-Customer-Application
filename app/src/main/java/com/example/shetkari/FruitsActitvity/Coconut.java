package com.example.shetkari.FruitsActitvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shetkari.Adapters.CropInformationAdapter;
import com.example.shetkari.Model.CropInformationModel;
import com.example.shetkari.R;
import com.example.shetkari.databinding.ActivityCoconutBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Coconut extends AppCompatActivity {

    private ActivityCoconutBinding binding;
    private CropInformationAdapter cropInformationAdapter;
    private ArrayList<CropInformationModel> cropInformationModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCoconutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AllFruits").child("Coconut");
        binding.CoconutRecyclerView.setLayoutManager(new LinearLayoutManager(Coconut.this));
        binding.CoconutShimmer.startShimmer();

        cropInformationModelArrayList = new ArrayList<>();
        cropInformationAdapter = new CropInformationAdapter(Coconut.this, cropInformationModelArrayList);
        binding.CoconutRecyclerView.setAdapter(cropInformationAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.CoconutShimmer.stopShimmer();
                binding.CoconutShimmer.setVisibility(View.GONE);
                binding.CoconutRecyclerView.setVisibility(View.VISIBLE);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CropInformationModel cropInformationModel = dataSnapshot.getValue(CropInformationModel.class);
                    cropInformationModelArrayList.add(cropInformationModel);
                }
                cropInformationAdapter.notifyItemInserted(cropInformationModelArrayList.size() - 1);
                cropInformationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Coconut.this, "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}