package com.example.buttonsarray;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpiceAdapter extends RecyclerView.Adapter<SpiceAdapter.ViewHolder> {
    private List<Spice> mSpiceList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        Button spiceButton;
        public ViewHolder(View view) {
            super(view);
            spiceButton = (Button) view.findViewById(R.id.spice_button);
        }
    }

    public SpiceAdapter(List<Spice> spiceList) {
        mSpiceList = spiceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spice_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.spiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Spice spice = mSpiceList.get(position);
                if(spice.getType() == Fruit.TYPE_UNPRESSED){
                    ViewCompat.setBackgroundTintList(holder.spiceButton, ColorStateList.valueOf(Color.parseColor("#00994C")));
                    spice.setType_pressed();
                }
                else{
                    ViewCompat.setBackgroundTintList(holder.spiceButton, ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                    spice.setType_unpressed();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Spice spice = mSpiceList.get(position);
        holder.spiceButton.setText(spice.getName());
        if(spice.getType() == 1){
            ViewCompat.setBackgroundTintList(holder.spiceButton, ColorStateList.valueOf(Color.parseColor("#00994C")));
        }
        else{
            ViewCompat.setBackgroundTintList(holder.spiceButton, ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
        }
    }

    @Override
    public int getItemCount() {
        return mSpiceList.size();
    }
}
