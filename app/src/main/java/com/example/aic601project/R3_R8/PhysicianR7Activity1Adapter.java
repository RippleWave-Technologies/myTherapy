package com.example.aic601project.R3_R8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aic601project.R7ListItem;
import com.example.aic601project.R;

import java.util.List;

public class PhysicianR7Activity1Adapter extends RecyclerView.Adapter<PhysicianR7Activity1Adapter.ViewHolder> {
    private List<R7ListItem> itemList; // List to hold the data for each list item

    // Constructor for the adapter
    public PhysicianR7Activity1Adapter(List<R7ListItem> itemList) {
        this.itemList = itemList;
    }

    // ViewHolder class to hold the views for each list item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView;
        public TextView nameTextView;
        public ImageButton infoButton;
        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            infoButton = itemView.findViewById(R.id.infoButton);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for the list item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_physician_r7, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the item data at the given position
        R7ListItem item = itemList.get(position);
        // Set the data to the views in the ViewHolder
        holder.dateTextView.setText(item.getDate());
        holder.nameTextView.setText(item.getName());

        // Set click listener for the info button
        holder.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the clicked item
                R7ListItem patient = itemList.get(holder.getAdapterPosition());

                // Start the InformationActivity and pass the necessary data
                Intent intent = new Intent(holder.itemView.getContext(), PhysicianR7Activity2.class);
                intent.putExtra("date", patient.getDate());
                intent.putExtra("name", patient.getName());
                intent.putExtra("time", patient.getTime());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
