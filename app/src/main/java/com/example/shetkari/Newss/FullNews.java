package com.example.shetkari.Newss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.shetkari.R;
import com.example.shetkari.databinding.ActivityFullNewsBinding;

public class FullNews extends AppCompatActivity {


    private ActivityFullNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFullNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.fullScreenDateAndDay.setText(getIntent().getStringExtra("DateAndDay"));
        Glide.with(this).load(getIntent().getStringExtra("shortNewsImage")).into(binding.newsImage);
        binding.fullScreenHeadline.setText(getIntent().getStringExtra("Headline").toString());
        binding.fullNewsDescription.setText(getIntent().getStringExtra("Description").toString());
        binding.bottomImageHeading.setText(getIntent().getStringExtra("bottomImageHeading").toString());
        binding.fullAdditionalOne.setText(getIntent().getStringExtra("additionalOne").toString());
        binding.fullAdditionalOne.setTextSize(18);
        binding.fullAdditionalOne.setTextColor(getResources().getColor(R.color.black));
        binding.fullAdditionalTwo.setText(getIntent().getStringExtra("additionalTwo").toString());
        binding.fullAdditionalTwo.setTextSize(15);
        binding.fullAdditionalTwo.setTextColor(getResources().getColor(R.color.templet_color));
        binding.fullAdditionalThree.setText(getIntent().getStringExtra("additionalThree").toString());
        binding.fullAdditionalThree.setTextSize(18);
        binding.fullAdditionalThree.setTextColor(getResources().getColor(R.color.black));
        binding.fullAdditionalFour.setText(getIntent().getStringExtra("additionalFour").toString());
        binding.fullAdditionalFour.setTextSize(15);
        binding.fullAdditionalFour.setTextColor(getResources().getColor(R.color.templet_color));
    }
}