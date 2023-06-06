package com.example.shetkari.fragments;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.shetkari.ProfileActivity.EditProfile;
import com.example.shetkari.ProfileActivity.SeeProfile;
import com.example.shetkari.ProfileActivity.VideoTutorial;
import com.example.shetkari.R;
import com.example.shetkari.WeatherActivity;
import com.example.shetkari.Whether;
import com.example.shetkari.databinding.FragmentSettingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Setting extends Fragment {

    private DatabaseReference reference;
    private FragmentSettingBinding binding;
    public Setting() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(getLayoutInflater());
        getProfile();

        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfile.class);
                startActivity(intent);
            }
        });

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SeeProfile.class);
                startActivity(intent);
            }
        });
//        binding.myShop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), MyShop.class);
//                startActivity(intent);
//            }
//        });
//        binding.SelectYourLanguage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Lanugage.class);
//                startActivity(intent);
//            }
//        });

        binding.weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WeatherActivity.class);
                startActivity(intent);
            }
        });
        binding.videoTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoTutorial.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }

    private void getProfile() {
        reference = FirebaseDatabase.getInstance().getReference("CustomerProfile").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String getfarmerName = snapshot.child("customerName").getValue().toString();
                    binding.name.setText(getfarmerName);
                    String profileImage = snapshot.child("getUserImage").getValue().toString();
                    Glide.with(getApplicationContext()).load(profileImage).into(binding.getCustomerProfileImage);
                    String getfarmerCity = snapshot.child("city").getValue().toString();
                    binding.city.setText(getfarmerCity);
                    String getFarmerCountry = snapshot.child("country").getValue().toString();
                    binding.country.setText(getFarmerCountry);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}