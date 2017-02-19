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
import com.caknow.customer.util.net.settings.UpdateSettingRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.caknow.customer.settings.fragment.SettingsFragment.HINT_KEY;
import static com.caknow.customer.settings.fragment.SettingsFragment.TITLE_KEY;

/**
 * Created by junu on 1/1/17.
 */

public class UpdateSettingFragment extends BaseFragment implements Callback<ResponseBody> {



    @BindView(R.id.setting_update_et)
    EditText updateField;

    @OnClick(R.id.setting_update_submit_btn)
    void updateField(){

        if(!(updateField.getText().toString().isEmpty())) {
            RequestBody body = UpdateSettingRequest.getRequestBody(new UpdateSettingRequest(updateField.getText().toString()));

            retrofit.create(SettingsAPI.class).updateProfile(body).enqueue(this);
        } else{
            Toast.makeText(getContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
        }
    }


    private String title, hint;
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + UpdateSettingFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_setting, container, false);
        final Bundle bundle = getArguments();
        unbinder = ButterKnife.bind(this, v);

        if(bundle != null) {
            title = bundle.getString(TITLE_KEY, "Unknown");
            hint = bundle.getString(HINT_KEY, "Hint");
            updateField.setHint(hint);
        }
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
       ((SettingsActivity) getActivity()).updateTitle("Phone", R.drawable.ic_action_back);
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if(response.isSuccessful()){
            getActivity().onBackPressed();
        }else{
            Toast.makeText(getContext(), "Not a Valid Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Toast.makeText(getContext(), "Not a Valid Phone Number", Toast.LENGTH_SHORT).show();
    }
}
