package com.example.aguilar_cipolatti_final.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aguilar_cipolatti_final.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button mButtonIngresar;
    Button mButtonRegistrarse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonIngresar=findViewById(R.id.btningresar);
        mButtonRegistrarse=findViewById(R.id.btnregistrar);

        mButtonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        mButtonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegistro();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(MainActivity.this, Mapa_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    private void goToRegistro() {
        Intent intent=new Intent(MainActivity.this, Registro_Activity.class);
        startActivity(intent);
    }

    private void goToLogin() {
        Intent intent = new Intent(MainActivity.this, Login_Activity.class);
        startActivity(intent);
    }

}