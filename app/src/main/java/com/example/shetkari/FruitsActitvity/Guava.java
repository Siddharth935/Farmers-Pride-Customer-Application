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
import com.example.shetkari.databinding.ActivityAppleBinding;
import com.example.shetkari.databinding.ActivityGuavaBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Guava extends AppCompatActivity {
    private ActivityGuavaBinding binding;
    private CropInformationAdapter cropInformationAdapter;
    private ArrayList<CropInformationModel> cropInformationModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AllFruits").child("Guava");
        binding.GuavaRecyclerView.setLayoutManager(new LinearLayoutManager(Guava.this));
        binding.GuavaShimmer.startShimmer();

        cropInformationModelArrayList = new ArrayList<>();
        cropInformationAdapter = new CropInformationAdapter(Guava.this, cropInformationModelArrayList);
        binding.GuavaRecyclerView.setAdapter(cropInformationAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.GuavaShimmer.stopShimmer();
                binding.GuavaShimmer.setVisibility(View.GONE);
                binding.GuavaRecyclerView.setVisibility(View.VISIBLE);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CropInformationModel cropInformationModel = dataSnapshot.getValue(CropInformationModel.class);
                    cropInformationModelArrayList.add(cropInformationModel);
                }
                cropInformationAdapter.notifyItemInserted(cropInformationModelArrayList.size() - 1);
                cropInformationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Guava.this, "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}