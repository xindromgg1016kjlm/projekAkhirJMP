package com.example.projekakhirjmp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputData extends AppCompatActivity {
    Button btneditinput;
    EditText ednama, edumur, edmoto;
    DatabaseHelper dbmaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btneditinput = findViewById(R.id.btnsimpan);
        ednama = findViewById(R.id.editnama);
        edumur = findViewById(R.id.editumur);
        edmoto = findViewById(R.id.editmoto);
        dbmaster = new DatabaseHelper(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");

        if (id != null) {
            Cursor data = dbmaster.getAllData();
            if (data.moveToFirst()) {
                do {
                    if (data.getString(0).equals(id)) {
                        ednama.setText(data.getString(1));
                        edumur.setText(data.getString(2));
                        edmoto.setText(data.getString(3));
                        btneditinput.setText("Update Data");
                        break;
                    }
                } while (data.moveToNext());
            }

            btneditinput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = dbmaster.updateData(id, ednama.getText().toString(), Integer.parseInt(edumur.getText().toString()), edmoto.getText().toString());
                    if (isUpdated)
                        Toast.makeText(InputData.this, "Data Updated", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(InputData.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            btneditinput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = dbmaster.insertData(ednama.getText().toString(), Integer.parseInt(edumur.getText().toString()), edmoto.getText().toString());
                    if (isInserted)
                        Toast.makeText(InputData.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(InputData.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}