package com.example.myapplication;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.location.FusedLocationProviderClient;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Button;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.text.format.Formatter;

import android.net.wifi.WifiManager;

public class MainPage extends Fragment {

    Button btLocation;
    FusedLocationProviderClient client;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //Linking layout to java file
        View fFLayout = inflater.inflate(R.layout.main_pages, container, false);

        btLocation = fFLayout.findViewById(R.id.bt_location);

        client = LocationServices.getFusedLocationProviderClient(requireActivity());



        return fFLayout;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if((requestCode==100) && (grantResults.length>0)
        && (grantResults[0]+ grantResults[1] == PackageManager.PERMISSION_GRANTED))
        {
            getCurrentLocation();
        }
        else
        {
            Toast.makeText(getActivity(),"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();

                    if (location != null) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference loc= database.getReference("Location");
                        loc.child("Latitude").setValue(String.valueOf(location.getLatitude()));
                        loc.child("Longitude").setValue(String.valueOf(location.getLongitude()));
                    }
                    else {
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {

                                Location location1 = locationResult.getLastLocation();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference loc= database.getReference("Location");
                                loc.child("Latitude").setValue(String.valueOf(location1.getLatitude()));
                                loc.child("Longitude").setValue(String.valueOf(location1.getLongitude()));
                            }
                        };
                        client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

                    }
                }
            });
        }
        else
        {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }
    private void getIPAddress()
    {
        WifiManager wifiManager=(WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        String ipaddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ipadd= database.getReference("IP Address");
        ipadd.setValue(ipaddress);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.bt_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIPAddress();

                if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                }
                else
                {
                    ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},100);
                }
                NavHostFragment.findNavController(MainPage.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}
