package com.caknow.customer.service.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.garage.VehicleServiceActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.garage.Vehicle;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.service.NewServiceRequestActivity;
import com.caknow.customer.util.net.service.location.Geolocation;
import com.caknow.customer.util.net.service.ServiceAPI;
import com.caknow.customer.util.net.service.location.ServiceAddress;
import com.caknow.customer.util.net.service.NewServiceRequest;
import com.caknow.customer.util.net.service.ServiceRequestResponse;
import com.caknow.customer.widget.NothingSelectedSpinnerAdapter;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junu on 1/1/17.
 */

public class NewServiceDetailsFragment extends BaseFragment implements Callback<ServiceRequestResponse> {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + NewServiceDetailsFragment.class.getName();

    // Fields needed to make service request
    ServiceAddress address;
    int serviceType;
    ArrayList<String> serviceId;
    String vehicleId;
    String description;
    Geolocation geolocation;
    Vehicle vehicle;

    // Handler to handle opening keyboard on touch of the description layout
    private Handler mHandler= new Handler();

    @BindView(R.id.rdl_description_input) EditText descriptionEditText;
    @BindView(R.id.service_detail_mileage_editext) EditText mileageEditText;
    @BindView(R.id.spinner_time_state) Spinner spinnerPriority;
    @OnClick(R.id.rdl_pic1_layout)
    void uploadPhoto(){
        final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//to get image and videos, I used a */"
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, 1);
    }
    @OnClick(R.id.service_request_description_layout)
    void focusDescription(){
        descriptionEditText.requestFocus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service_request_detail, container, false);
        unbinder = ButterKnife.bind(this, v);
        ((NewServiceRequestActivity)getActivity()).updateTitle("Details", R.drawable.ic_action_back);
        address = ((NewServiceRequestActivity)getActivity()).getServiceAddress();
        serviceType = ((NewServiceRequestActivity)getActivity()).getServiceType();
        serviceId = ((NewServiceRequestActivity)getActivity()).getServiceId();
        vehicleId = ((NewServiceRequestActivity)getActivity()).getVehicleId();
        geolocation = ((NewServiceRequestActivity)getActivity()).getGeolocation();
        description = ((NewServiceRequestActivity)getActivity()).getServiceDescription();
        vehicle = ((NewServiceRequestActivity) getActivity()).getVehicle();
        setupPrioritySpinner();
        setHasOptionsMenu(true);
        return v;
    }

    // Set up NothingSelectedSpinnerAdapter to show hint text in spinner
    void setupPrioritySpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.priorities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setPrompt("How soon do you need this service?");

        spinnerPriority.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.spinner_row_service_detail_priority,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        getContext()));
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

    /**
     * Sloppy way of building a payload object for request submission.
     */
    private void submitServiceRequest(){
        String mileage = mileageEditText.getText().toString();
        if (!mileage.matches("")) {
            try {
                ((NewServiceRequestActivity) getActivity()).showProgress();
            } catch (Exception e) {
                // this call is not thread safe
            }
            final NewServiceRequest payload = new NewServiceRequest();
            payload.setAddress(address);
            payload.setServiceList(serviceId);
            payload.setVehicleId(vehicleId);
            payload.setMileage(Long.valueOf(mileage));
            payload.setPriority(spinnerPriority.getSelectedItemPosition());
            payload.setDescription(description);
            payload.setGeolocation(geolocation);
            payload.setType(serviceType);
            String text = NewServiceRequest.getJsonString(payload);
            RequestBody body =
                    RequestBody.create(MediaType.parse("application/json"), text);
            Call<ServiceRequestResponse> call = retrofit.create(ServiceAPI.class).submitNewServiceRequest(body);
            call.enqueue(this);
        }else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            String message = "Please Enter Proper Mileage on Your Vehicle!";
            alertDialogBuilder.setMessage(message);
            alertDialogBuilder.setPositiveButton("OK", (dialogIntergace, i) -> {
                dialogIntergace.dismiss();
            });
            alertDialogBuilder.show();
        }

    }

    @Override
    public void onResponse(Call<ServiceRequestResponse> call, Response<ServiceRequestResponse> response) {
        ServiceRequestResponse body = response.body();
        // If Successful, close parent activity NewServiceRequestActivity
        if(body.isSuccess()){
            try {
                Intent intent = new Intent(getActivity(), VehicleServiceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Constants.VEHICLE_PARCEL_KEY, (Parcelable) vehicle);
                getActivity().finish();
                ((NewServiceRequestActivity) getActivity()).hideProgress();
                startActivity(intent);
            } catch(Exception e){
                // getActivity() call is not thread safe
            }
        } else{
            Toast.makeText(getContext(), body.getMessage(), Toast.LENGTH_SHORT).show();
            ((NewServiceRequestActivity) getActivity()).hideProgress();

        }

    }

    @Override
    public void onFailure(Call<ServiceRequestResponse> call, Throwable t) {
        Toast.makeText(getContext(), "Oops, something happened!", Toast.LENGTH_SHORT).show();
    }
}
