package com.example.shetkari.CropActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shetkari.Adapters.CropInformationAdapter;
import com.example.shetkari.Model.CropInformationModel;
import com.example.shetkari.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Corn extends AppCompatActivity {

    private CropInformationAdapter cropInformationAdapter;
    private ArrayList<CropInformationModel> cropInformationModelArrayList;
    private ShimmerFrameLayout shimmerFrameLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corn);

        shimmerFrameLayout = findViewById(R.id.CornShimmer);
        recyclerView = findViewById(R.id.CornRecyclerView);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AllVegetable").child("Corn");
        recyclerView.setLayoutManager(new LinearLayoutManager(Corn.this));
        shimmerFrameLayout.startShimmer();

        cropInformationModelArrayList = new ArrayList<>();
        cropInformationAdapter = new CropInformationAdapter(Corn.this, cropInformationModelArrayList);
        recyclerView.setAdapter(cropInformationAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CropInformationModel cropInformationModel = dataSnapshot.getValue(CropInformationModel.class);
                    cropInformationModelArrayList.add(cropInformationModel);
                }
                cropInformationAdapter.notifyItemInserted(cropInformationModelArrayList.size() - 1);
                cropInformationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Corn.this, "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}