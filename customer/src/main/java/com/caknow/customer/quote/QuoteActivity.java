package com.caknow.customer.quote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.garage.AddVehicleActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.service.quotes.ServiceList;
import com.caknow.customer.widget.BaseActivity;
import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.sothree.slidinguppanel.CustomSlidingUpPanelLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by junu on 1/1/17.
 */

public class QuoteActivity extends BaseActivity {

//    @BindView(R.id.topContent)
//    FrameLayout topLayout;
//
//    @BindView(R.id.bottomContent)
//    FrameLayout bottomLayout;
    ArrayList<QuoteList> quotesList;
    ArrayList<ServiceList> serviceList;
    String serviceRequestId;
    CustomSlidingUpPanelLayout slidingUpPanelLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        quotesList = getIntent().getParcelableArrayListExtra(Constants.QUOTE_LIST_ID_PARCEL_KEY);
        serviceList = getIntent().getParcelableArrayListExtra(Constants.SERVICE_LIST_PARCEL_KEY);
        serviceRequestId = getIntent().getStringExtra(Constants.SERVICE_REQUEST_ID_PARCEL_KEY);
        if (savedInstanceState == null) {
            QuoteMapFragment mapFragment = new QuoteMapFragment();
            Bundle mapArgs = new Bundle();
            mapArgs.putParcelableArrayList(Constants.QUOTE_LIST_ID_PARCEL_KEY, quotesList);
            mapArgs.putParcelableArrayList(Constants.SERVICE_LIST_PARCEL_KEY, serviceList);
            mapArgs.putString(Constants.SERVICE_REQUEST_ID_PARCEL_KEY, serviceRequestId);
            mapFragment.setArguments(mapArgs);
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.add(R.id.fragment, mapFragment);
            trans.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quote_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_start_new_service) {
            Intent intent = new Intent(this, AddVehicleActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initContentView() {
        //getIntent().getExtras().getParcelable("quote");

        ButterKnife.bind(this);
//        slidingUpPanelLayout = ButterKnife.findById(this, R.id.sliding_layout);
//        slidingUpPanelLayout.setEnabled(true);

//        addFragment(R.id.topContent,
//                mapFragment,
//                QuoteMapFragment.FRAGMENT_TAG);
//        addFragment(R.id.bottomContent,
//                new QuoteDetailFragment(),
//                QuoteDetailFragment.FRAGMENT_TAG);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Quote");
            ((ImageButton)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }


    /**
     * Use this to hide a fragment from view
     * @param fragment
     */
    private void hideFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.commit();
    }

    public void openPane(){
        slidingUpPanelLayout.expandPane();
    }
}
