package com.example.aic601project.R3_R8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aic601project.ModelPatient;
import com.example.aic601project.R;

import java.util.ArrayList;

public class PhysicianFragment3R5Adapter extends RecyclerView.Adapter<PhysicianFragment3R5Adapter.NewR5Holder> {

    private ArrayList<ModelPatient> patientsList;
    private RecyclerViewClickListener listener;

    public PhysicianFragment3R5Adapter(ArrayList<ModelPatient> patientsList,
                                       RecyclerViewClickListener listener) {
        this.patientsList = patientsList;
        this.listener = listener;
    }

    public void setFilteredList(ArrayList<ModelPatient> filteredList) {
        this.patientsList = filteredList;
        notifyDataSetChanged();
    }

    public class NewR5Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView iconView;
        TextView nameView;

        public NewR5Holder(@NonNull View itemView) {
            super(itemView);
            iconView = itemView.findViewById(R.id.iconview);
            nameView = itemView.findViewById((R.id.name));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public NewR5Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contacts, parent,
                false);
        return new NewR5Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewR5Holder holder, int position) {
        //ModelPatient patient = patientsList.get(position);
        String str = patientsList.get(position).getSurname() + " " + patientsList.get(position).getName();
        holder.nameView.setText(str);
        String subs = holder.nameView.getText().toString().substring(0, 1);
        holder.iconView.setText(subs.toUpperCase());
    }

    @Override
    public int getItemCount() {
        if (patientsList == null)
            return 0;
        else
            return patientsList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
