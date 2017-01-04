package com.caknow.customer.settings;


import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.caknow.customer.BaseActivity;
import com.caknow.app.R;
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
        try {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.abs_layout);
            getSupportActionBar().setHomeButtonEnabled(true);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setIcon(R.drawable.ic_menu_back);
        } catch (NullPointerException e){
            //
        }
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
}
