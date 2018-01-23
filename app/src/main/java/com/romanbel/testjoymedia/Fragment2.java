package com.romanbel.testjoymedia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.romanbel.testjoymedia.model.Entity;
import com.romanbel.testjoymedia.model.Shop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by roman on 20.01.18.
 */

public class Fragment2 extends Fragment  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Double  mLatitude = 55.75370903771494,
            mLongitude = 37.61981338262558;
    public static final int REQUEST_CAMERA_PERMISSION_RESULT = 123;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, null);

        buildGoogleApiClient();

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        } else {
            Toast.makeText(getActivity(), "Not connected...", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "Not connected");
        }


        Entity entity = readJson();

        List<Shop> list = entity.getShop();
        for(Shop shop: list){
            shop.setDistance(calculateDistance(mLatitude, mLongitude, Double.parseDouble(shop.getLatitude()), Double.parseDouble(shop.getLongitude())).intValue());
        }
        Collections.sort(list);

        RecyclerAdapter mRecyclerAdapter = new RecyclerAdapter(list, getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mRecyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, REQUEST_CAMERA_PERMISSION_RESULT);
        }

        return view;
    }

    private Entity readJson(){
        String json = null;
        Entity entity = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("shops.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            entity = gson.fromJson(json, Entity.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLatitudeLongitude();

    }

    private void getLatitudeLongitude(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }

        if (mLastLocation != null) {
            mLatitude = mLastLocation.getLatitude();
            mLongitude = mLastLocation.getLongitude();
        } else {
            Log.d("TAG", "mLastLocation = null");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("TAG", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("TAG", "onConnectionFailed");
    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION_RESULT) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(),
                        "Будут применены координаты центра Москвы!", Toast.LENGTH_SHORT).show();
            } else {
                getLatitudeLongitude();
            }
        }
    }

    private Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2){

        Double R = 6378.137; // Radius of earth in KM
        Double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        Double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        Double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double d = R * c;
        return d * 1000; // meters
    }
}
