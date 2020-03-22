package com.example.buttonsarray;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        Button fruitButton;
        public ViewHolder(View view) {
            super(view);
            fruitButton = (Button) view.findViewById(R.id.fruit_button);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                if(fruit.getType() == Fruit.TYPE_UNPRESSED){
                    ViewCompat.setBackgroundTintList(holder.fruitButton, ColorStateList.valueOf(Color.parseColor("#00994C")));
                    fruit.setType_pressed();
                }
                else{
                    ViewCompat.setBackgroundTintList(holder.fruitButton, ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                    fruit.setType_unpressed();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitButton.setText(fruit.getName());
        if(fruit.getType() == 1){
            ViewCompat.setBackgroundTintList(holder.fruitButton, ColorStateList.valueOf(Color.parseColor("#00994C")));
        }
        else{
            ViewCompat.setBackgroundTintList(holder.fruitButton, ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
        }
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }}
