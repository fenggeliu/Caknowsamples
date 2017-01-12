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
import com.caknow.customer.BaseFragment;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.PreferenceKeys;
import com.caknow.customer.util.SessionPreferences;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.HeadersContract;
import com.caknow.customer.util.net.garage.AddVehicleRequest;
import com.caknow.customer.util.net.garage.AddVehicleResponse;
import com.caknow.customer.util.net.garage.GarageAPI;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Inject
    Retrofit retrofit;

    @OnClick(R.id.acsl_submit_btn)
    void addCarToGarage(){

        GarageAPI garageAPI = retrofit.create(GarageAPI.class);
        String text = AddVehicleRequest.getJsonString(new AddVehicleRequest(this.year, this.make, this.model, "", "100"));
        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), text);

        Call<AddVehicleResponse> call = garageAPI.addVehicle(body);
        //asynchronous call
        call.enqueue(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CAKNOWApplication.get().getNetComponent().inject(this);
        Bundle bundle = getArguments();
        if(bundle != null){
            make = bundle.getString("make", "make");
            model = bundle.getString("model", "model");
            year = bundle.getString("year", "year");

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
    public void onResponse(Call<AddVehicleResponse> call, Response<AddVehicleResponse> response) {
        try {
            HomeActivity homeActivity = (HomeActivity) getActivity();
            homeActivity.addFragment(R.id.flContent,
                    new GarageFragment(),
                    GarageFragment.FRAGMENT_TAG);
        } catch (Exception e){
            //
        }
    }

    @Override
    public void onFailure(Call<AddVehicleResponse> call, Throwable t) {
        Toast.makeText(getContext(), "Error adding car!", Toast.LENGTH_SHORT).show();
    }
}
