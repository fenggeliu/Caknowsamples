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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.caknow.customer.settings.fragment.SettingsFragment.HINT_KEY;
import static com.caknow.customer.settings.fragment.SettingsFragment.TITLE_KEY;

/**
 * Created by junu on 1/1/17.
 */

public class UpdateSettingFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + UpdateSettingFragment.class.getName();
    @BindView(R.id.setting_update_title_tv)
    TextView titleView;
    @BindView(R.id.setting_update_et)
    EditText updateField;
    private String title, hint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_setting, container, false);
        final Bundle bundle = getArguments();
        unbinder = ButterKnife.bind(this, v);

        if (bundle != null) {
            title = bundle.getString(TITLE_KEY, "Unknown");
            hint = bundle.getString(HINT_KEY, "Hint");
            titleView.setText(title);
            updateField.setHint(hint);
        }
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((SettingsActivity) getActivity()).updateTitle("Phone Number");
    }


}
