package com.polinema.meme;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnSuccessListener;

public class LokasiActivity extends AppCompatActivity implements DapatkanAlamatTask.onTaskSelesai{


    private static final int REQUEST_LOCATION_PERMISSON = 1;
    private static final int GOOGLE_API_CLIENT_ID = 0; //pendeklarasian variabel
    private GoogleApiClient googleApiClient;
    private static final int REQUEST_PICK_PLACE =1;
    private static final int REQUEST_OK = 1;

    private static String NAME_PLACE ="" ;
    private static String ADDRESS_PLACE = "";
    private static int IMG_PLACE=-1;

    private Button mLocationButton;
    private Button mPilihButton;

    private TextView mLocationTextView;
    private Location mLastLocation;
    private FusedLocationProviderClient mFusedLocationClient;

    private ImageView mAndroidImageView;
    private AnimatorSet mRotateAnim;
    private boolean mTrackingLocation;
    private LocationCallback mLocationCall;

    private PlaceDetectionClient mPlacesDetCli;
    private String mLastPlacesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);


        mPilihButton = (Button) findViewById(R.id.buttonPilih);

        mLocationButton = (Button) findViewById(R.id.buttonLocation);
        mLocationTextView = (TextView) findViewById(R.id.textViewLocation);
        mAndroidImageView= (ImageView) findViewById(R.id.img_android);

        mRotateAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.rotate);
        mRotateAnim.setTarget(mAndroidImageView);

        mPlacesDetCli = Places.getPlaceDetectionClient(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTrackingLocation)
                {
                    mulaiTrackingLokasi();
                }
                else
                {
                    stopTracking();
                }
//                getLocations(); iki modul 2 dan 1,2
            }
        });

        mLocationCall = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult){
                if (mTrackingLocation)
                {
                    new DapatkanAlamatTask( LokasiActivity.this,
                            LokasiActivity.this).execute(locationResult.getLastLocation());
                }
            }
        };
        mPilihButton.setOnClickListener(new View.OnClickListener() { //percobaan 10.4 untuk menambahkan button baru pilih lokasi
            @Override
            public void onClick(View v) {
//                pilihLokasi();
            }
        });
    }

    private void getLocations() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSON);
        }
        else {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            TextView mLocationTextView = (TextView) findViewById(R.id.textViewLocation);
                            if (location != null){

                                new DapatkanAlamatTask(LokasiActivity.this, LokasiActivity.this).execute(location);

                            }
                            else {
                                mLocationTextView.setText("Lokasi Tidak tersedia");
                            }
                        }
                    }
            );
        }
        mLocationTextView.setText(getString(R.string.alamat_text,"sedang mencari alamat",System.currentTimeMillis()));
    }

    private void mulaiTrackingLokasi()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSON);
        }
        else {
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(),mLocationCall,null);
            mLocationTextView.setText(getString(R.string.alamat_text,"sedang mencari alamat cinta",System.currentTimeMillis()));
            mTrackingLocation = true;
            mLocationButton.setText("Berhenti tracking syank");
            mRotateAnim.start();
        }

    }
    private void stopTracking()
    {
        if (mTrackingLocation)
        {

            mTrackingLocation = false;
            mFusedLocationClient.removeLocationUpdates(mLocationCall);
            mLocationButton.setText("Mulai mencari serpihan hati yang hilang");
            mLocationTextView.setText("Pencari cinta sedng berhenti");
            mRotateAnim.end();

        }
    }
    private LocationRequest getLocationRequest()
    {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult){
        switch (requestCode){
            case REQUEST_LOCATION_PERMISSON:

                if(grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED)
                {
                    getLocations();
                }
                else {
                    Toast.makeText(this, "permisinya maaf ditolak ", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onTaskCompleted(String result) throws SecurityException {
        if (mTrackingLocation){
            mLocationTextView.setText(getString(R.string.alamat_text,result, System.currentTimeMillis()));
        }
//        PendingResult<PlaceLikelihoodBuffer> placeResult = Places.PlaceDetectionApi.getCurrentPlace(googleApiClient, null); //nafi
//        placeResult.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
//            @Override
//            public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {
//                for (PlaceLikelihood placeLikelihood : placeLikelihoods) {
//                    mLocationTextView.setText(getString(R.string.alamat_text,
//                            placeLikelihood.getPlace().getName(),
//                            placeLikelihood.getPlace().getAddress(),
//                            System.currentTimeMillis()));//mengupdate text nama lokasi dengan data yang didapatkan
//                    NAME_PLACE = placeLikelihood.getPlace().getName().toString();// masukan data data tersebut kedalam statid variabel untuk di saveinstance agar tidak hilang
//                    ADDRESS_PLACE = placeLikelihood.getPlace().getAddress().toString();// masukan data data tersebut kedalam statid variabel untuk di saveinstance agar tidak hilang
//                    IMG_PLACE = setTipeLokasi(placeLikelihood.getPlace());// masukan data data tersebut kedalam statid variabel untuk di saveinstance agar tidak hilang
//                    mAndroidImageView.setImageResource(IMG_PLACE);
//                }
//                placeLikelihoods.release();
//            }
//        });
    }

//    private int setTipeLokasi(Place currentPlace) {
//        int drawableID = - 1;
//        for (Integer placeType: currentPlace.getPlaceTypes()) //jenis lokasi yang terdeteksi termasuk kampus, kopi atau toko dll
//        {
//            switch (placeType)
//            {
//                case Place.TYPE_UNIVERSITY:  //type lokasi
//                    drawableID = R.drawable.kampus;
//                    break;
//                case Place.TYPE_CAFE:
//                    drawableID = R.drawable.warkop;
//                    break;
//                case Place.TYPE_SHOPPING_MALL:
//                    drawableID = R.drawable.toko;
//                    break;
//                case Place.TYPE_MOVIE_THEATER:
//                    drawableID = R.drawable.bioskop;
//                    break;
//                case Place.TYPE_ATM:
//                    drawableID = R.drawable.uang;
//                    break;
//                case Place.TYPE_FOOD:
//                    drawableID = R.drawable.makan;
//                    break;
//                case Place.TYPE_LAUNDRY:
//                    drawableID = R.drawable.baju;
//                    break;
//                case Place.TYPE_HOSPITAL:
//                    drawableID = R.drawable.hos;
//                    break;
//            }
//        }
//        if (drawableID < 0){
//            drawableID = R.drawable.unkw;
//        }
//        return drawableID;
//    }

//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){ //percobaan 10.4
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_OK)
//        {
//            Place place = PlacePicker.getPlace(this, data);
//            setTipeLokasi(place);
//            mLocationTextView.setText(
//                    getString(R.string.alamat_text,
//                            place.getName(),
//                            place.getAddress(),
//                            System.currentTimeMillis()));
//            NAME_PLACE = place.getName().toString(); //ambil nama tempat
//            ADDRESS_PLACE = place.getAddress().toString(); //ambil alamat
//            IMG_PLACE = setTipeLokasi(place);
//            mAndroidImageView.setImageResource(IMG_PLACE); //gamabr
//
//        }
//        else
//        {
//            mLocationTextView.setText("Lokasi kenapa gk dipilih broohh");
//        }
//    }

//    private void getadress() {
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
//        {
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSON);
//        }
//        else
//        {
//            Log.d("GET_PERMISSION","getlocation: permission are granted");
//            mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location) {
//                    if (location != null)
//                    {
//                        new DapatkanAlamatTask (LokasiActivity.this,LokasiActivity.this).execute(location);
//                        Log.d("GET_PERMISSION","founded");
//                    }
//                    else
//                    {
//                        mLocationTextView.setText("lokasi tidak tersedia");
//                    }
//                }
//            });
//        }
//        mLocationTextView.setText("MENCARI Lokasimu");
//    }
//
//    private void pilihLokasi() { //method ini berfungsi ketika action pilih lokasi diclick
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//        try {
//            startActivityForResult(builder.build(LokasiActivity.this),REQUEST_PICK_PLACE);
//        }
//        catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e){
//            e.printStackTrace();
//        }
//    }
//    @Override
//    protected void onSaveInstanceState(Bundle savedInstanceState) { //function digunakan agar data dari alamat sebelumnya disimpan ke dalaman Save Instance State
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putString("placeName",NAME_PLACE);
//        savedInstanceState.putString("placeAddress",ADDRESS_PLACE);
//        savedInstanceState.putInt("placeImage",IMG_PLACE);
//
//    }
}
