package com.example.aic601project.R3_R8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aic601project.R;

import java.util.List;

public class NewR5Adapter extends RecyclerView.Adapter<NewR5Adapter.NewR5Holder> {

    private List<Temppatients> patientsList;
    private RecyclerViewClickListener listener;

    public NewR5Adapter(List<Temppatients> patientsList, RecyclerViewClickListener listener) {
        this.patientsList = patientsList;
        this.listener = listener;
    }

    public void setFilteredList(List<Temppatients> filteredList){
        this.patientsList = filteredList;
        notifyDataSetChanged();
    }

    public class NewR5Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_physician_r5_patient, parent, false);
        return new NewR5Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewR5Holder holder, int position) {
        Temppatients patient = patientsList.get(position);
        holder.nameView.setText(patientsList.get(position).getName());
        String subs = holder.nameView.getText().toString().substring(0,1);
        holder.iconView.setText(subs.toUpperCase());
        //holder.iconView.setImageResource((patientsList.get(position).getImage()));
    }

    @Override
    public int getItemCount() {
        if(patientsList==null)
            return 0;
        else
            return patientsList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}

