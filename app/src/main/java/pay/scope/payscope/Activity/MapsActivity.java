package pay.scope.payscope.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import pay.scope.payscope.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final int REQUEST_CHECK_SETTINGS = 4;
    private FusedLocationProviderClient fusedLocationClient;
    private double latitude, longitude;
    private boolean isMapReady = false;
    private TextView locationTextView;
    public MaterialToolbar mapToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationTextView = findViewById(R.id.locationTextView);
        mapToolbar = findViewById(R.id.mapToolbar);

        mapToolbar.setNavigationOnClickListener(v -> finish());



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkLocationSettingsAndGetLocation();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    private void checkLocationSettingsAndGetLocation() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, locationSettingsResponse -> requestLocationUpdates());

        task.addOnFailureListener(this, e -> {
            if (e instanceof ResolvableApiException) {
                try {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(MapsActivity.this, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException sendEx) {
                    // Ignore the error.
                }
            } else {
                moveToNextActivity();
            }
        });
    }

    private void requestLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    Location location = locationResult.getLastLocation();
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        Log.d("Address", "Latitude: " + latitude + " Longitude: " + longitude);
                        if (isMapReady) {
                            updateMapLocation();
                        }
                        locationTextView.setText("Location: Latitude: " + latitude + ", Longitude: " + longitude);

                        Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                            if (addresses != null && !addresses.isEmpty()) {
                                Address address = addresses.get(0);
                                String addressString = address.getAddressLine(0);

                                locationTextView.setText("Address:" + addressString);

//                                locationTextView.setText("Latitude: " + latitude + "\nLongitude: " + longitude + "\nAddress: " + addressString);
//                                Log.d("showLocation", "Latitude: " + latitude + "\nLongitude: " + longitude + "\nAddress: " + addressString);
                            } else {
                                locationTextView.setText("Latitude: " + latitude + "\nLongitude: " + longitude + "\nAddress not found");
//                                Log.d("showLocation", "Latitude: " + latitude + "\nLongitude: " + longitude + "\nAddress not found");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
//                            locationTextView.setText("Latitude: " + latitude + "\nLongitude: " + longitude + "\nUnable to get address");
//                            Log.d("showLocation", "Latitude: " + latitude + "\nLongitude: " + longitude + "\nUnable to get address");
                        }
                    } else {
                        Log.e("Address", "Location is null");
                    }
                }
            }, null);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    private void updateMapLocation() {
        LatLng currentLocation = new LatLng(latitude, longitude);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14f));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                requestLocationUpdates();
            } else {
                Toast.makeText(this, "Location services are not enabled.", Toast.LENGTH_SHORT).show();
                moveToNextActivity();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
                moveToNextActivity();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        isMapReady = true;

        if (latitude != 0 && longitude != 0) {
            updateMapLocation();
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void moveToNextActivity() {
        Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
        startActivity(intent);
        finish();
    }
}







/*
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import pay.scope.payscope.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final int REQUEST_CHECK_SETTINGS = 4;
    private FusedLocationProviderClient fusedLocationClient;
    private double latitude, longitude;
    private boolean isMapReady = false;
    private TextView locationTextView;
    public MaterialToolbar mapToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationTextView = findViewById(R.id.locationTextView);
        mapToolbar = findViewById(R.id.mapToolbar);

        mapToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkLocationSettingsAndGetLocation();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    private void checkLocationSettingsAndGetLocation() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, locationSettingsResponse -> requestLocationUpdates());

        task.addOnFailureListener(this, e -> {
            if (e instanceof ResolvableApiException) {
                try {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(MapsActivity.this, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException sendEx) {
                    // Ignore the error.
                }
            }
        });
    }

    private void requestLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    Location location = locationResult.getLastLocation();
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

//                        Log.d("Address", "Latitude: " + latitude + " Longitude: " + longitude);
                        if (isMapReady) {
                            updateMapLocation();
                        }
//                        locationTextView.setText("Location: Latitude: " + latitude + ", Longitude: " + longitude);


                        Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                            if (addresses != null && !addresses.isEmpty()) {
                                Address address = addresses.get(0);
                                String addressString = address.getAddressLine(0);
//                                locationTextView.setText("Latitude: " + latitude + "\nLongitude: " + longitude + "\nAddress: " + addressString);
                                locationTextView.setText("Address: " + addressString);
//                                Log.d("showLocation", "Latitude: " + latitude + "\nLongitude: " + longitude + "\nAddress: " + addressString);
                            } else {
                                locationTextView.setText("Latitude: " + latitude + "\nLongitude: " + longitude + "\nAddress not found");
//                                Log.d("showLocation", "Latitude: " + latitude + "\nLongitude: " + longitude + "\nAddress not found");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            locationTextView.setText("Latitude: " + latitude + "\nLongitude: " + longitude + "\nUnable to get address");
//                            Log.d("showLocation", "Latitude: " + latitude + "\nLongitude: " + longitude + "\nUnable to get address");
                        }


                    } else {
                        Log.e("Address", "Location is null");
                    }
                }
            }, null);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    private void updateMapLocation() {
        LatLng currentLocation = new LatLng(latitude, longitude);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14f));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == RESULT_OK) {
                requestLocationUpdates();
            } else {
                Toast.makeText(this, "Location services are not enabled.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        isMapReady = true;

        if (latitude != 0 && longitude != 0) {
            updateMapLocation();

        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}

 */