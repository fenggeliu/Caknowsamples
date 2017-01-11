package com.caknow.customer.garage;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.garage.fragment.AddVehicleMakeFragment;
import com.caknow.customer.garage.fragment.AddVehicleModelFragment;
import com.caknow.customer.garage.fragment.AddVehicleYearFragment;
import com.caknow.customer.garage.fragment.ConfirmVehicleFragment;
import com.caknow.customer.garage.fragment.MMYListItem;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.garage.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/1/17.
 */

public class NewVehicleActivity extends BaseActivity implements Callback<MMYResponse>,
AddVehicleMakeFragment.OnListFragmentInteractionListener,
AddVehicleModelFragment.OnListFragmentInteractionListener,
        AddVehicleYearFragment.OnListFragmentInteractionListener{

    protected String make, makeNiceName;
    protected String model, modelNiceName;
    protected String year;
    List<Make> payload;
    Make selectedMake;
    List<MMYListItem> makeList;
    List<MMYListItem> modelList;
    List<MMYListItem> yearList;

    @Inject
    Retrofit retrofit;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_garage);
        ButterKnife.bind(this);
        CAKNOWApplication.get().getNetComponent().inject(this);

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
    public void onResponse(Call<MMYResponse> call, Response<MMYResponse> response) {
        this.payload = response.body().getPayload().getMakes();
        ArrayList<Make> data = parseMakeData();
        AddVehicleMakeFragment fragment = new AddVehicleMakeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        bundle.putParcelableArrayList(Constants.ITEM_LIST_PARCEL_KEY, parseMakeData());
        fragment.setArguments(bundle);
        addFragment(R.id.flContent,
                fragment,
                AddVehicleMakeFragment.FRAGMENT_TAG);
    }

    @Override
    public void onFailure(Call<MMYResponse> call, Throwable t) {

        Toast.makeText(NewVehicleActivity.this, "ERROR RETRIEVING MMY", Toast.LENGTH_LONG);
    }

    private ArrayList<Make> parseMakeData(){
        ArrayList<Make> makes = new ArrayList<Make>();
        makes.addAll(payload);
        return makes;
    }

    private ArrayList<Model> parseModelData(List<Model> modelsList){
        ArrayList<Model> models = new ArrayList();
        models.addAll(modelsList);
        return models;
    }

    private ArrayList<Year> parseYearData(List<String> yearsList){
        ArrayList<Year> years = new ArrayList();
        for(int i=0; i<yearsList.size(); i++){
            years.add(new Year(year));
        }
        return years;
    }


    @Override
    public void onListFragmentInteraction(Make item) {
        make = item.getName();
        AddVehicleModelFragment fragment = new AddVehicleModelFragment();
        ArrayList<Model> models = parseModelData(item.getModels());
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.ITEM_LIST_PARCEL_KEY, models);
        fragment.setArguments(args);
        this.replaceFragment(R.id.flContent, fragment, AddVehicleModelFragment.FRAGMENT_TAG, "model");
    }

    @Override
    public void onListFragmentInteraction(Model item) {
        model = item.getName();
        modelNiceName = item.getNiceName();
        AddVehicleModelFragment fragment = new AddVehicleModelFragment();
        ArrayList<Year> models = parseYearData(item.getYears());
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.ITEM_LIST_PARCEL_KEY, models);
        this.replaceFragment(R.id.flContent, fragment, AddVehicleModelFragment.FRAGMENT_TAG, "model");
    }

    @Override
    public void onListFragmentInteraction(Year item) {
        ConfirmVehicleFragment fragment = new ConfirmVehicleFragment();
        year = item.value;
        Bundle args = new Bundle();
        args.putString("make", make);
        args.putString("model", model);
        args.putString("year", year);
        this.replaceFragment(R.id.flContent, fragment, AddVehicleModelFragment.FRAGMENT_TAG, "model");
    }
}
