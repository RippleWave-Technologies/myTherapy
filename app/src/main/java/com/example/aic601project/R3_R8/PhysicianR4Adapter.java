package com.example.aic601project.R3_R8;

import java.util.List;

import com.example.aic601project.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhysicianR4Adapter extends RecyclerView.Adapter<PhysicianR4Adapter.NewR4Holder> {
    private List<JavaTempPhysicianR5Patients> patientsList;
    private PhysicianR4Adapter.RecyclerViewClickListener listener;

    public PhysicianR4Adapter(List<JavaTempPhysicianR5Patients> patientsList,
            PhysicianR4Adapter.RecyclerViewClickListener listener) {
        this.patientsList = patientsList;
        this.listener = listener;
    }

    public class NewR4Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView appoinment;

        public NewR4Holder(@NonNull View itemView) {
            super(itemView);
            appoinment = itemView.findViewById(R.id.history1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(itemView, getAdapterPosition());
        }

    }

    @NonNull
    @Override
    public PhysicianR4Adapter.NewR4Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_physician_r4, parent, false);
        return new PhysicianR4Adapter.NewR4Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhysicianR4Adapter.NewR4Holder holder, int position) {
        holder.appoinment.setText(patientsList.get(position).getName());
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
