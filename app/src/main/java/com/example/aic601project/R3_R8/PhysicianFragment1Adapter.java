package com.example.aic601project.R3_R8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aic601project.ModelAppointmentPFragment1;
import com.example.aic601project.R;
import com.example.aic601project.RecyclerViewInterface;

import java.util.ArrayList;

public class PhysicianFragment1Adapter extends RecyclerView.Adapter<com.example.aic601project.R3_R8.PhysicianFragment1Adapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ModelAppointmentPFragment1> appointments;

    public PhysicianFragment1Adapter(Context context, ArrayList<ModelAppointmentPFragment1> appointments,
                                 RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.appointments = appointments;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public com.example.aic601project.R3_R8.PhysicianFragment1Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_appointments, parent, false);
        return new com.example.aic601project.R3_R8.PhysicianFragment1Adapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(com.example.aic601project.R3_R8.PhysicianFragment1Adapter.MyViewHolder holder, int position) {
        holder.date.setText(appointments.get(position).getAptDate().substring(0, appointments.get(position).getAptDate().length() - 3));
        holder.name.setText(appointments.get(position).getAptSurname().concat(" " + appointments.get(position).getAptName()));
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, name;

        public MyViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);

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
