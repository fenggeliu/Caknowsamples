package com.caknow.customer.quote;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.quote.adapter.HeaderAdapter;
import com.caknow.customer.transaction.TransactionActivity;
import com.caknow.customer.transaction.TransactionDetailsAdapter;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.service.quotes.Quote;
import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.caknow.customer.util.net.service.quotes.ServiceList;
import com.caknow.customer.widget.LockableListView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.CustomSlidingUpPanelLayout;

import java.util.ArrayList;


/**
 * Fragment that contains the map and sliding panel layout to view quote information.
 */
public class QuoteMapFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        CustomSlidingUpPanelLayout.PanelSlideListener, LocationListener, HeaderAdapter.ItemClickListener, OnMapReadyCallback {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + QuoteMapFragment.class.getName();

    private static final String ARG_LOCATION = "arg.location";

     private LockableListView mListView;
    private CustomSlidingUpPanelLayout mSlidingUpPanelLayout;
    QuoteList currentQuote;
    private String serviceRequestId;
    // ListView stuff
    private View mTransparentHeaderView;
    private View mSpaceView;
    private View mTransparentView;
    private View mWhiteSpaceView;
    private HeaderAdapter mHeaderAdapter;
    private LatLng mLocation;
    private Marker mLocationMarker;
    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    private boolean mIsNeedLocationUpdate = true;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private ArrayList<QuoteList> quoteList;
    private ArrayList<ServiceList> serviceList;
    private QuoteDetailListAdapter adapter;
    private Point displaySize;
    private FrameLayout acceptQuoteButton;
    public QuoteMapFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        displaySize = new Point();
        display.getSize(displaySize);

        try{
            Bundle args = getArguments();
            quoteList = args.getParcelableArrayList(Constants.QUOTE_LIST_ID_PARCEL_KEY);
            serviceList = args.getParcelableArrayList(Constants.SERVICE_LIST_PARCEL_KEY);
            serviceRequestId = args.getString(Constants.SERVICE_REQUEST_ID_PARCEL_KEY);
        } catch (Exception e){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mListView = (LockableListView) rootView.findViewById(R.id.quote_list_view);
        mListView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        mSlidingUpPanelLayout = (CustomSlidingUpPanelLayout) rootView.findViewById(R.id.slidingLayout);
        mSlidingUpPanelLayout.setEnableDragViewTouchEvents(true);

        int mapHeight = getResources().getDimensionPixelSize(R.dimen.map_height);
        mSlidingUpPanelLayout.setPanelHeight( getResources().getDimensionPixelSize(R.dimen.slider_map_height)); // you can use different height here
//        mSlidingUpPanelLayout.setScrollableView(mListView, mapHeight);

        mSlidingUpPanelLayout.setPanelSlideListener(this);
        mSlidingUpPanelLayout.setSlidingEnabled(true);
        // transparent view at the top of ListView
        mTransparentView = rootView.findViewById(R.id.transparentView);
        mWhiteSpaceView = rootView.findViewById(R.id.whiteSpaceView);

        acceptQuoteButton = (FrameLayout) rootView.findViewById(R.id.quote_accept_layout);
        acceptQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(getActivity(), TransactionActivity.class);
                final Bundle extras = new Bundle();
                extras.putParcelable(Constants.SELECTED_QUOTE_ITEM_ID_PARCEL_KEY, currentQuote);
                extras.putString(Constants.SERVICE_REQUEST_ID_PARCEL_KEY, serviceRequestId);
                extras.putBoolean("paymentMode", true);
                intent.putExtras(extras);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        // init header view for ListView
        mTransparentHeaderView = inflater.inflate(R.layout.transparent_header_view, mListView, false);
        mSpaceView = mTransparentHeaderView.findViewById(R.id.space);


        mSlidingUpPanelLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSlidingUpPanelLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mSlidingUpPanelLayout.onPanelDragged(0);
            }
        });
        mSlidingUpPanelLayout.collapsePane();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLocation = getArguments().getParcelable(ARG_LOCATION);
        if (mLocation == null) {
            mLocation = getLastKnownLocation(false);
        }
        
        ArrayList<String> testData = new ArrayList<String>(100);
        for (int i = 0; i < 100; i++) {
            testData.add("Item " + i);
        }
        // show white bg if there are not too many items
         mWhiteSpaceView.setVisibility(View.VISIBLE);

        // ListView approach
        mListView.addHeaderView(mTransparentHeaderView);
        adapter = new QuoteDetailListAdapter(getContext(), quoteList, serviceList);
        mListView.setAdapter(adapter);
//        mListView.setOnItemClickListener((parent, view, position, id) -> mSlidingUpPanelLayout.collapsePane());
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            TransactionDetailsAdapter feeAdapter = new TransactionDetailsAdapter(getContext(), currentQuote, null);
            mListView.setAdapter(feeAdapter);
//            mWhiteSpaceView.setVisibility(View.GONE);
        });
        mTransparentHeaderView.setOnClickListener(view -> {
            mListView.setAdapter(adapter);
            mWhiteSpaceView.setVisibility(View.GONE);
        });

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMapFragment.getMapAsync(this);
            // Check if we were successful in obtaining the map.

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // In case Google Play services has since become available.
        setUpMapIfNeeded();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Connect the client.
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        // Disconnecting the client invalidates it.
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private LatLng getLastKnownLocation() {
        return getLastKnownLocation(true);
    }

    private LatLng getLastKnownLocation(boolean isMoveMarker) {
        LocationManager lm = (LocationManager) CAKNOWApplication.get().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_LOW);
        String provider = lm.getBestProvider(criteria, true);
        if (provider == null) {
            return null;
        }
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
        }
        Location loc = lm.getLastKnownLocation(provider);
        if (loc != null) {
            LatLng latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
            if (isMoveMarker) {
                //moveMarker(latLng);
            }
            return latLng;
        }
        return null;
    }


    private void setupMarkers(){
        if(this.quoteList.size()>0){
            for (int i=0; i<quoteList.size();i++){
                QuoteList currentQuote = quoteList.get(i);
                LatLng currentLatLng = new LatLng(currentQuote.getLatitude(), currentQuote.getLongitude());
                String currentPrice;
                try{
                    currentPrice = currentQuote.getPriceDetails().get(currentQuote.getPriceDetails().size()-1).getPrice();
                } catch (Exception e){
                    currentPrice = "$X.XX";
                }

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(currentPrice)))
                        .position(currentLatLng).anchor(0.5f, 0.5f));
                marker.setTag(i);
                moveToLocation(marker.getPosition());

            }
        }
    }
    private void moveMarker(LatLng latLng) {
        if (mLocationMarker != null) {
            mLocationMarker.remove();
        }

//// add marker to Map
//        mLocationMarker = mMap.addMarker(new MarkerOptions()
//                .position(latLng).anchor(0.5f, 0.5f));
    }


    private void moveToLocation(LatLng latLng) {
        moveToLocation(latLng, true);
    }

    private void moveToLocation(LatLng latLng, final boolean moveCamera) {
        if (latLng == null) {
            return;
        }
        moveMarker(latLng);
        mLocation = latLng;
        mListView.post(() -> {
            if (mMap != null && moveCamera) {
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(mLocation, 11.0f)));
            }
        });
    }

    private void collapseMap() {
        if (mHeaderAdapter != null) {
            mHeaderAdapter.showSpace();
        }
        mTransparentView.setVisibility(View.GONE);
        if (mMap != null && mLocation != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLocation, 15f), 10, null);
            mMap.animateCamera(CameraUpdateFactory.scrollBy(0, displaySize.y*.30f), 100, null);
        }
        mListView.setScrollingEnabled(true);
    }

    private void expandMap() {
        if (mHeaderAdapter != null) {
            mHeaderAdapter.hideSpace();
        }
        mTransparentView.setVisibility(View.VISIBLE);
        if (mMap != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLocation, 11f), 1000, null);
        }
        mListView.setScrollingEnabled(false);
    }

    @Override
    public void onPanelSlide(View view, float v) {
    }

    @Override
    public void onPanelCollapsed(View view) {
        expandMap();
    }

    @Override
    public void onPanelExpanded(View view) {
        collapseMap();
    }

    @Override
    public void onPanelAnchored(View view) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (mIsNeedLocationUpdate) {
            //moveToLocation(location);
            // Start with map expanded and details closed
            mSlidingUpPanelLayout.collapsePane();
        }

        mIsNeedLocationUpdate  = false;
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setNumUpdates(1);

        // For showing a move to my location button
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);


            }
            return;

        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onItemClicked(int position) {
        mSlidingUpPanelLayout.collapsePane();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {
            // For showing a move to my location button
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            1);


                }
                return;

            }

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setCompassEnabled(false);
            mMap.getUiSettings().setZoomControlsEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            LatLng update = getLastKnownLocation();
//            if (update != null) {
//                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(update, 11.0f)));
//            }
            setupMarkers();
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    currentQuote = adapter.selectItem((Integer) marker.getTag());
                    mSlidingUpPanelLayout.expandPane();
                    mLocation = marker.getPosition();
                    return false;
                }
            });
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    mIsNeedLocationUpdate = false;
                    //moveToLocation(latLng, false);
                    //mSlidingUpPanelLayout.expandPane();
                    mSlidingUpPanelLayout.collapsePane();
                }
            });
            // Start with map expanded and details closed

        }
    }

    private Bitmap getMarkerBitmapFromView(String price) {

        View customMarkerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.map_marker, null);
        TextView markerTextView = (TextView) customMarkerView.findViewById(R.id.marker_price);
        markerTextView.setText(price);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }
}

