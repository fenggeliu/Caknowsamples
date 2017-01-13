package com.caknow.customer.service.fragment;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.service.NewServiceRequestActivity;
import com.caknow.customer.util.net.service.Geolocation;
import com.caknow.customer.util.net.service.ServiceAPI;
import com.caknow.customer.util.net.service.ServiceAddress;
import com.caknow.customer.util.net.service.ServiceRequestPayload;
import com.caknow.customer.util.net.service.ServiceRequestResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/1/17.
 */

public class ServiceDetailsFragment extends BaseFragment implements Callback<ServiceRequestResponse> {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ServiceDetailsFragment.class.getName();
    ServiceAddress address;
    int serviceType;
    List<String> services;
    String[] serviceId;
    String vehicleId;
    Geolocation geolocation;
    @Inject
    Retrofit retrofit;


    @BindView(R.id.service_detail_mileage_editext)
    EditText mileageEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service_request_detail, container, false);
        CAKNOWApplication.get().getNetComponent().inject(this);
        unbinder = ButterKnife.bind(this, v);
        ((NewServiceRequestActivity) getActivity()).updateTitle("Details", R.drawable.ic_action_back);
        address = ((NewServiceRequestActivity) getActivity()).getServiceAddress();
        serviceType = ((NewServiceRequestActivity) getActivity()).getServiceType();
        serviceId = ((NewServiceRequestActivity) getActivity()).getServiceId();
        vehicleId = ((NewServiceRequestActivity) getActivity()).getVehicleId();
        geolocation = ((NewServiceRequestActivity) getActivity()).getGeolocation();
        services = new ArrayList<>();
        services.add(serviceId[0]);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.service_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_start_new_service) {
            submitServiceRequest();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void submitServiceRequest() {
        ((NewServiceRequestActivity) getActivity()).showProgress();
        ServiceRequestPayload payload = new ServiceRequestPayload();
        payload.setAddress(address);
        payload.setServiceList(services);
        payload.setVehicleId(vehicleId);
        payload.setMileage(Long.valueOf(mileageEditText.getText().toString()));
        payload.setPriority(0);
        payload.setDescription("");
        payload.setGeolocation(geolocation);
        String text = ServiceRequestPayload.getJsonString(payload);
        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), text);
        Call<ServiceRequestResponse> call = retrofit.create(ServiceAPI.class).submitNewServiceRequest(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ServiceRequestResponse> call, Response<ServiceRequestResponse> response) {
        getActivity().finish();
    }

    @Override
    public void onFailure(Call<ServiceRequestResponse> call, Throwable t) {
        Toast.makeText(getContext(), "Oops, something happened!", Toast.LENGTH_SHORT).show();
    }
}
