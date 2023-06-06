package com.example.shetkari.fragments;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shetkari.Adapters.ViewPagerAdpaters;
import com.example.shetkari.LoginActivity;
import com.example.shetkari.R;
import com.example.shetkari.vegitablesFragments.Fruits;
import com.example.shetkari.vegitablesFragments.Vegetable;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DatabaseReference reference;
    private TextView Name, mobileNo, HI;
    private ImageView profileIMage;
    private LinearLayout btnhide, btnshow,logout;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Name = view.findViewById(R.id.Name);
        profileIMage = view.findViewById(R.id.profileImageView);
        mobileNo = view.findViewById(R.id.farmerMobileNumber);
//        HI = view.findViewById(R.id.hiiiii);
        btnhide = view.findViewById(R.id.Hii);
        logout = view.findViewById(R.id.logout);
        btnshow = view.findViewById(R.id.showLayout);

        btnhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isvisible = btnshow.getVisibility();
                if (isvisible == View.VISIBLE) {
                    btnshow.setVisibility(View.GONE);
//                    HI.setText("Hiiii");
                } else {
//                    HI.setText("Bye");
                    btnshow.setVisibility(View.VISIBLE);
                }
            }
        });
        Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isvisible = btnshow.getVisibility();
                if (isvisible == View.VISIBLE) {
                    btnshow.setVisibility(View.GONE);
//                    HI.setText("Hiiii");
                } else {
//                    HI.setText("Bye");
                    btnshow.setVisibility(View.VISIBLE);
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        profileIMage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isvisible = btnshow.getVisibility();
                if (isvisible == View.VISIBLE) {
                    btnshow.setVisibility(View.GONE);
//                    HI.setText("Hiiii");
                } else {
//                    HI.setText("Bye");
                    btnshow.setVisibility(View.VISIBLE);
                }
            }
        });
        getProfile();
        addFragment(view);
        return view;
    }
    private void logout() {
        new AlertDialog.Builder(getContext())
                .setIcon(R.drawable.baseline_warning_24)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout this app")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void getProfile() {
        reference = FirebaseDatabase.getInstance().getReference("CustomerProfile").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String getfarmerName = snapshot.child("customerName").getValue().toString();
                    Name.setText(getfarmerName);
                    String profileImage = snapshot.child("getUserImage").getValue().toString();
                    Glide.with(getApplicationContext()).load(profileImage).into(profileIMage);
                    String getfarmerMobileNumber = snapshot.child("customerMobileNumber").getValue().toString();
                    mobileNo.setText(getfarmerMobileNumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.Categories);
        viewPager = view.findViewById(R.id.viewpager2);

        ViewPagerAdpaters adpaters = new ViewPagerAdpaters(getChildFragmentManager());
        adpaters.addFragment(new Vegetable(),"Vegetable");
        adpaters.addFragment(new Fruits(),"Fruits");
        viewPager.setAdapter(adpaters);
        tabLayout.setupWithViewPager(viewPager);
    }
}