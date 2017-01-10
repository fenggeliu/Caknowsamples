package com.caknow.customer.garage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.Application;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.garage.fragment.AddVehicleFragment;
import com.caknow.customer.garage.fragment.ConfirmVehicleFragment;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.BaseRequestInterceptor;
import com.caknow.customer.util.net.garage.GarageAPI;
import com.caknow.customer.util.net.garage.GarageResponse;
import com.caknow.customer.util.net.garage.MMYResponse;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junu on 1/1/17.
 */

public class NewVehicleActivity extends BaseActivity implements AddVehicleFragment.OnListFragmentInteractionListener, Callback<MMYResponse> {

    protected String make, makeNiceName;
    protected String model, modelNiceName;
    protected String year;

    List<Make> makeList;
    List<Model> modelList;
    List<Year> yearList;

    private Retrofit retrofit;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_garage);
        ButterKnife.bind(this);
        Application.get().getNetComponent().inject(this);

        GarageAPI garageAPI = retrofit.create(GarageAPI.class);
        Call<MMYResponse> call = garageAPI.getMMY();
        //asynchronous call
        call.enqueue(this);



    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        MMYDeserializer<Year> myDeserializer = new MMYDeserializer<Year>(Year.class,
                new SubContentDeserializer());
        Gson gson = new GsonBuilder().registerTypeAdapter(Year.class, myDeserializer).create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addNetworkInterceptor(new StethoInterceptor());
        httpClient.addInterceptor(new BaseRequestInterceptor());
        return httpClient.build();
      retrofit =  new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("New Vehicle");
            ((ImageButton)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }


    @Override
    public void onListFragmentInteraction(MMY item) {
        String selectedName = item.getNiceName();
        String displayName = item.getNiceName();

        if(item.type < 3) {
            AddVehicleFragment modelFragment = new AddVehicleFragment();
            Bundle bundle = new Bundle();
            String valueString;
            switch (item.type) {
                case 0:
                    valueString = "make";
                    make = item.getName().concat(" ");

                    Make make = (Make) item;
                    modelList = make.getModels();
                    break;
                case 1:
                    valueString = "model";
                    model = item.getName().concat(" ");
                    Model model = (Model) item;
                    yearList = model.getYears();
                    break;
                case 2:
                    valueString = "year";
                    year = item.getName();
                    break;
                default:
                    valueString = "";
                    break;
            }
            if (!TextUtils.isEmpty(valueString)) {
                bundle.putInt(valueString, item.type);
            }

            if(item.type == 2){
                ConfirmVehicleFragment confirmFragment = new ConfirmVehicleFragment();
                Bundle confirmBundle = new Bundle();
                confirmBundle.putString("displayName", this.make.concat(this.model).concat(this.year));
                confirmFragment.setArguments(confirmBundle);
                replaceFragment(R.id.flContent, confirmFragment, AddVehicleFragment.FRAGMENT_TAG, "confirmation");
            }
            else{
                modelFragment.setArguments(bundle);
                replaceFragment(R.id.flContent, modelFragment, AddVehicleFragment.FRAGMENT_TAG, valueString);
            }

        }

    }

    public List<Make> getMakeList(){
        return this.makeList;
    }
    public List<Model> getModelList(){
        return this.modelList;
    }
    public List<Year> getYearList(){
        return this.yearList;
    }

    @Override
    public void onResponse(Call<MMYResponse> call, Response<MMYResponse> response) {
        this.makeList = response.body().getPayload().getMakes();

        AddVehicleFragment fragment = new AddVehicleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        fragment.setArguments(bundle);
        addFragment(R.id.flContent,
                fragment,
                AddVehicleFragment.FRAGMENT_TAG);
    }

    @Override
    public void onFailure(Call<MMYResponse> call, Throwable t) {

        Toast.makeText(NewVehicleActivity.this, "ERROR RETRIEVING MMY", Toast.LENGTH_LONG);
    }


}
