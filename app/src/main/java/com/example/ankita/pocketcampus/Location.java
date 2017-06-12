package com.example.ankita.pocketcampus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(37.349171,-121.939124) ,
                18.0f) );
        Marker m1 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.347648,-121.939387))
                .anchor(0.5f, 0.5f)
                .title("Benson")
                .snippet("SCU")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.benson)));
        Marker m2 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.348330,-121.938100))
                .anchor(0.5f, 0.5f)
                .title("Library")
                .snippet("SCU")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.library)));

        Marker m3 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.348411,-121.939913))
                .anchor(0.5f, 0.5f)
                .title("Kenna Hall")
                .snippet("SCU")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.kenna)));

        Marker m4 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.349234,-121.938137))
                .anchor(0.5f, 0.5f)
                .title("Bannan Engineering")
                .snippet("SCU")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bannan)));

        Marker m5 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.348206,-121.941539))
                .anchor(0.5f, 0.5f)
                .title("ISS-Varsi Hall")
                .snippet("SCU")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.varsi)));

        Marker m6 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.349886,-121.938852))
                .anchor(0.5f, 0.5f)
                .title("Admission & Enrollment")
                .snippet("SCU")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.admission)));

        Marker m7 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.350647,-121.934215))
                .anchor(0.5f, 0.5f)
                .title("Guadalupe Hall")
                .snippet("SCU")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.gaudalupe)));

        Marker m8 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.350581,-121.940503))
                .anchor(0.5f, 0.5f)
                .title("Daly Science")
                .snippet("SCU")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.daly)));

        Marker m9 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.349042,-121.939552))
                .anchor(0.5f, 0.5f)
                .title("SCU Law School")
                .snippet("SCU")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.law)));


    }
}
