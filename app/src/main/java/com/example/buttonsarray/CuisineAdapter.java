package com.example.buttonsarray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.ViewHolder>{
    private Context mContext;
    private List<Cuisine> mCuisineList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView cuisineImage;
        TextView cuisineName;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            cuisineImage = (ImageView) view.findViewById(R.id.fruit_image);
            cuisineName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    public CuisineAdapter(List<Cuisine> cuisineList) {
        mCuisineList = cuisineList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.cuisine_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cuisine cuisine = mCuisineList.get(position);
        holder.cuisineName.setText(cuisine.getName());
        Glide.with(mContext).load(cuisine.getImageId()).into(holder.cuisineImage);
    }

    @Override
    public int getItemCount() {
        return mCuisineList.size();
    }

}
