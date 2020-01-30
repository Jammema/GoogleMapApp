package com.example.googlemapapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng Jonota_tower = new LatLng(23.7540926, 90.3910788);
        mMap.addMarker(new MarkerOptions().position(Jonota_tower).title("Marker in Janata Tower"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jonota_tower, 16));

        mapBackgroundDesignChange(mMap);
        locationPermissionRequest();
    }

    private void locationPermissionRequest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==1){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "PERMISSION IS GRANTED", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Permission is denied ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void mapBackgroundDesignChange(GoogleMap mMap) {

    }

    private void mapBackkgroundChange(GoogleMap googleMap) {
        try {
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.changemapdesignapi));
            if (!success) {
                Log.e("m", "Style parsing failed.");
            }

        } catch (Resources.NotFoundException e) {
            Log.e("MainActivity", "Can't find style. Error:", e);
        }


    }
}
