package com.example.shetkari.CropActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.bumptech.glide.Glide;
import com.example.shetkari.databinding.ActivityFullScreenDetailInfoAboutCropBinding;

public class FullScreenDetailInfoAboutCrop extends AppCompatActivity {

    private ActivityFullScreenDetailInfoAboutCropBinding binding;
//    private RightScrollRecyclerAdapter rightScrollRecyclerAdapter;
//    private ArrayList<RightScrollModel> rightScrollModelArrayList;
    private static int PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullScreenDetailInfoAboutCropBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (ContextCompat.checkSelfPermission(FullScreenDetailInfoAboutCrop.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(FullScreenDetailInfoAboutCrop.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CODE);

        }
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AllVegetableAllVegetable").child("Potato");
//        binding.morePotatoRecycler.setLayoutManager(new LinearLayoutManager(FullScreenDetailInfoAboutCrop.this, LinearLayoutManager.HORIZONTAL, false));
//        binding.shimmerCropInformation.startShimmer();
//
//        rightScrollModelArrayList = new ArrayList<>();
//        rightScrollRecyclerAdapter = new RightScrollRecyclerAdapter(FullScreenDetailInfoAboutCrop.this, rightScrollModelArrayList);
//        binding.morePotatoRecycler.setAdapter(rightScrollRecyclerAdapter);

        Glide.with(this).load(getIntent().getStringExtra("CropImage")).into(binding.CropImageFull);
        binding.CropNameFull.setText(getIntent().getStringExtra("CropName").toString());
        binding.CropKGFull.setText(getIntent().getStringExtra("CropKG").toString());
        binding.CropRateFull.setText(getIntent().getStringExtra("CropRate").toString());
        binding.LocationFull.setText(getIntent().getStringExtra("Location").toString());
        binding.farmerNameFull.setText(getIntent().getStringExtra("farmerName").toString());
        binding.farmerMobileNumberFull.setText(getIntent().getStringExtra("farmerMobileNumber").toString());
        binding.farmerMobileNumberTwoFull.setText(getIntent().getStringExtra("farmerMobileNumberTwo").toString());
        binding.farmerEmailFull.setText(getIntent().getStringExtra("farmerEmail").toString());

        binding.farmerMobileNumberFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno = binding.farmerMobileNumberFull.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + phoneno));
                startActivity(i);
            }
        });
        binding.farmerMobileNumberTwoFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno = binding.farmerMobileNumberTwoFull.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + phoneno));
                startActivity(i);
            }
        });
        binding.farmerEmailFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String feedback = "mailto:" + Uri.encode(binding.farmerEmailFull.getText().toString()) + "?subject=" + Uri.encode("Feedback") + Uri.encode("");
                Uri uri = Uri.parse(feedback);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent, "send email"));
            }
        });

//        reference.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                binding.shimmerCropInformation.stopShimmer();
//                binding.shimmerCropInformation.setVisibility(View.GONE);
//                binding.morePotatoRecycler.setVisibility(View.VISIBLE);
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    RightScrollModel rightScrollModel = dataSnapshot.getValue(RightScrollModel.class);
//                    rightScrollModelArrayList.add(rightScrollModel);
//                }
//                rightScrollRecyclerAdapter.notifyItemInserted(rightScrollModelArrayList.size() - 1);
//                rightScrollRecyclerAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(FullScreenDetailInfoAboutCrop.this, "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        });


    }
}