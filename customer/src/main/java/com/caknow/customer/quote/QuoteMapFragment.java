package com.caknow.customer.quote;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class QuoteMapFragment extends BaseFragment implements
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + QuoteMapFragment.class.getName();

    private MapFragment mapFragment;
    private List<Marker> markerList;
    private GoogleMap mMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quote_map, container, false);
        unbinder = ButterKnife.bind(this, v);

        return v;
    }

    /**
     * Setup map
     *
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Store reference in fragment
        mMap = googleMap;
        // Adding Test data
        markerList.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.035761, -118.3520387))
                .title("$555.55")
        ));
        markerList.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.035761, -118.3520388))
                .title("$555.55")
        ));
        markerList.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.035761, -118.3520389))
                .title("$555.55")
        ));

        mMap.setOnMarkerClickListener(this);

    }

    /**
     * Called when the user clicks a marker.
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null && getActivity() != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(getActivity(),
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}

