package com.example.shetkari.Newss;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shetkari.Adapters.NewsAdapter;
import com.example.shetkari.Model.NewsModel;
import com.example.shetkari.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HindiNews extends Fragment {

    private DatabaseReference reference;
    private NewsAdapter newsAdapter;
    private ArrayList<NewsModel> list;
    private ShimmerFrameLayout shimmerFrameLayout;

    public HindiNews() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hindi_news, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.hindiNewsRecycler);
        reference = FirebaseDatabase.getInstance().getReference("HindiNews");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shimmerFrameLayout = view.findViewById(R.id.shimmerHindi);
        shimmerFrameLayout.startShimmer();

        list = new ArrayList<>();
        newsAdapter = new NewsAdapter(getContext(), list);
        recyclerView.setAdapter(newsAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    NewsModel newsModel = dataSnapshot.getValue(NewsModel.class);
                    list.add(newsModel);
                }
                newsAdapter.notifyItemInserted(list.size() - 1);
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }
}