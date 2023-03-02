package com.coder.elaundry_apps.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import com.coder.elaundry_apps.R;
import com.coder.elaundry_apps.api.Constants;
import com.coder.elaundry_apps.databinding.ActivityMapsUserBinding;
import com.coder.elaundry_apps.model.TitikOrder;
import com.coder.elaundry_apps.utils.HelperUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivityUser extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsUserBinding binding;
    private TitikOrder titikOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                return null;
            }

            @Nullable
            @Override
            public View getInfoWindow(@NonNull Marker marker) {

                Context context = getApplicationContext(); //or getActivity(), YourActivity.this, etc.

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(context);
                snippet.setTextColor(Color.RED);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
        Intent iin= getIntent();
        Bundle extra = iin.getExtras();
        if(extra != null) {
            String xlatitude = extra.getString("latitude","");
            String xlongitude = extra.getString("longitude","");
            Double  clatitude= Double.parseDouble(xlatitude);
            Double clongitude = Double.parseDouble(xlongitude);
//            this.MapSetup(clatitude,clongitude,mMap);
//            HelperUtils.pesan(getApplicationContext(),String.valueOf(clatitude));
//            LatLng sydney = new LatLng(clatitude, clongitude);
//            mMap.addMarker(new MarkerOptions().position(sydney).title("Laundry"));


            LatLng customer = new LatLng(clatitude,clongitude);
            mMap.addMarker(new MarkerOptions().position(customer).title("Penjemputan"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(customer));
            SharedPreferences sharedPreferences = getApplication().getSharedPreferences(
                    Constants.KEY_USER, Context.MODE_PRIVATE);
            String latitude = sharedPreferences.getString("latitude", "");
            String longitude = sharedPreferences.getString("longitude", "");
            Double  blatitude= Double.parseDouble(latitude);
            Double blongitude = Double.parseDouble(longitude);

            LatLng laundry = new LatLng(blatitude,blongitude);
            mMap.addMarker(new MarkerOptions().position(laundry).title("Laundry"));

            PolylineOptions polylineOptions = new PolylineOptions()
                    .add(new LatLng(clatitude, clongitude))//point A
                    .add(new LatLng(blatitude, blongitude)); // Point B.

            Polyline polyline = mMap.addPolyline(polylineOptions);
        }

    }

    private void MapSetup(Double clatitude,Double clongitude,GoogleMap mMap){



    }
}