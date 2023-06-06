package com.example.shetkari.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shetkari.Adapters.ViewPagerAdpaters;
import com.example.shetkari.Newss.AssamiNews;
import com.example.shetkari.Newss.HindiNews;
import com.example.shetkari.Newss.MarathiNews;
import com.example.shetkari.Newss.PunjabiNews;
import com.example.shetkari.R;
import com.example.shetkari.vegitablesFragments.Fruits;
import com.example.shetkari.vegitablesFragments.Vegetable;
import com.google.android.material.tabs.TabLayout;

public class NewsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        addFragment(view);
        return view;
    }

    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.NewsLanguage);
        viewPager = view.findViewById(R.id.NewsLanguageViewPager);

        ViewPagerAdpaters adpaters = new ViewPagerAdpaters(getChildFragmentManager());
        adpaters.addFragment(new MarathiNews(),"Marathi");
        adpaters.addFragment(new HindiNews(),"Hindi");
        adpaters.addFragment(new PunjabiNews(),"Punjabi");
        adpaters.addFragment(new AssamiNews(),"Assami");
        viewPager.setAdapter(adpaters);
        tabLayout.setupWithViewPager(viewPager);
    }
}