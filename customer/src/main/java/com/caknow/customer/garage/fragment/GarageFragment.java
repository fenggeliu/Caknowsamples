package com.caknow.customer.garage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.garage.NewVehicleActivity;
import com.caknow.customer.garage.Vehicle;
import com.caknow.customer.garage.VehicleServiceActivity;
import com.caknow.customer.garage.adapter.GarageAdapter;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.garage.GarageAPI;
import com.caknow.customer.util.net.garage.GarageResponse;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/1/17.
 */

public class GarageFragment extends BaseFragment implements Callback<GarageResponse> {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + GarageFragment.class.getName();

    /**
     * Butterknife Annotated View Bindings
     */
    @BindView(R.id.home_empty_garage_view) LinearLayout emptyGarageView;
    @BindView(R.id.vehicle_display) GridView vehicleGridView;

    @Inject
    Retrofit retrofit;
    @OnClick(R.id.home_empty_garage_view)
    void addNewCar(){
        try {
            Intent intent = new Intent(getActivity(), NewVehicleActivity.class);
            startActivity(intent);
        } catch (Exception e){

        }
    }



    HomeActivity homeActivity;
    GarageAdapter gridViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.content_garage, container, false);
        unbinder = ButterKnife.bind(this, v);
        homeActivity = (HomeActivity) getActivity();

        CAKNOWApplication.get().getNetComponent().inject(this);
        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        loadData();
    }

    private void loadData(){
        // prepare call in Retrofit 2.0
        if(homeActivity != null) {
            GarageAPI garageAPI = homeActivity.retrofit.create(GarageAPI.class);
            Call<GarageResponse> call = garageAPI.getVehicles();
            //asynchronous call
            call.enqueue(this);

            // synchronous call would be with execute, in this case you
            // would have to perform this outside the main thread
//         call.execute();

            // to cancel a running request
            // call.cancel();
            // calls can only be used once but you can easily clone them
            //Call<StackOverflowQuestions> c = call.clone();
            //c.enqueue(this);
        }
    }


    @Override
    public void onResponse(Call<GarageResponse> call, Response<GarageResponse> response) {

        List<Vehicle> vehicles;
        try{
            vehicles = response.body().getGaragePayload().getVehicles();
            if(vehicles.size() == 0) {
                emptyGarageView.setVisibility(View.VISIBLE);
            }
            else{
                emptyGarageView.setVisibility(View.INVISIBLE);
            }
            gridViewAdapter = new GarageAdapter(getContext(), vehicles);
            vehicleGridView.setAdapter(gridViewAdapter);
            vehicleGridView.setOnItemClickListener((adapterView, view, i, l) -> {
                try {
                    final Intent intent = new Intent(getActivity(), VehicleServiceActivity.class);
                    final Bundle extras = new Bundle();
                    extras.putParcelable(Constants.VEHICLE_PARCEL_KEY, gridViewAdapter.getItem(i));
                    intent.putExtras(extras);
                    getActivity().startActivity(intent);
                } catch(Exception e){
                    //
                }
            });
            vehicleGridView.invalidate();
            ((HomeActivity) getActivity()).hideProgress();
        } catch (Exception e){
            //
        }


    }

    @Override
    public void onFailure(Call<GarageResponse> call, Throwable t) {
        //Show Error

        ((HomeActivity) getActivity()).hideProgress();

    }
}
