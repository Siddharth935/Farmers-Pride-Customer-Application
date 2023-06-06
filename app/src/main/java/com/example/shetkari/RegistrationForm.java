package com.example.shetkari;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.shetkari.Model.RegisterModel;
import com.example.shetkari.databinding.ActivityRegistrationFormBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public class RegistrationForm extends AppCompatActivity implements LocationListener {
    private LocationManager locationManager;
    private ActivityRegistrationFormBinding binding;
    private Uri filepath;
    private Bitmap bitmap;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase rootNode;
    private DatabaseReference referenceTwo;
    private final int REQUEST_CODE = 207;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //new add
        FirebaseDatabase.getInstance().goOnline();
        //get location
        locationEnabled();
        getLocation();
        //share preference
        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = sharedPreferences.getString("FirstTimeInstall", "");

        if (FirstTime.equals("Yes")) {
            Intent intent = new Intent(RegistrationForm.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }


        binding.selectCropImage.setOnClickListener(v -> Dexter.withActivity(RegistrationForm.this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image File"), REQUEST_CODE);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check());

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.customerName.getEditableText().toString().isEmpty()) {
                    Toast.makeText(RegistrationForm.this, "Enter Your Crop Name", Toast.LENGTH_SHORT).show();
                } else if (binding.customerMobileNumber.getEditableText().toString().isEmpty()) {
                    Toast.makeText(RegistrationForm.this, "Enter Your MobileNumber", Toast.LENGTH_SHORT).show();
                } else if (binding.customerMobileNumberTwo.getEditableText().toString().isEmpty() || binding.customerEmail.getEditableText().toString().isEmpty()) {

                    upload();
                } else {
                    upload();

                }
            }
        });
    }

    private void nextActivity() {
        Intent intent = new Intent(RegistrationForm.this, MainActivity.class);
        startActivity(intent);
    }

    private void upload() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();

        String customerName = binding.customerName.getEditableText().toString();
        String customerMobileNumber = binding.customerMobileNumber.getEditableText().toString();
        String customerMobileNumberTwo = binding.customerMobileNumberTwo.getEditableText().toString();
        String customerEmail = binding.customerEmail.getEditableText().toString();


        String state = binding.State.getText().toString();
        String country = binding.Country.getText().toString();
        String addresss = binding.addresss.getText().toString();
        String pincode = binding.PinCode.getText().toString();
        String city = binding.City.getText().toString();


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("CustomerProfile").child("" + customerMobileNumber);


        storageReference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        rootNode = FirebaseDatabase.getInstance();
                        referenceTwo = rootNode.getReference("CustomerProfile");
                        RegisterModel registerModel = new RegisterModel(customerName, customerMobileNumber, customerMobileNumberTwo, customerEmail, state, country, addresss, pincode, city, uri.toString());
                        Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        referenceTwo.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(registerModel);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int percentage = (int) ((100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount());
                dialog.setMessage("Uploading " + (int) percentage + " %...");
                if (percentage == 100) {
                    dialog.dismiss();
                    nextActivity();
                    Toast.makeText(RegistrationForm.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(RegistrationForm.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            binding.PinCode.setText(addresses.get(0).getPostalCode());
            binding.City.setText(addresses.get(0).getLocality());
            binding.State.setText(addresses.get(0).getAdminArea());
            binding.Country.setText(addresses.get(0).getCountryName());
            binding.addresss.setText(addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                binding.getUserImage.setImageBitmap(bitmap);
            } catch (Exception e) {
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}