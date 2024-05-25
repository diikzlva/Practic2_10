package com.mirea.kt.ribo.practical_2_10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextModel, editTextSerialNumber, editTextPrice;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));
        editTextModel = findViewById(R.id.etModel);
        editTextSerialNumber = findViewById(R.id.etSerialNumber);
        editTextPrice = findViewById(R.id.etPrice);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnNext = findViewById(R.id.btnNext);
        btnAdd.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd){
            if (this.dbManager != null){
                String model = editTextModel.getText().toString();
                String serialNumber = editTextSerialNumber.getText().toString();
                String price = editTextPrice.getText().toString();
                Log.d("ПРОВЕРКА",model+serialNumber);
                if(!model.isEmpty() && !serialNumber.isEmpty() && !price.isEmpty()){
                    boolean result = dbManager.saveTelephoneToDatabase(new Telephone(model, serialNumber, Integer.parseInt(price)));
                    if (result){
                        Toast.makeText(this, R.string.insert_success, Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(this, R.string.insert_error, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, R.string.incorrect_value, Toast.LENGTH_LONG).show();
                }
            }
        }else if (v.getId() == R.id.btnNext){
            startActivity(new Intent(this, TelephoneActivity.class));
        }
    }
}