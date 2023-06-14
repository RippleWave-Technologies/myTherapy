package com.example.aic601project.R9_R10;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aic601project.R;
import com.example.aic601project.R10ListItem;

import java.util.List;

public class UserFragment2R10Adapter extends RecyclerView.Adapter<UserFragment2R10Adapter.ButtonViewHolder> {
    private List<R10ListItem> itemList;

    public UserFragment2R10Adapter(List<R10ListItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_r10, parent, false);
        return new ButtonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        R10ListItem item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ButtonViewHolder extends RecyclerView.ViewHolder {
        private Button historyButton;

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            historyButton = itemView.findViewById(R.id.R10history1);
            historyButton.setOnClickListener(v -> {
                // Open another layout (Activity/Fragment) here
                Intent intent = new Intent(itemView.getContext(), UserR10Activity.class);
                itemView.getContext().startActivity(intent);
            });
        }
        public void bind(R10ListItem item) {
            String name = item.getName();
            String date = item.getDate();
            String number = item.getNumber();

            // Create the text using the dynamic data
            String buttonText =  date +"  " +name+ "   " + number;
            // Set the text for the Button
            historyButton.setText(buttonText);
        }
    }
}