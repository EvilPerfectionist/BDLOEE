package com.example.buttonsarray;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarbonAdapter extends RecyclerView.Adapter<CarbonAdapter.ViewHolder>  {
    private List<Carbon> mCarbonList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        Button carbonButton;

        public ViewHolder(View view) {
            super(view);
            carbonButton = (Button) view.findViewById(R.id.carbon_button);
        }
    }

    public CarbonAdapter(List<Carbon> carbonList) {
        mCarbonList = carbonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carbon_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.carbonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Carbon carbon = mCarbonList.get(position);
                if(carbon.getType() == Fruit.TYPE_UNPRESSED){
                    ViewCompat.setBackgroundTintList(holder.carbonButton, ColorStateList.valueOf(Color.parseColor("#00994C")));
                    carbon.setType_pressed();
                }
                else{
                    ViewCompat.setBackgroundTintList(holder.carbonButton, ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                    carbon.setType_unpressed();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Carbon carbon = mCarbonList.get(position);
        holder.carbonButton.setText(carbon.getName());
        if(carbon.getType() == 1){
            ViewCompat.setBackgroundTintList(holder.carbonButton, ColorStateList.valueOf(Color.parseColor("#00994C")));
        }
        else{
            ViewCompat.setBackgroundTintList(holder.carbonButton, ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
        }
    }

    @Override
    public int getItemCount() {
        return mCarbonList.size();
    }
}
