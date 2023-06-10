package com.example.aic601project.R1_R2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aic601project.ModelClinic;
import com.example.aic601project.R;
import com.example.aic601project.RecyclerViewInterface;

import java.util.ArrayList;

public class AdminFragment1Adapter extends RecyclerView.Adapter<AdminFragment1Adapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ModelClinic> clinics;

    public AdminFragment1Adapter(Context context, ArrayList<ModelClinic> clinics,
                                 RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.clinics = clinics;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contacts, parent, false);
        return new AdminFragment1Adapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.physioName.setText(clinics.get(position).getPhysioName());
        holder.physioName1Letter.setText(clinics.get(position).getPhysioName().substring(0, 1).toUpperCase());
    }

    @Override
    public int getItemCount() {
        return clinics.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView physioName, physioName1Letter;

        public MyViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            physioName = itemView.findViewById(R.id.name);
            physioName1Letter = itemView.findViewById(R.id.iconview);

            itemView.setOnClickListener(v -> {
                if (recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            });
        }
    }
}