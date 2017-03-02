package com.caknow.customer.service.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.Manifest;

import com.bumptech.glide.Glide;
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

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by junu on 1/1/17.
 */

public class NewServiceDetailsFragment extends BaseFragment implements Callback<ServiceRequestResponse> {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + NewServiceDetailsFragment.class.getName();

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_REQUEST_CAMERA = 2;
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
    @BindView(R.id.rdl_pic1) TextView picOne;
    @BindView(R.id.rdl_pic2_layout) LinearLayout picTwoLayout;
    @BindView(R.id.rdl_pic3_layout) LinearLayout picThreeLayout;
    @BindView(R.id.rdl_pic2) ImageView picTwo;
    @BindView(R.id.rdl_pic3) ImageView picThree;
//    @OnClick(R.id.rdl_pic1_layout)
//    void uploadPhoto(){
//
//        final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
////to get image and videos, I used a */"
//        galleryIntent.setType("image/*");
//        startActivityForResult(galleryIntent, 1);
//    }
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

        picOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                View parentView = inflater.inflate(R.layout.bottom_sheet_image_upload, null);
                bottomSheetDialog.setContentView(parentView);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parentView.getParent());
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics()));
                bottomSheetDialog.show();

                Button cameraButton = (Button) parentView.findViewById(R.id.bs_camera_btn);
                Button galleryButton = (Button) parentView.findViewById(R.id.bs_gallery_btn);

                cameraButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Check Permissions Now
                            // Callback onRequestPermissionsResult interceptado na Activity MainActivity
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.CAMERA},
                                    RESULT_REQUEST_CAMERA);
                        } else {
                            // permission has been granted, continue as usual

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,RESULT_REQUEST_CAMERA);
                        }
                        bottomSheetDialog.dismiss();
                    }
                });

                galleryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    //to get image and videos, I used a */"
                        galleryIntent.setType("image/*");
                        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
           if (picTwoLayout.getVisibility() == View.GONE){
               picTwoLayout.setVisibility(View.VISIBLE);
               Glide.with(getContext()).load(selectedImage).into(picTwo);
           }else{
               picThreeLayout.setVisibility(View.VISIBLE);
               Glide.with(getContext()).load(selectedImage).into(picThree);
//               picThree.setImageURI(selectedImage);
           }
        } else if (requestCode == RESULT_REQUEST_CAMERA){
            Uri selectedImage = data.getData();
            if (picTwoLayout.getVisibility() == View.GONE){
                picTwoLayout.setVisibility(View.VISIBLE);
                Glide.with(getContext()).load(selectedImage).into(picTwo);
            }else {
                picThreeLayout.setVisibility(View.VISIBLE);
                Glide.with(getContext()).load(selectedImage).into(picThree);
//               picThree.setImageURI(selectedImage);
            }
        }
//        FragmentTransaction tr = getFragmentManager().beginTransaction();
//        tr.replace(R.id.container_current, this);
//        tr.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RESULT_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
