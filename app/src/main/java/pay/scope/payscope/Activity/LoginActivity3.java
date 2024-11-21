package pay.scope.payscope.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.IntentSender;
import android.content.SharedPreferences;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;

import com.google.android.gms.tasks.Task;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import pay.scope.payscope.ApiService.LoginService;
import pay.scope.payscope.Model.LoginModel;
import pay.scope.payscope.Model.LoginResponce;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity3 extends AppCompatActivity {
    Button login3;
    TextView signup3, forgot_password3, messageError;
    private EditText usernameEditText, passwordEditText;
    private ImageView imgEye;
    private boolean isPasswordVisible = false;
    private LoginService apiService;
    private LinearLayout LoginEditBox1;
    RelativeLayout LoginEditBox2;
    public static SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    public static final String KEY_USER_NAME = "UserName";
    public static final String KEY_USER_TOKEN = "Token";
    double latitude, longitude;
    private String ipAddress;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final int REQUEST_CHECK_SETTINGS = 4;
    private FusedLocationProviderClient fusedLocationClient;
    private boolean isLocationAvailable = false;
    private static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        login3 = findViewById(R.id.login3);
        signup3 = findViewById(R.id.signup3);
        imgEye = findViewById(R.id.ImgEye);
        messageError = findViewById(R.id.messageError);
        forgot_password3 = findViewById(R.id.forgot_password3);
        usernameEditText = findViewById(R.id.log_username);
        passwordEditText = findViewById(R.id.log_password);
        LoginEditBox1 = findViewById(R.id.LoginEditBox1);
        LoginEditBox2 = findViewById(R.id.LoginEditBox2);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.dismiss();


        retrieveFromSharedPreferences();
        preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        Retrofit retrofit = RetrofitLogin.getClient("https://stage.payscope.in");
        apiService = retrofit.create(LoginService.class);
        ipAddress = getIPAddress(true);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkLocationSettingsAndGetLocation();

        login3.setOnClickListener(v -> {
            if (!isLocationAvailable) {
                Toast.makeText(LoginActivity3.this, "Location services are required for login. Please enable them.", Toast.LENGTH_SHORT).show();
                checkLocationSettingsAndGetLocation();
                return;
            }

            progressDialog.show();
            final String username = usernameEditText.getText().toString().trim();
            final String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() && password.isEmpty()) {
                Toast.makeText(LoginActivity3.this, "Enter your username and password", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            } else if (password.isEmpty()) {
                Toast.makeText(LoginActivity3.this, "Enter your password", Toast.LENGTH_SHORT).show();
                LoginEditBox2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transparent_backgrounderror));
                LoginEditBox1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transparent_background));
                progressDialog.dismiss();
            } else {
                loginUser(username, password, latitude, longitude, ipAddress);
                LoginEditBox1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transparent_background));
                LoginEditBox2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transparent_background));
            }
        });

        signup3.setOnClickListener(v -> startActivity(new Intent(LoginActivity3.this, RegistrationActivity3.class)));

        forgot_password3.setOnClickListener(v -> startActivity(new Intent(LoginActivity3.this, ForgotPasswordActivity.class)));

        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        imgEye.setImageResource(R.drawable.baseline_visibility_off_24);

        imgEye.setOnClickListener(v -> {
            if (isPasswordVisible) {
                passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                imgEye.setImageResource(R.drawable.baseline_visibility_off_24);
            } else {
                passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                imgEye.setImageResource(R.drawable.baseline_remove_red_eye_24);
            }
            isPasswordVisible = !isPasswordVisible;
            passwordEditText.setSelection(passwordEditText.getText().length());
        });
    }

    private void saveLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    private void loginUser(String username, String password, double latitude, double longitude, String ipAddress) {
        LoginModel loginRequest = new LoginModel(username, password, latitude, longitude, ipAddress);
        progressDialog.show();
        apiService.createLogin(loginRequest).enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponce> call, @NonNull Response<LoginResponce> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    LoginResponce loginResponse = response.body();
                    if (loginResponse != null && loginResponse.isStatus()) {
                        Log.d("LoginActivity3", "isSuccessful: " + response.isSuccessful());
                        saveToSharedPreferences(loginRequest.getUsername(), loginResponse.getToken());
                        Log.d("token", "Username: " + loginRequest.getUsername() + " Token: " + loginResponse.getToken());

//                        Toast.makeText(LoginActivity3.this, "Username: " + username + "password: " + password + "latitude: " + latitude + "longitude: " + longitude + "ipAddress: " + ipAddress, Toast.LENGTH_SHORT).show();

                        saveLoginStatus();

                        String otp_verification = loginResponse.getOtp_verifiaction();

                        if (otp_verification != null) {
                            switch (otp_verification) {
                                case "true":
                                    Toast.makeText(LoginActivity3.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(LoginActivity3.this, loginResponse.getOtp_verifiaction(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity3.this, ValidationNumberActivity.class));
                                    finish();
                                    break;
                                case "false":
                                    Toast.makeText(LoginActivity3.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(LoginActivity3.this, loginResponse.getOtp_verifiaction(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity3.this, MainActivity.class));
                                    finish();
                                    break;
                                default:
                                    Toast.makeText(LoginActivity3.this, loginResponse.getOtp_verifiaction(), Toast.LENGTH_SHORT).show();
                                    // Optionally handle unexpected values or errors here
                                    break;
                            }
                        }

                    } else {
                        LoginEditBox2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.transparent_backgrounderror));
                        messageError.setText(loginResponse != null ? loginResponse.getMessage() : "Unknown error");
                    }
                } else {
                    messageError.setText(response.message());
                    Log.d("LoginActivity3", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponce> call, @NonNull Throwable t) {
                Log.d("LoginActivity3", "Network Error: " + t);
                progressDialog.dismiss();
            }
        });
    }

    private void saveToSharedPreferences(String username, String token) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_NAME, username);
        editor.putString(KEY_USER_TOKEN, token);
        editor.apply();
    }

    private void retrieveFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_USER_NAME, "No username");
        String token = sharedPreferences.getString(KEY_USER_TOKEN, "No token");
        Log.d("SharedPreferences", "Username: " + username);
        Log.d("SharedPreferences", "Token: " + token);
    }


    public String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        ipAddress = addr.getHostAddress();
                        boolean isIPv4 = ipAddress.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4) {
                                return ipAddress;
                            }
                        } else {
                            if (!isIPv4) {
                                int delim = ipAddress.indexOf('%');
                                return delim < 0 ? ipAddress : ipAddress.substring(0, delim);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", "Error getting IP address", ex);
        }
        return null;
    }

    private void checkLocationSettingsAndGetLocation() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, locationSettingsResponse -> requestLocationUpdates());
        task.addOnFailureListener(this, e -> {
            if (e instanceof ResolvableApiException) {
                try {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(LoginActivity3.this, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException sendEx) {
                    Log.e("LocationSettings", "Failed to resolve location settings", sendEx);
                }
            }
        });
    }

    private void requestLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    Location location = locationResult.getLastLocation();
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        isLocationAvailable = true;
                    } else {
                        Log.e("Location", "Location is null");
                        isLocationAvailable = false;
                    }
                }
            }, null);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
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
}
