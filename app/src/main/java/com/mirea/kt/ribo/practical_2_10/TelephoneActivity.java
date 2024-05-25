package com.mirea.kt.ribo.practical_2_10;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TelephoneActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TelephoneAdapter adapter;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        DBManager dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));
        ArrayList<Telephone> telephones = dbManager.loadAllTelephonesFromDatabase();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TelephoneAdapter adapter = new TelephoneAdapter(telephones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}