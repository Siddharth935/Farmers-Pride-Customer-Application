package com.example.shetkari.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shetkari.Model.NewsModel;
import com.example.shetkari.Newss.FullNews;
import com.example.shetkari.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    Context context;
    ArrayList<NewsModel> newsModelArrayList;

    public NewsAdapter(Context context, ArrayList<NewsModel> newsModelArrayList) {
        this.context = context;
        this.newsModelArrayList = newsModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_news_templete, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NewsModel newsModel = newsModelArrayList.get(position);
        holder.DateAndDay.setText(newsModel.getDateAndDay());
        holder.Headline.setText(newsModel.getHeadline());
        holder.Description.setText(newsModel.getDescription());
        holder.additionalOne.setText(newsModel.getAdditionalOne());
        holder.additionalTwo.setText(newsModel.getAdditionalTwo());
        holder.additionalThree.setText(newsModel.getAdditionalThree());
        holder.additionalFour.setText(newsModel.getAdditionalFour());
        Glide.with(context).load(newsModel.getShortNewsImage()).into(holder.shortNewsImage);
        holder.bottomImageHeading.setText(newsModel.getBottomImageHeading());
        holder.clickHereFullView.setText(newsModel.getClickHereFullView());

        holder.clickHereFullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, FullNews.class);
                intent.putExtra("DateAndDay",newsModel.getDateAndDay());
                intent.putExtra("Headline",newsModel.getHeadline());
                intent.putExtra("Description",newsModel.getDescription());
                intent.putExtra("shortNewsImage", newsModel.getShortNewsImage());
                intent.putExtra("bottomImageHeading", newsModel.getBottomImageHeading());
                intent.putExtra("additionalOne", newsModel.getAdditionalOne());
                intent.putExtra("additionalTwo", newsModel.getAdditionalTwo());
                intent.putExtra("additionalThree", newsModel.getAdditionalThree());
                intent.putExtra("additionalFour", newsModel.getAdditionalFour());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView DateAndDay, Headline, Description,bottomImageHeading,clickHereFullView, additionalOne, additionalTwo, additionalThree, additionalFour;
        ImageView shortNewsImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            DateAndDay = itemView.findViewById(R.id.DateAndDay);
            Headline = itemView.findViewById(R.id.Headline);
            Description = itemView.findViewById(R.id.Description);
            shortNewsImage = itemView.findViewById(R.id.shortNewsImage);
            bottomImageHeading = itemView.findViewById(R.id.bottomImageHeading);
            clickHereFullView = itemView.findViewById(R.id.clickHereFullView);
            additionalOne = itemView.findViewById(R.id.additionalOne);
            additionalTwo = itemView.findViewById(R.id.additionalTwo);
            additionalThree = itemView.findViewById(R.id.additionalThree);
            additionalFour = itemView.findViewById(R.id.additionalFour);
        }
    }
}
