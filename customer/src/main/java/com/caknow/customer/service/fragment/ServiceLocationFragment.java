package com.caknow.customer.service.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.service.NewServiceRequestActivity;
import com.caknow.customer.service.model.LocationItem;

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

    @OnClick(R.id.service_location_find_me_button)
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
                Toast.makeText(getActivity(), "Unable to determine address", Toast.LENGTH_SHORT);
            }
        }
    }



    @OnClick(R.id.service_location_next_button)
    void startServiceSelection(){
        NewServiceRequestActivity activity = (NewServiceRequestActivity) getActivity();
        ServiceSelectFragment fragment = new ServiceSelectFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(LocationItem.PARCELABLE_KEY, new LocationItem("address1", "address2", "city", "90035"));
        fragment.setArguments(bundle);
        activity.replaceFragment(R.id.flContent, fragment, ServiceSelectFragment.FRAGMENT_TAG, "service_type");
    }

    @BindView(R.id.service_location_find_me_button)
    TextView findBeButton;

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




}
