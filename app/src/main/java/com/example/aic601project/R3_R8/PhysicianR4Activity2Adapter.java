package com.example.aic601project.R3_R8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aic601project.ModelR4Appointment;
import com.example.aic601project.R;
import com.example.aic601project.RecyclerViewInterface;

import java.util.ArrayList;

public class PhysicianR4Activity2Adapter extends RecyclerView.Adapter<PhysicianR4Activity2Adapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ModelR4Appointment> appointments;

    public PhysicianR4Activity2Adapter(Context context, ArrayList<ModelR4Appointment> appointments,
                                       RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.appointments = appointments;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public PhysicianR4Activity2Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_appointments, parent, false);
        return new PhysicianR4Activity2Adapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(PhysicianR4Activity2Adapter.MyViewHolder holder, int position) {
        holder.date.setText(appointments.get(position).getDate().substring(0, 10));
        holder.name.setText(appointments.get(position).getLastName());
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
