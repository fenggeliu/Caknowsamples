package com.caknow.customer.service;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.garage.NewVehicleActivity;
import com.caknow.customer.service.fragment.ServiceDetailsFragment;
import com.caknow.customer.service.fragment.ServiceListFragment;
import com.caknow.customer.service.fragment.ServiceLocationFragment;
import com.caknow.customer.util.net.service.Address;
import com.caknow.customer.util.net.service.Geolocation;
import com.caknow.customer.util.net.service.Services;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class NewServiceRequestActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ServiceListFragment.OnListFragmentInteractionListener{

    private String vehicleId;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Geolocation geolocation;
    Address serviceAddress;
    private int typeId;
    String[] services;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 13;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        services = new String[1];
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
                new ServiceLocationFragment(),
                ServiceLocationFragment.FRAGMENT_TAG);
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

    public Location requestLocation(){
        if(mLastLocation != null){
            return mLastLocation;
        }
       return getLastLocation();
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
    public void onListFragmentInteraction(Services item) {
        services[0] = item.getCatagoryId();
        ServiceDetailsFragment fragment = new ServiceDetailsFragment();
        Bundle args = new Bundle();
        args.putString("vehicleId", vehicleId);
        args.putInt("typeId", typeId);
        args.putString("description", item.getName());
        args.putParcelable("address", serviceAddress);
        args.putParcelable("geolocation", geolocation);
        fragment.setArguments(args);
        replaceFragment(R.id.flContent, fragment, ServiceDetailsFragment.FRAGMENT_TAG, "details");
    }

    public void setType(final int typeId){
        this.typeId = typeId;
    }


}
