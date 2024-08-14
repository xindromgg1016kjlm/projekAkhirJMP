package com.example.projekakhirjmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Login extends AppCompatActivity {

    Button btnregister,btnlogin;
    EditText eduserlogin, edpasslogin;
    DatabaseHelper dblogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        eduserlogin = findViewById(R.id.editTextUsername);
        edpasslogin = findViewById(R.id.editTextPassword);
        btnregister = findViewById(R.id.buttonRegister);
        btnlogin    = findViewById(R.id.buttonLogin);
        dblogin = new DatabaseHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String suser = eduserlogin.getText().toString();
                String spassword = edpasslogin.getText().toString();
                Boolean checkUserPassword = dblogin.checkUserPassword(suser, spassword);
                if (checkUserPassword) {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                        startActivity(intent);
            }
        });





    }
}