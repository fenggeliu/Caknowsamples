package com.caknow.customer.settings.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caknow.app.R;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.job.JobActivity;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.settings.ConsumerInfoResponse;
import com.caknow.customer.util.net.settings.SettingsAPI;
import com.caknow.customer.util.net.settings.UpdatePasswordRequest;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.registration.InitActivity;
import com.caknow.customer.settings.SettingsActivity;
import com.caknow.customer.util.SessionPreferences;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.caknow.customer.home.HomeActivity.PROFILE_IMAGE_UPDATE;
import static com.facebook.FacebookSdk.getApplicationContext;
import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static java.lang.String.format;

/**
 * Created by junu on 1/1/17.
 */

public class SettingsFragment extends BaseFragment {
    static final String TITLE_KEY = "title";
    static final String HINT_KEY = "hint";
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + SettingsFragment.class.getName();
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int RESULT_LOAD_IMAGE = 1;

    @BindView(R.id.settings_layout_username_text) TextView usernameContent;
    @BindView(R.id.settings_layout_photo_imageview) ImageView userPhotoImageView;

    @BindView(R.id.settings_layout_email_content) TextView emailContent;
    @BindView(R.id.settings_layout_phone_content) TextView phoneContentView;

    SettingsActivity settingsActivity;

    @OnClick(R.id.settings_layout_phone_layout)
    void openPhoneSetting(){
        UpdateSettingFragment updateFragment = new UpdateSettingFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, "Phone");
        bundle.putString(HINT_KEY, "Enter your phone number");
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.replaceFragment(R.id.settingsContent, updateFragment, UpdateSettingFragment.FRAGMENT_TAG, "phone");
    }

    @OnClick(R.id.settings_layout_password_layout)
    void openPasswordSetting(){
        UpdatePasswordFragment updatePasswordFragment = new UpdatePasswordFragment();
        final Bundle bundle = new Bundle();

        bundle.putString(TITLE_KEY, "Change Password");
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.replaceFragment(R.id.settingsContent, updatePasswordFragment, UpdatePasswordFragment.FRAGMENT_TAG, "password");
    }

    @OnClick(R.id.settings_layout_car_layout)
    void openGarage(){
        ManageCarFragment manageCarFragment = new ManageCarFragment();
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.replaceFragment(R.id.settingsContent, manageCarFragment, ManageCarFragment.FRAGMENT_TAG, "manage_Car");
    }

    @OnClick(R.id.settings_sign_out_container)
    void signOut(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        String message = format("Are you sure you want to sign out?");
        alertDialogBuilder.setTitle("Sign Out");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK", (dialogInterface, i) -> {
            try {
                if (getActivity() != null && !getActivity().isFinishing()) {
                    SessionPreferences.INSTANCE.resetCredentials();
                    final Intent intent = new Intent(getActivity(), InitActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            } catch (NullPointerException e){
                //
            }
        });
        alertDialogBuilder.setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());

        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.show();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, v);
        settingsActivity = (SettingsActivity) getActivity();
        SettingsAPI settingsAPI = retrofit.create(SettingsAPI.class);
        settingsAPI.getConsumerInfo().enqueue(new Callback<ConsumerInfoResponse>() {
            @Override
            public void onResponse(Call<ConsumerInfoResponse> call, Response<ConsumerInfoResponse> response) {
                ConsumerInfoResponse responseBody = response.body();
                emailContent.setText(responseBody.getPayload().getEmail());
                phoneContentView.setText(responseBody.getPayload().getPhone());
                usernameContent.setText(responseBody.getPayload().getFName() + " " + responseBody.getPayload().getLName());
                Glide.with(getContext()).load(responseBody.getPayload().getProfileImg()).into(userPhotoImageView);
            }

            @Override
            public void onFailure(Call<ConsumerInfoResponse> call, Throwable t) {

            }
        });
        userPhotoImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
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
                return true;
            }
        });


        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
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
        }
    }

    protected void asyncUpload(String imgString){
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... imgStrings) {
                Cloudinary cloudinary = new Cloudinary(Constants.CLOUDINARY_AUTHENTICATION_URL);
                String url = "";
                Map resultMap;
                try {
                    resultMap = cloudinary.uploader().upload(imgString, ObjectUtils.asMap("public_id","profile_picture"));
                    System.out.println(resultMap.toString());
                    url = resultMap.get("url").toString();
                } catch (IOException e) {
//                    Toast.makeText(getApplicationContext(), "Cloudinary upload failed", Toast.LENGTH_SHORT).show();
                }
                JsonObject newPicture = new JsonObject();
                newPicture.addProperty("profileImg", url);
                RequestBody imageRequest = RequestBody.create(MediaType.parse("application/json"), newPicture.toString());
                retrofit.create(SettingsAPI.class).uploadProfileImage(imageRequest).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            getActivity().finish();
                            startActivityForResult(new Intent(getActivity(), HomeActivity.class), PROFILE_IMAGE_UPDATE);
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                return null;
            }
        };
        task.execute(imgString);
    }
}


