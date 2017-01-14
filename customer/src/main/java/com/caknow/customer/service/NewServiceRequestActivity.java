package com.caknow.customer.service;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.widget.BaseActivity;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.service.fragment.NewServiceDetailsFragment;
import com.caknow.customer.service.fragment.NewServiceListFragment;
import com.caknow.customer.service.fragment.NewServiceLocationFragment;
import com.caknow.customer.util.net.service.location.Geolocation;
import com.caknow.customer.util.net.service.location.ServiceAddress;
import com.caknow.customer.util.net.service.ServiceItem;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class NewServiceRequestActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, NewServiceListFragment.OnListFragmentInteractionListener{

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;


    //// BAD STUFF!
    private String vehicleId;
    Geolocation geolocation;
    ServiceAddress serviceAddress;
    private int typeId;
    private String description;
    ArrayList<String> services;
    String imagePath;
    String imageFilePath;
    File image = null;
    Bitmap bitmap;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 13;

    @Inject
    Cloudinary cloudinary;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        CAKNOWApplication.get().getNetComponent().inject(this);
        services = new ArrayList<>();
        vehicleId = getIntent().getStringExtra("vehicleId");
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_service_request);
        ButterKnife.bind(this);
        addFragment(R.id.flContent,
                new NewServiceLocationFragment(),
                NewServiceLocationFragment.FRAGMENT_TAG);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("New Service");
            ((ImageButton)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(this, "googleApiClient Read", Toast.LENGTH_SHORT);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    private Location getLastLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return null;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {

        }
        return mLastLocation;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getLastLocation();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    public void updateTitle(String titleText, final int drawableId){
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText(titleText);
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(drawableId);
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).invalidate();

        } catch (NullPointerException e){
            //
        }
    }

    @Override
    public void onListFragmentInteraction(ServiceItem item) {
        setServiceId(item.getCatagoryId());
        NewServiceDetailsFragment fragment = new NewServiceDetailsFragment();
        Bundle args = new Bundle();
        args.putString("vehicleId", vehicleId);
        args.putInt("typeId", typeId);
        args.putString("description", item.getName());
        args.putParcelable("address", serviceAddress);
        args.putParcelable("geolocation", geolocation);
        fragment.setArguments(args);
        replaceFragment(R.id.flContent, fragment, NewServiceDetailsFragment.FRAGMENT_TAG, "details");
    }

    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
    ///// Here lies a collection of really shitty public methods to build a service request.
    ///// Hopefully someone will build a proper builder, rx steam, whatever it is to stop this
    ///// madness.
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////

    public void setType(final int typeId){
        this.typeId = typeId;
    }

    public void setServiceId(final String serviceId){
        this.services.add(serviceId);
    }

    public void setServiceDescription(final String description){
       this.description = description;
    }

    public void setServiceAddress(ServiceAddress address){
        this.serviceAddress = address;
    }

    public void setGeolocation(Geolocation geolocation){
        this.geolocation = geolocation;
    }
    public void setServiceType(int type){
        this.typeId = type;
    }

    public Location requestLocation(){
        if(mLastLocation != null){
            return mLastLocation;
        }
        return getLastLocation();
    }

    public ServiceAddress getServiceAddress(){
        return this.serviceAddress;
    }

    public String getVehicleId(){
        return this.vehicleId;
    }

    public String getServiceDescription(){
        return this.description;
    }


    public Geolocation getGeolocation(){
        return this.geolocation;
    }

    public int getServiceType(){
        return this.typeId;
    }

    public ArrayList<String> getServiceId(){
        return this.services;
    }

    public Bitmap getBitmap(){
        return this.bitmap;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            imageFilePath = getPath(selectedImageUri);
            bitmap = BitmapFactory.decodeFile(imagePath);
            Map resultMap;
            try {
                resultMap = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
            } catch(IOException e){
                Toast.makeText(this, "Cloudinary upload failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(projection[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return cursor.getString(column_index);
    }
}
