package com.caknow.customer.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.BuildConfig;
import com.caknow.app.R;
import com.caknow.customer.BaseActivity;
import com.caknow.customer.feedback.FeedbackActivity;
import com.caknow.customer.garage.GarageActivity;
import com.caknow.customer.garage.NewVehicleActivity;
import com.caknow.customer.garage.fragment.GarageFragment;
import com.caknow.customer.history.HistoryActivity;
import com.caknow.customer.message.MessagesActivity;
import com.caknow.customer.payment.PaymentActivity;
import com.caknow.customer.promo.PromoActivity;
import com.caknow.customer.quote.QuoteActivity;
import com.caknow.customer.settings.SettingsActivity;
import com.caknow.customer.util.PreferenceKeys;
import com.caknow.customer.util.SessionPreferences;
import com.caknow.customer.util.constant.Constants;

import com.caknow.customer.util.net.BaseRequestInterceptor;
import com.caknow.customer.webview.WebViewActivity;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnListFragmentInteractionListener{

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    DrawerLayout drawer;
    OkHttpClient client;
    public Retrofit retrofit;

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
        try {
            getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button).setVisibility(View.GONE);
        } catch(Exception e){
            //
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set up Header
        TextView nameView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.shopper_name);
        ImageView userPhoto = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.user_photo);
        userPhoto.setOnClickListener(view -> addFragment(R.id.flContent,
                new GarageFragment(),
                GarageFragment.FRAGMENT_TAG));
        nameView.setOnClickListener(view -> addFragment(R.id.flContent,
                new GarageFragment(),
                GarageFragment.FRAGMENT_TAG));
        //TODO: set user info here
        nameView.setText(SessionPreferences.INSTANCE.getStringPref(PreferenceKeys.USER_FNAME));
        userPhoto.setImageDrawable(getDrawable(R.drawable.com_facebook_profile_picture_blank_portrait));


        // Set up Footer
        TextView aboutView = (TextView) navigationView.findViewById(R.id.nav_about_us_tv);
        TextView versionView = (TextView) navigationView.findViewById(R.id.nav_version_tv);

        aboutView.setOnClickListener(view -> {
            final Intent intent = new Intent(HomeActivity.this, WebViewActivity.class);
            final Bundle bundle = new Bundle();
            bundle.putString(Constants.URL_PARCEL_KEY, "http://www.caknow.com");
            intent.putExtras(bundle);
            startActivity(intent);
        });
        versionView.setText("Version ".concat(BuildConfig.VERSION_NAME));

    }

    @OnClick(R.id.user_photo)
    void returnHome() {
        addFragment(R.id.flContent,
                new GarageFragment(),
                GarageFragment.FRAGMENT_TAG);
    }

    @OnClick(R.id.shopper_name)
    void returnHome2() {
        addFragment(R.id.flContent,
                new GarageFragment(),
                GarageFragment.FRAGMENT_TAG);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (client == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addNetworkInterceptor(new StethoInterceptor());
            httpClient.addInterceptor(new BaseRequestInterceptor());
            httpClient.addNetworkInterceptor(chain -> {
                Request request = chain.request();
                long t1 = 0 ,t2 = 0;
                try {
                     t1 = System.nanoTime();
                    Log.d("OkHttp", String.format("Sending %s request %s on %s%n%s",
                            request.method(), request.url(), chain.connection(), request.headers()));
                } catch (Exception e){
                    //
                }
                Response response = chain.proceed(request);
                try {
                    t2 = System.nanoTime();
                    Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s",
                            response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                } catch (Exception e){
                    //
                }
                return response;
            });

            client = httpClient.build();
        }
        if (retrofit == null) {
            Gson gson = new GsonBuilder().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

        }

    }


    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Garage");
        } catch (NullPointerException e){
            //
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.flContent,
                new GarageFragment(),
                GarageFragment.FRAGMENT_TAG);
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
            Intent intent = new Intent(this, NewVehicleActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_messages) {
            Intent intent = new Intent(this, MessagesActivity.class);
            startActivity(intent);
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


    @Override
    public void onListFragmentInteraction(HomeCardItem item) {
        Intent intent = new Intent(this, QuoteActivity.class);
        startActivity(intent);
    }


}
