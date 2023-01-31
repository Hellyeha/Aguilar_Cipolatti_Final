package com.example.aguilar_cipolatti_final.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aguilar_cipolatti_final.R;
import com.example.aguilar_cipolatti_final.includes.MyToolBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_Activity extends AppCompatActivity {

    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPassword;
    Button mButtonLogin;


    FirebaseAuth mAuth;
    DatabaseReference mDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MyToolBar.show(this,"Inicio de Sesión",true);


        mTextInputEmail = findViewById(R.id.textinputcorreo);
        mTextInputPassword=findViewById(R.id.textinputpassword);
        mButtonLogin=findViewById(R.id.botonacceder);

        mAuth=FirebaseAuth.getInstance();
        mDataBase= FirebaseDatabase.getInstance().getReference();

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        String email = mTextInputEmail.getText().toString();
        String password=mTextInputPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()){
            if (password.length() >=6) {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login_Activity.this, "Ingreso con Exito", Toast.LENGTH_SHORT).show();


                            Intent intent =new Intent(Login_Activity.this, Mapa_Activity.class);

                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);


                    }
                        else {
                            Toast.makeText(Login_Activity.this, "La Contraseña es Incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            else{
                Toast.makeText(this, "La contraseña debe contener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }

            }
            else {
            Toast.makeText(this, "Ingrese Correo y Contraseña", Toast.LENGTH_SHORT).show();
        }

        }
    }