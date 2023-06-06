package com.example.shetkari.ProfileActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.shetkari.R;
import com.example.shetkari.databinding.ActivityEditProfileBinding;
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

public class EditProfile extends AppCompatActivity implements LocationListener {
    private ActivityEditProfileBinding binding;
    private LocationManager locationManager;
    private Uri filepath;
    private Bitmap bitmap;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase rootNode;
    private DatabaseReference referenceTwo;
    private boolean isPermissonGranted;
    private final int REQUEST_CODE = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        loactiononOff();
        checkPermission();
        getLocation();


        binding.selectCropImage.setOnClickListener(v -> Dexter.withActivity(EditProfile.this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
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
                    Toast.makeText(EditProfile.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
                } else if (binding.customerMobileNumber.getEditableText().toString().isEmpty()) {
                    Toast.makeText(EditProfile.this, "Enter Your MobileNumber", Toast.LENGTH_SHORT).show();
                } else if (binding.customerMobileNumberTwo.getEditableText().toString().isEmpty() || binding.customerEmail.getEditableText().toString().isEmpty()) {
                    upload();
                } else {
                    upload();

                }
            }
        });
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
        storageReference = storage.getReference("UserProfile").child("" + customerMobileNumber);


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
                    Toast.makeText(EditProfile.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    private void loactiononOff() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            Toast.makeText(EditProfile.this, "On", Toast.LENGTH_SHORT).show();
        }else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("Location is off. Please Enable location")
                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    });

            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog1.show();
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
//            assert data != null;
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

    private void checkPermission() {
        Dexter.withContext(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                isPermissonGranted = true;
//                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }
        }).check();
    }
}