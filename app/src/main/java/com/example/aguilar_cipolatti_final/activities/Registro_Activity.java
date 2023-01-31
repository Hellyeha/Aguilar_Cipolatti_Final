package com.example.aguilar_cipolatti_final.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aguilar_cipolatti_final.R;
import com.example.aguilar_cipolatti_final.includes.MyToolBar;
import com.example.aguilar_cipolatti_final.models.User;
import com.example.aguilar_cipolatti_final.providers.AuthProviders;
import com.example.aguilar_cipolatti_final.providers.UserProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro_Activity extends AppCompatActivity {

    AuthProviders mAuthproviders;
    UserProvider mUserprovider;

    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        MyToolBar.show(this, "Registro", true);

        mAuthproviders = new AuthProviders();
        mUserprovider = new UserProvider();


        mButtonRegister=findViewById(R.id.btnRegister);
        mTextInputName=findViewById(R.id.textInputName);
        mTextInputEmail=findViewById(R.id.textInputEmail);
        mTextInputPassword=findViewById(R.id.textInputPassword);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickregister();
            }
        });

    }

    private void clickregister() {
        String name = mTextInputName.getText().toString();
        String email = mTextInputEmail.getText().toString();
        String password = mTextInputPassword.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if (password.length()>=6){
                register(name, email, password);
            }
            else {
                Toast.makeText(this, "La Contraseña debe contener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }

    }


    private void register(String name, String email, String password) {
        mAuthproviders.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    User user = new User(id, name, email);
                    create(user);

                }
                else {
                    Toast.makeText(Registro_Activity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    void create(User user){
        mUserprovider.create(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Registro_Activity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Registro_Activity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


/*
    private void saveUser(String id, String name, String email) {

        User user = new User();

        user.setName(name);
        user.setEmail(email);

        mDatabase.child("Users").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Registro_Activity.this, "Registro realizado con exito", Toast.LENGTH_SHORT).show();

                    Intent intent =new Intent(Registro_Activity.this, Mapa_Activity.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(Registro_Activity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
*/

}