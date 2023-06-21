package com.example.aic601project.R3_R8;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.example.aic601project.R7ListItem;
import com.example.aic601project.R;
import com.google.android.material.appbar.MaterialToolbar;

public class PhysicianR7Activity1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhysicianR7Activity1Adapter adapter;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_r7_1);
        getWindow().setStatusBarColor(getResources().getColor(R.color.md_theme_light_surfaceVariant, this.getTheme()));

        toolbar = findViewById(R.id.topAppBar); // Change to "topAppBar" instead of "appBarLayout"
        setupToolbarWithBackButton();

        recyclerView = findViewById(R.id.recyclerView);

        List<R7ListItem> patientList = new ArrayList<R7ListItem>();
        // Create a list of sample patients
        patientList.add(new R7ListItem("2023-04-30", "John Doe", "10:00"));
        patientList.add(new R7ListItem("2023-05-01", "John Doe", "10:00"));
        patientList.add(new R7ListItem("2023-05-02", "Jane Smith", "09:30"));
        patientList.add(new R7ListItem("2023-05-03", "Bob Johnson", "13:00"));

        adapter = new PhysicianR7Activity1Adapter (patientList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }


    // sets up a toolbar where clicking the back button calls onBackPressed()
    private void setupToolbarWithBackButton() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    // overrides the default onBackPressed() function and includes an exit animation
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_slide_in_or_out, R.anim.slide_out_from_top);
    }
}
