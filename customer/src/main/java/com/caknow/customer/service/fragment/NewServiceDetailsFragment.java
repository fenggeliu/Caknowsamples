package com.caknow.customer.service.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
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
import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import javax.inject.Inject;

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
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
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
    ArrayList<String> pictures = new ArrayList<>();
    ArrayList<String> imageList = new ArrayList<>();
//    Cloudinary cloudinary;

    // Handler to handle opening keyboard on touch of the description layout
    private Handler mHandler= new Handler();

    @Inject
    Cloudinary cloudinary;

    @BindView(R.id.rdl_description_input) EditText descriptionEditText;
    @BindView(R.id.service_detail_mileage_editext) EditText mileageEditText;
    @BindView(R.id.spinner_time_state) Spinner spinnerPriority;
    @BindView(R.id.rdl_pic1) ImageView picOne;
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
//        pictures = new ArrayList<>();
//        imageList = new ArrayList<>();
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
                        int permission = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(
                                    getActivity(),
                                    PERMISSION_STORAGE,
                                    REQUEST_EXTERNAL_STORAGE);
                        }
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
//            if (pictures != null) {
//                for (int i = 0; i < pictures.size(); i++) {
//                    System.out.println(pictures.get(i));
//                    Cloudinary cloudinary = new Cloudinary(Constants.CLOUDINARY_AUTHENTICATION_URL);
//                    Map resultMap;
//                    try {
//                        resultMap = cloudinary.uploader().upload(pictures.get(i), ObjectUtils.emptyMap());
//                        System.out.println(resultMap.toString());
//                        imageList.add(resultMap.get("url").toString());
//                        System.out.println(imageList);
//                    } catch (IOException e) {
//                        Toast.makeText(getApplicationContext(), "Cloudinary upload failed", Toast.LENGTH_SHORT).show();
//                    }
//                    asyncUpload(pictures.get(i));
//                }
//            }
            final NewServiceRequest payload = new NewServiceRequest();
            payload.setAddress(address);
            payload.setServiceList(serviceId);
            payload.setVehicleId(vehicleId);
            payload.setMileage(Long.valueOf(mileage));
            payload.setPriority(spinnerPriority.getSelectedItemPosition());
            payload.setDescription(description);
            payload.setGeolocation(geolocation);
            payload.setType(serviceType);
            payload.setImageList(imageList);
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
        String outPutFile;
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
           if (picTwoLayout.getVisibility() == View.GONE){
               picTwoLayout.setVisibility(View.VISIBLE);
               Glide.with(getContext()).load(selectedImage).into(picTwo);
           }else if (picThreeLayout.getVisibility() == View.GONE){
               picThreeLayout.setVisibility(View.VISIBLE);
               Glide.with(getContext()).load(selectedImage).into(picThree);
           }else {
               picOne.setImageDrawable(null);
               Glide.with(getContext()).load(selectedImage).into(picOne);
           }
            InputStream image_stream = null;
            try {
                image_stream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//            String debug = "http://res.cloudinary.com/demo/image/facebook/c_thumb,g_face,h_90,w_120/billclinton.jpg";



            //byte array method (null object reference)
//            Bitmap imageBitmap= BitmapFactory.decodeStream(image_stream);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            imageBitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
//            byte[] byteArray = stream.toByteArray();
////            ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
//            outPutFile = Base64.encodeToString(byteArray, Base64.DEFAULT);

            //uri path method (null object reference)
//            File file = new File(((NewServiceRequestActivity) getActivity()).getPath(selectedImage));
//            System.out.println(selectedImage.toString());
            String filePath;
            try {
                String[] proj = { MediaStore.Images.Media.DATA };
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, proj,
                        null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(proj[0]);
                filePath = cursor.getString(columnIndex);
            } catch (Exception e) {
                filePath = selectedImage.getPath();
            }
            Log.v("log", "filePath is : " + filePath);
//            try {
//                File file = new File(filePath);
//                InputStream stream = new FileInputStream(file);
//                pictures.add(file.getAbsolutePath());

            System.out.println(filePath);
//            Cloudinary cloudinary = new Cloudinary(Constants.CLOUDINARY_AUTHENTICATION_URL);
            asyncUpload(filePath);

////
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            System.out.println(((NewServiceRequestActivity) getActivity()).getPath(selectedImage));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
        } else if (requestCode == RESULT_REQUEST_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            if (picTwoLayout.getVisibility() == View.GONE){
                picTwoLayout.setVisibility(View.VISIBLE);
                picTwo.setImageBitmap(imageBitmap);
            }else if(picThreeLayout.getVisibility() == View.GONE){
                picThreeLayout.setVisibility(View.VISIBLE);
                picThree.setImageBitmap(imageBitmap);
//               picThree.setImageURI(selectedImage);
            }else{
                picOne.setImageDrawable(null);
                picOne.setImageBitmap(imageBitmap);
            }
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), imageBitmap, "temp", null);
            Uri outPutUri = Uri.parse(path);
            String filePath;
            try {
                String[] proj = { MediaStore.Images.Media.DATA };
                Cursor cursor = getApplicationContext().getContentResolver().query(outPutUri, proj,
                        null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(proj[0]);
                filePath = cursor.getString(columnIndex);
            } catch (Exception e) {
                filePath = outPutUri.getPath();
            }
            asyncUpload(filePath);
        }
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

    protected void asyncUpload(String imgString){
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... imgStrings) {
                Cloudinary cloudinary = new Cloudinary(Constants.CLOUDINARY_AUTHENTICATION_URL);
                Map resultMap;
                try {
                    resultMap = cloudinary.uploader().upload(imgString, ObjectUtils.asMap("public_id",""));
                    System.out.println(resultMap.toString());
                    imageList.add(resultMap.get("url").toString());
                } catch (IOException e) {
//                    Toast.makeText(getApplicationContext(), "Cloudinary upload failed", Toast.LENGTH_SHORT).show();
                }
                System.out.println(imageList);
                return null;
            }
        };
        task.execute(imgString);
    }
}
