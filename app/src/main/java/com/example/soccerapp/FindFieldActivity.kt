package com.example.soccerapp

import com.example.soccerapp.BuildConfig;
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.lang.reflect.Modifier

class FindFieldActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var mMap: GoogleMap
    lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.find_field_activity)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val singapore = LatLng(1.35, 103.07)
        mMap.addMarker(MarkerOptions().position(singapore).title("Marker in Singapore"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(singapore))
    }

}
