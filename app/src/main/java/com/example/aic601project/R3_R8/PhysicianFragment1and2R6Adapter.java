package com.example.aic601project.R3_R8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aic601project.ModelAppointment;
import com.example.aic601project.ModelPatient;
import com.example.aic601project.R;
import com.example.aic601project.RecyclerViewInterface;

import java.util.ArrayList;

public class PhysicianFragment1and2R6Adapter extends RecyclerView.Adapter<PhysicianFragment1and2R6Adapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ModelAppointment> appointments;
    ArrayList<ModelPatient> patients;

    public PhysicianFragment1and2R6Adapter(Context context, ArrayList<ModelAppointment> appointments, ArrayList<ModelPatient> patients,
                                           RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.appointments = appointments;
        this.patients = patients;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public PhysicianFragment1and2R6Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_appointments, parent, false);
        return new PhysicianFragment1and2R6Adapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(PhysicianFragment1and2R6Adapter.MyViewHolder holder, int position) {
        String nameSurname = patients.get(position).getName().concat(" " + patients.get(position).getSurname());
        holder.name.setText(nameSurname);
        holder.date.setText(appointments.get(position).getDate().substring(0, 16));
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
