package com.caknow.android.customer.app.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.caknow.android.customer.app.activity.BaseActivity;
import com.caknow.android.customer.app.activity.FeedbackActivity;
import com.caknow.android.customer.app.activity.GarageActivity;
import com.caknow.android.customer.app.activity.HistoryActivity;
import com.caknow.android.customer.app.activity.PaymentActivity;
import com.caknow.android.customer.app.activity.PromoActivity;
import com.caknow.android.customer.app.fragment.HomeFragment;
import com.caknow.android.customer.app.settings.SettingsActivity;
import com.caknow.app.BuildConfig;
import com.caknow.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView.LayoutManager mLayoutManager;
    private HomeCardAdapter mAdapter;
    private NavigationView navigationView;
    @BindView(R.id.home_recyclerview) RecyclerView mRecyclerView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    private int lastCheckedItem = R.id.nav_messages;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

//        navigationView.setCheckedItem(R.id.nav_messages);
        TextView nameView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.shopper_name);
        TextView versionView = (TextView) navigationView.findViewById(R.id.nav_version_tv);
        ImageView userPhoto = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.user_photo);
        nameView.setText("success!");
        versionView.setText("Version ".concat(BuildConfig.VERSION_NAME));

    }

    @OnClick(R.id.user_photo)
    void returnHome(){
        addFragment(R.id.flContent,
                new HomeFragment(),
                HomeFragment.FRAGMENT_TAG);
    }

    @OnClick(R.id.shopper_name)
    void returnHome2(){
        addFragment(R.id.flContent,
                new HomeFragment(),
                HomeFragment.FRAGMENT_TAG);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        List<HomeCardItem> items = new ArrayList<>();
        mAdapter = new HomeCardAdapter(items);

    }

    @Override
    protected void configView() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.flContent,
                new HomeFragment(),
                HomeFragment.FRAGMENT_TAG);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_start_new_service) {
            final Intent intent = new Intent(this, GarageActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_messages) {
            addFragment(R.id.flContent,
                    new HomeFragment(),
                    HomeFragment.FRAGMENT_TAG);
        } else if (id == R.id.nav_payment) {
            Intent intent = new Intent(this, PaymentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_promo) {
            Intent intent = new Intent(this, PromoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            final Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(this, FeedbackActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        // Returning false to leave item unchecked
        return false;
    }




}
