package com.caknow.customer.settings;


import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.caknow.app.R;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.settings.fragment.SettingsFragment;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends BaseActivity {

    @BindString(R.string.settings_layout_title)
    String titleString;

    @BindView(R.id.mytext)
    TextView titleTv;
    @BindView(R.id.settingsContent)
    FrameLayout content;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        titleTv.setText(titleString);

        addFragment(R.id.settingsContent, new SettingsFragment(), SettingsFragment.FRAGMENT_TAG);
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
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Settings");
            ((ImageButton)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }
}
