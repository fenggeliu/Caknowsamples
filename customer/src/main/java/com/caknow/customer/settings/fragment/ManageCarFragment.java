package com.caknow.customer.settings.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseFragment;
import com.caknow.customer.settings.SettingsActivity;
import com.caknow.customer.util.net.garage.GarageAPI;
import com.caknow.customer.util.net.garage.GarageResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.caknow.customer.settings.fragment.SettingsFragment.HINT_KEY;
import static com.caknow.customer.settings.fragment.SettingsFragment.TITLE_KEY;

/**
 * Created by junu on 1/1/17.
 */

public class ManageCarFragment extends BaseFragment implements Callback<GarageResponse> {

    @BindView(R.id.setting_update_title_tv)
    TextView titleView;

    @BindView(R.id.setting_update_et)
    EditText updateField;

    private String title, hint;
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ManageCarFragment.class.getName();
    SettingsActivity settingsActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_setting, container, false);
        final Bundle bundle = getArguments();
        unbinder = ButterKnife.bind(this, v);
        settingsActivity = (SettingsActivity) getActivity();
        if(bundle != null) {
            title = bundle.getString(TITLE_KEY, "Unknown");
            hint = bundle.getString(HINT_KEY, "Hint");
            titleView.setText(title);
            updateField.setHint(hint);
        }
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        getActivity().setTitle("History");
    }

    private void loadData(){
        // prepare call in Retrofit 2.0
        if(settingsActivity != null) {
            GarageAPI garageAPI = settingsActivity.retrofit.create(GarageAPI.class);
            Call<GarageResponse> call = garageAPI.getVehicles();
            //asynchronous call
            call.enqueue(this);

            // synchronous call would be with execute, in this case you
            // would have to perform this outside the main thread
//         call.execute();

            // to cancel a running request
            // call.cancel();
            // calls can only be used once but you can easily clone them
            //Call<StackOverflowQuestions> c = call.clone();
            //c.enqueue(this);
        }
    }


    @Override
    public void onResponse(Call<GarageResponse> call, Response<GarageResponse> response) {

    }

    @Override
    public void onFailure(Call<GarageResponse> call, Throwable t) {

    }
}
