package com.caknow.customer.garage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.garage.Vehicle;
import com.caknow.customer.garage.VehicleServiceResponse;
import com.caknow.customer.garage.VehicleType;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.service.NewServiceRequestActivity;
import com.caknow.customer.service.adapter.VehicleServiceAdapter;
import com.caknow.customer.service.model.Maintenance;
import com.caknow.customer.service.model.Repair;
import com.caknow.customer.service.model.VehicleServicePayload;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.garage.GarageAPI;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by junu on 1/1/17.
 */

public class VehicleServiceFragment extends BaseFragment  implements Callback<VehicleServiceResponse> {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + VehicleServiceFragment.class.getName();

    private String displayName;

    @BindView(R.id.vehicle_service_name)
    TextView vehicleName;

    @BindView(R.id.srcl_car_logo_display)
    ImageView vehicleLogo;

    @BindView(R.id.new_vehicle_service_button)
    Button submitButton;

    @BindView(R.id.no_service_request_layout)
    LinearLayout noServiceRequestLayout;

    @BindView(R.id.vehicle_service_top_banner)
    LinearLayout bannerLayout;

    @BindView(R.id.vehicle_service_sticky_list)
    StickyListHeadersListView serviceListView;

    @Inject
    Retrofit retrofit;

    private Vehicle vehicle;
    private VehicleServiceAdapter adapter;
    /**
     * Launches New Service Request
     */
    @OnClick(R.id.new_vehicle_service_button)
    void startNewService(){
        Intent intent = new Intent(getActivity(), NewServiceRequestActivity.class);
        intent.putExtra("vehicleId", vehicle.getId());
        getActivity().startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Retrieve the vehicle data from the previous GarageFragment
        CAKNOWApplication.get().getNetComponent().inject(this);
        Bundle bundle = getArguments();
        vehicle = bundle.getParcelable(Constants.VEHICLE_PARCEL_KEY);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vehicleservice, container, false);
        unbinder = ButterKnife.bind(this, v);
        Glide.with(getActivity()).load(vehicle.getImageUrl()).into(vehicleLogo);
        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        loadData(vehicle);
    }

    private void loadData(Vehicle vehicle){
        try{
            ((HomeActivity)getActivity()).showProgress();
        } catch(Exception e){

        }
        GarageAPI garageAPI = retrofit.create(GarageAPI.class);

        Call<VehicleServiceResponse> call = garageAPI.getServiceRequestsByVehicleId(vehicle.getId());
        //asynchronous call
        call.enqueue(this);


    }

    @Override
    public void onResponse(Call<VehicleServiceResponse> call, Response<VehicleServiceResponse> response) {
        VehicleServicePayload serviceResponse = response.body().getServiceRequests();
        bannerLayout.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        String logo = Constants.LOGOURL.concat(serviceResponse.getVehicleMake());
        Glide.with(this).load(logo).into(vehicleLogo);
        vehicleName.setText(serviceResponse.getVehicleSummary());
        vehicleName.invalidate();
        vehicleLogo.invalidate();
//        if(serviceResponse.getMaintenanceList().isEmpty() && serviceResponse.getRepairList().isEmpty()){
//            noServiceRequestLayout.setVisibility(View.VISIBLE);
//        }
//        else{
            adapter = new VehicleServiceAdapter(getContext(), createDummyRepairData(), createDummyMaintenanceData());
            serviceListView.setAdapter(adapter);
            serviceListView.invalidate();
//        }

    }

    private ArrayList<Repair> createDummyRepairData(){
        //TODO REMOVE!
        ArrayList<Repair> testRepairList = new ArrayList<>();
        Repair dummyRepair = new Repair();
        dummyRepair.setOrderNo("123455");
        testRepairList.add(dummyRepair);
        return testRepairList;
    }

    private ArrayList<Maintenance> createDummyMaintenanceData(){
        //TODO REMOVE!
        ArrayList<Maintenance> testMaintenanceList = new ArrayList<>();
        Maintenance dummyMaintenance = new Maintenance();
        dummyMaintenance.setOrderNo("123455");
        testMaintenanceList.add(dummyMaintenance);
        return testMaintenanceList;
    }
    @Override
    public void onFailure(Call<VehicleServiceResponse> call, Throwable t) {

    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and niceName
        void onListFragmentInteraction(VehicleType item);
    }
}
