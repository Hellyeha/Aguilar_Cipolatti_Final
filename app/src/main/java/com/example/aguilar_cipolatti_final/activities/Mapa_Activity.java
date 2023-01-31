package com.example.aguilar_cipolatti_final.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aguilar_cipolatti_final.R;
import com.example.aguilar_cipolatti_final.providers.AuthProviders;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class Mapa_Activity extends AppCompatActivity implements OnMapReadyCallback {


    Button mButtonLogout;
    AuthProviders mAuthProviders;

    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);



        mButtonLogout=findViewById(R.id.btnlogout);

        mAuthProviders=new AuthProviders();

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuthProviders.logout();
                Intent intent = new Intent(Mapa_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap=googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
}