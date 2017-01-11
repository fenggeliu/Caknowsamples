package com.caknow.customer.garage.fragment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
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
    private Retrofit retrofit;

    private String year, make, model, trim;

    @BindView(R.id.acsl_vehicle_name)
    TextView vehicleName;

    @BindView(R.id.acsl_submit_btn)
    Button submitButton;

    @OnClick(R.id.acsl_submit_btn)
    void addCarToGarage(){
        if (client == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addNetworkInterceptor(new StethoInterceptor());
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header(HeadersContract.HEADER_X_API_KEY, "sJvVmx9uyJD7eE1bZraPEUfsm6BpzyOlgDZ04eqRyUs=") // <-- this is the important line
                        .header(HeadersContract.HEADER_X_ACCESS_TOKEN, SessionPreferences.INSTANCE.getStringPref(PreferenceKeys.ACCESS_TOKEN)) // <-- this is the important line
                        .header("Content-Type", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
            client = httpClient.build();
        }
        if (retrofit == null) {
            Gson gson = new GsonBuilder().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

        }

        GarageAPI garageAPI = retrofit.create(GarageAPI.class);
        String text = AddVehicleRequest.getJsonString(new AddVehicleRequest(this.year, this.make, this.model, this.trim, "100"));
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
            year = bundle.getString("year", "year");
            make = bundle.getString("make", "make");
            model = bundle.getString("model", "model");
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

    }
}
