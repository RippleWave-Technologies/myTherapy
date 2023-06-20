package com.example.aic601project.R1_R2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aic601project.ModelService;
import com.example.aic601project.R;
import com.example.aic601project.RecyclerViewInterface;

import java.util.ArrayList;

public class AdminFragment2Adapter extends RecyclerView.Adapter<AdminFragment2Adapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ModelService> services;

    public AdminFragment2Adapter(Context context, ArrayList<ModelService> services,
                                RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.services = services;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public AdminFragment2Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contacts, parent, false);
        return new AdminFragment2Adapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(AdminFragment2Adapter.MyViewHolder holder, int position) {
        holder.serviceName.setText(services.get(position).getName());
        holder.serviceName1Letter.setText(services.get(position).getName().substring(0, 1).toUpperCase());
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, serviceName1Letter;

        public MyViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.name);
            serviceName1Letter = itemView.findViewById(R.id.iconview);

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
