package com.example.shetkari.vegitablesFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shetkari.CropActivity.Corn;
import com.example.shetkari.CropActivity.Ginger;
import com.example.shetkari.CropActivity.Onion;
import com.example.shetkari.CropActivity.Peanut;
import com.example.shetkari.CropActivity.Potato;
import com.example.shetkari.CropActivity.Rice;
import com.example.shetkari.databinding.FragmentVegetableBinding;

public class Vegetable extends Fragment {

    private FragmentVegetableBinding binding;

    public Vegetable() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVegetableBinding.inflate(getLayoutInflater());

        binding.potato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Potato.class);
                startActivity(intent);

            }
        });
        binding.Corn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Corn.class);
                startActivity(intent);

            }
        });
        binding.Onion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Onion.class);
                startActivity(intent);

            }
        });
        binding.Peanut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Peanut.class);
                startActivity(intent);

            }
        });
        binding.Rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Rice.class);
                startActivity(intent);

            }
        });
        binding.Ginger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Ginger.class);
                startActivity(intent);

            }
        });
        return binding.getRoot();
    }
}