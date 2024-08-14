package com.example.projekakhirjmp;

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

public class Register extends AppCompatActivity {

    Button btnregister;
    EditText edusername,edpassword;
    DatabaseHelper dblogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnregister = findViewById(R.id.buttonreg);
        edusername = findViewById(R.id.editTextUsername1);
        edpassword = findViewById(R.id.editTextPassword1);
        dblogin = new DatabaseHelper(this);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edusername.getText().toString();
                String password = edpassword.getText().toString();
                Boolean checkUser = dblogin.checkUser(user);
                if (checkUser == false) {
                    Boolean insert = dblogin.insertUser(user, password);
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
