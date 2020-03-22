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

public class ProteinAdapter extends RecyclerView.Adapter<ProteinAdapter.ViewHolder> {
    private List<Protein> mProteinList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        Button proteinButton;
        public ViewHolder(View view) {
            super(view);
            proteinButton = (Button) view.findViewById(R.id.protein_button);
        }
    }

    public ProteinAdapter(List<Protein> proteinList) {
        mProteinList = proteinList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.protein_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.proteinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Protein protein = mProteinList.get(position);
                if(protein.getType() == Fruit.TYPE_UNPRESSED){
                    ViewCompat.setBackgroundTintList(holder.proteinButton, ColorStateList.valueOf(Color.parseColor("#00994C")));
                    protein.setType_pressed();
                }
                else{
                    ViewCompat.setBackgroundTintList(holder.proteinButton, ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
                    protein.setType_unpressed();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Protein protein = mProteinList.get(position);
        holder.proteinButton.setText(protein.getName());
        if(protein.getType() == 1){
            ViewCompat.setBackgroundTintList(holder.proteinButton, ColorStateList.valueOf(Color.parseColor("#00994C")));
        }
        else{
            ViewCompat.setBackgroundTintList(holder.proteinButton, ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
        }
    }

    @Override
    public int getItemCount() {
        return mProteinList.size();
    }
}
