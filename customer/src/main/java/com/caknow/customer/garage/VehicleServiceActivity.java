package com.caknow.customer.garage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.job.JobActivity;
import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.caknow.customer.util.net.service.quotes.ServiceList;
import com.caknow.customer.widget.BaseActivity;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.garage.fragment.VehicleServiceFragment;
import com.caknow.customer.quote.QuoteActivity;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.garage.Vehicle;
import com.caknow.customer.util.net.service.GetQuotesResponse;
import com.caknow.customer.util.net.service.ServiceAPI;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.caknow.customer.garage.fragment.VehicleServiceFragment.FRAGMENT_TAG;

/**
 * This activity is loaded when user clicks on a vehicle from the {@link com.caknow.customer.garage.fragment.GarageFragment}
 *
 * Created by junu on 1/1/17.
 */

public class VehicleServiceActivity extends BaseActivity implements VehicleServiceFragment.OnListFragmentInteractionListener {

    protected String make;
    protected String model;
    protected String year;
    protected String trim;
    protected String currentServiceRequestId = "";
    Vehicle vehicle;

    @Inject
    Retrofit retrofit;

    ServiceAPI serviceAPI;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_vehicle_history);
        ButterKnife.bind(this);
        CAKNOWApplication.get().getNetComponent().inject(this);
        // Retrieve the Vehicle object from the intent extras passed in from GarageFragment
        vehicle = getIntent().getExtras().getParcelable(Constants.VEHICLE_PARCEL_KEY);
    }

    @Override
    protected void initView() {
        final VehicleServiceFragment fragment = new VehicleServiceFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.VEHICLE_PARCEL_KEY, vehicle);
        fragment.setArguments(bundle);
        // Load up a new VehicleServiceFragment with the data set in initContentView
        addFragment(R.id.flContent,
                fragment,
                FRAGMENT_TAG);
    }

    @Override
    protected void initData() {
        serviceAPI = retrofit.create(ServiceAPI.class);
    }

    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Garage");
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }

    /**
     * When User clicks on a service item in the VehicleServiceActivity, the events are posted here
     * and user should be routed to the next screen from here.
     * @param item
     */
    @Override
    public void onListFragmentInteraction(VehicleServiceInterface item) {
        switch (item.getStatus()) {
            case 1:
                openQuotesMap(item);
                break;
            case 2:
                openAcceptedQuote(item);
                break;
            case 3:
                openInServiceQuote(item);
                break;
            case 8:
//                try{
//                    hideProgress();
//                } catch(Exception e){
//
//                }
                openCompleteQuote(item);
                break;
            default:
                try{
                    hideProgress();
                } catch(Exception e){

                }
                break;
        }
    }

    private void openInServiceQuote(VehicleServiceInterface item){
        currentServiceRequestId = item.getServiceRequestId();
        serviceAPI.getQuotes(currentServiceRequestId).enqueue(new Callback<GetQuotesResponse>() {
            @Override
            public void onResponse(Call<GetQuotesResponse> call, Response<GetQuotesResponse> response) {
                try {
                    hideProgress();
                } catch(Exception e){

                }
                Bundle args = new Bundle();
                Intent intent = new Intent(VehicleServiceActivity.this, JobActivity.class);
                args.putParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY, item);
                intent.putExtras(args);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<GetQuotesResponse> call, Throwable t) {

            }
        });
    }
    private void openAcceptedQuote(VehicleServiceInterface item){
        currentServiceRequestId = item.getServiceRequestId();
        Intent intent = new Intent(VehicleServiceActivity.this, JobActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY, item);
        intent.putExtras(args);
        startActivity(intent);

    }

    private void openCompleteQuote(VehicleServiceInterface item){
        currentServiceRequestId = item.getServiceRequestId();
        Intent intent = new Intent(VehicleServiceActivity.this, JobActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY, item);
        intent.putExtras(args);
        startActivity(intent);

    }

    private void openQuotesMap(VehicleServiceInterface item){
        try{
            showProgress();
        } catch(Exception e){

        }
        if(item.getQuoteCount() == 0){
            hideProgress();
            final Intent emptyJobIntent = new Intent(this, JobActivity.class);
            final Bundle extras = new Bundle();
            extras.putParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY, item);
            emptyJobIntent.putExtras(extras);
            startActivity(emptyJobIntent);
            return;
        }
        currentServiceRequestId = item.getServiceRequestId();
        serviceAPI.getQuotes(currentServiceRequestId).enqueue(new Callback<GetQuotesResponse>() {
            @Override
            public void onResponse(Call<GetQuotesResponse> call, Response<GetQuotesResponse> response) {
                try {
                    hideProgress();
                } catch(Exception e){

                }
                Bundle args = new Bundle();
                Intent intent = new Intent(VehicleServiceActivity.this, QuoteActivity.class);
                ArrayList<QuoteList> quotes = new ArrayList();
                ArrayList<ServiceList> services = new ArrayList();
                services.addAll(response.body().getPayload().getServiceList());
                quotes.addAll(response.body().getPayload().getQuoteList());
                args.putParcelableArrayList(Constants.QUOTE_LIST_ID_PARCEL_KEY, quotes);
                args.putParcelableArrayList(Constants.SERVICE_LIST_PARCEL_KEY, services);
                args.putString(Constants.SERVICE_REQUEST_ID_PARCEL_KEY, response.body().getPayload().getServiceRequestId());
                intent.putExtras(args);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<GetQuotesResponse> call, Throwable t) {
                Toast.makeText(VehicleServiceActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                try {
                    hideProgress();
                } catch(Exception e){

                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
//        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
//        if(fragment != null)
//            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
