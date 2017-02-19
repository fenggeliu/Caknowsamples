package com.caknow.customer.settings.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.settings.SettingsActivity;
import com.caknow.customer.util.net.settings.SettingsAPI;
import com.caknow.customer.util.net.settings.UpdatePasswordRequest;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/1/17.
 */

public class UpdatePasswordFragment extends BaseFragment implements Callback<ResponseBody> {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + UpdatePasswordFragment.class.getName();

    @BindViews({R.id.update_password_current, R.id.update_password_new, R.id.update_password_new_coonfirm})
    List<EditText> passwordFields;

    @OnClick(R.id.update_password_submit_button)
    void submit(){
        ((SettingsActivity)getActivity()).showProgress();
        if(validateFields()){
            RequestBody body = UpdatePasswordRequest.getRequestBody(new UpdatePasswordRequest(passwordFields.get(0).getText().toString(), passwordFields.get(1).getText().toString()));
            retrofit.create(SettingsAPI.class).updatePassword(body).enqueue(this);
        } else{
            ((SettingsActivity)getActivity()).hideProgress();
        }
    }

//    @Inject
//    Retrofit retrofit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_updatepassword, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        ((SettingsActivity) getActivity()).updateTitle("Password", R.drawable.ic_action_back);
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try{
            ((SettingsActivity)getActivity()).hideProgress();
        } catch(Exception e){
            //Not thread safe
        }
        if(response.isSuccessful()){
            Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        try{
            ((SettingsActivity)getActivity()).hideProgress();
        } catch(Exception e){
            //Not thread safe
        }
    }


    private boolean validateFields(){
        boolean success = true;
        for(EditText editText : passwordFields){
            if(editText.getText().toString().isEmpty()){
                success = false;
                Toast.makeText(getContext(), "Please check all fields and make sure they are filled out.", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        if(!passwordFields.get(1).getText().toString().matches(passwordFields.get(1).getText().toString())){
            Toast.makeText(getContext(), "Please make sure the password are the same", Toast.LENGTH_SHORT).show();
            success = false;
        }

        return success;
    }
}
