package com.caknow.customer.garage.fragment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.garage.AddVehicleActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.garage.addvehicle.AddVehicleRequest;
import com.caknow.customer.util.net.garage.addvehicle.AddVehicleResponse;
import com.caknow.customer.util.net.garage.GarageAPI;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junu on 1/1/17.
 */

public class ConfirmVehicleFragment extends BaseFragment implements Callback<AddVehicleResponse> {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ConfirmVehicleFragment.class.getName();

    private String displayName;
    private AddVehicleMakeFragment payload;
    private OkHttpClient client;

    private String year, make, model, makeNN, modelNN;

    @BindView(R.id.acsl_vehicle_name)
    TextView vehicleName;

    @BindView(R.id.acsl_submit_btn)
    Button submitButton;


    @OnClick(R.id.acsl_submit_btn)
    void addCarToGarage(){

        ((AddVehicleActivity)getActivity()).showProgress();
        String logoUrl = Constants.LOGOURL.concat(makeNN);
        GarageAPI garageAPI = retrofit.create(GarageAPI.class);
        String text = AddVehicleRequest.getJsonString(new AddVehicleRequest(this.year, this.make, this.model, "", "0", logoUrl));
        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), text);

        Call<AddVehicleResponse> call = garageAPI.addVehicle(body);
        //asynchronous call
        call.enqueue(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null){
            make = bundle.getString(Constants.VEHICLE_ADD_PAYLOAD_MAKE_KEY, "make");
            model = bundle.getString(Constants.VEHICLE_ADD_PAYLOAD_MODEL_KEY, "model");
            year = bundle.getString(Constants.VEHICLE_ADD_PAYLOAD_YEAR_KEY, "year");
            makeNN = bundle.getString(Constants.VEHICLE_ADD_PAYLOAD_MAKE_NICENAME_KEY, "make");
            displayName = String.format(Locale.getDefault(), "%s %s %s", this.year, this.make, this.model);
        }
        else{
            displayName = "ERROR!";
        }


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_confirmvehicle, container, false);
        unbinder = ButterKnife.bind(this, v);
        vehicleName.setText(displayName);
        return v;
    }


    @Override
    public void onResume(){
        super.onResume();
        ((AddVehicleActivity)getActivity()).updateTitle("Add Vehicle");
    }

    @Override
    public void onResponse(Call<AddVehicleResponse> call, Response<AddVehicleResponse> response) {
        try{
            ((AddVehicleActivity)getActivity()).hideProgress();
            if(!response.isSuccessful()){
                Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT);
            }
            getActivity().finish();
        } catch(Exception e){

        }

    }

    @Override
    public void onFailure(Call<AddVehicleResponse> call, Throwable t) {
        try{
            ((AddVehicleActivity)getActivity()).showProgress();
            getActivity().finish();
        } catch(Exception e){

        }
        Toast.makeText(getContext(), "Error adding car!", Toast.LENGTH_SHORT).show();
    }
}
