package com.caknow.customer.service.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.service.NewServiceRequestActivity;
import com.caknow.customer.service.model.LocationItem;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.service.ServiceAddress;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by junu on 1/1/17.
 */

public class ServiceLocationFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ServiceLocationFragment.class.getName();

    Geocoder geocoder;

    @BindViews({ R.id.service_location_address_1, R.id.service_location_address_2, R.id.service_location_city, R.id.service_location_zip })
    List<EditText> nameViews;

    @BindView(R.id.service_location_next_button)
    Button continueButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((NewServiceRequestActivity)getActivity()).updateTitle("Location", R.drawable.ic_action_back);
    }

    @OnClick(R.id.service_location_next_button)
    void startServiceSelection(){
        if(validate()) {
            NewServiceRequestActivity activity = (NewServiceRequestActivity) getActivity();
            NewServiceFragment fragment = new NewServiceFragment();
            final Bundle bundle = new Bundle();
            ServiceAddress address = new ServiceAddress();
            address.setLineOne(nameViews.get(0).getText().toString());
            address.setLineTwo(nameViews.get(1).getText().toString());
            address.setCity(nameViews.get(2).getText().toString());
            address.setPostalCode(nameViews.get(3).getText().toString());
            ((NewServiceRequestActivity)getActivity()).setServiceAddress(address);
            fragment.setArguments(bundle);
            activity.replaceFragment(R.id.flContent, fragment, NewServiceFragment.FRAGMENT_TAG, "service_type");
        } else{
            Toast.makeText(getContext(), "Please check address again.", Toast.LENGTH_SHORT);
        }
    }

    boolean validate(){

        return !nameViews.get(0).getText().toString().isEmpty() && !nameViews.get(2).getText().toString().isEmpty() && !nameViews.get(3).getText().toString().isEmpty();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_location, container, false);
        unbinder = ButterKnife.bind(this, v);
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            geocoder.getFromLocationName("10900 Wilshire Blvd Los Angeles, CA 90035", 1);
        } catch(IOException io){
            //
        }

        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.service_location, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_locate_me) {
            checkLocation();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    void checkLocation(){
        List<Address> addresses;

        NewServiceRequestActivity activity = (NewServiceRequestActivity) getActivity();
        if(activity != null && !activity.isFinishing()) {
            try {
                Location location = activity.requestLocation();
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses != null) {
                    nameViews.get(0).setText(addresses.get(0).getAddressLine(0));
                    nameViews.get(2).setText(addresses.get(0).getLocality());
                    nameViews.get(3).setText(addresses.get(0).getPostalCode());
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Device GeoCoder is acting weird. Please try restarting your device for best results.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
