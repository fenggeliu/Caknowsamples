package com.caknow.customer.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.SessionPreferences;
import com.caknow.customer.util.constant.PreferenceKeys;
import com.caknow.customer.util.net.auth.AuthenticationAPI;
import com.caknow.customer.util.net.auth.AuthenticationPayload;
import com.caknow.customer.util.net.auth.AuthenticationResponse;
import com.caknow.customer.widget.BaseActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonObject;


import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by wesson_wxy on 2016/12/19.
 */

public class InitActivity extends BaseActivity implements Callback<AuthenticationResponse> {

    @Inject
    Retrofit retrofit;

    private CallbackManager facebookCallbackManager;
    private LoginManager loginManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;


    @BindView(R.id.init_layout_login_btn)
    Button signInButton;
    @BindView(R.id.init_layout_create_btn)
    Button createNewAcountButton;
    @BindView(R.id.init_layout_facebook_btn)
    LoginButton facebookLoginButton;

    // Start LoginActivity
    @OnClick(R.id.init_layout_login_btn)
    void startLogin() {
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    //Start SignUpActivity
    @OnClick(R.id.init_layout_create_btn)
    void startSignUp() {
        final Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.init_layout_facebook_btn)
//    @SuppressWarnings("unused")
    void loginWithFacebookAccount() {
        if (accessToken != null) {
            accessToken = null;
            loginManager.logOut();
//            InitActivity.this.finish();
        } else {
            loginManager.logInWithReadPermissions(InitActivity.this, Arrays.asList("email"));
//            InitActivity.this.showProgress();
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginManager = LoginManager.getInstance();
        facebookCallbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();
        loginManager.registerCallback(facebookCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Success");
                accessToken = loginResult.getAccessToken();
                InitActivity.this.submitFacebookLogin(accessToken);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    public void initContentView() {
        CAKNOWApplication.get().getNetComponent().inject(this);
        setContentView(R.layout.activity_init);
        ButterKnife.bind(this);


    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configView() {

    }

    @Override
    protected void setTitle() {

    }

    protected void submitFacebookLogin(AccessToken token) {
        JsonObject facebookJson = new JsonObject();
        facebookJson.addProperty("accountType", "FACEBOOK_USER");
        facebookJson.addProperty("token3st", token.getToken());
        RequestBody facebookRequest = RequestBody.create(MediaType.parse("application/json"), facebookJson.toString());
        AuthenticationAPI authenticationAPI = retrofit.create(AuthenticationAPI.class);
        authenticationAPI.login(facebookRequest).enqueue(this);
    }

    @Override
    public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
        if (response.isSuccessful()) {
            AuthenticationPayload authPayload = response.body().getAuthenticationPayload();

            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.ACCESS_TOKEN, authPayload.getToken());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.USER_FNAME, authPayload.getfName());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.USER_LNAME, authPayload.getlName());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.USER_ID, authPayload.get_id());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.REFRESH_TOKEN, authPayload.getRefreshToken());
            SessionPreferences.INSTANCE.setBoolPref(PreferenceKeys.BOOL_VERIFICATION_STATUS, authPayload.getVerificationStatus());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.STRIPE_TOKEN, authPayload.getStripeCusToken());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.PUBNUB_CHANNEL, authPayload.getPubnubChnl());
            SessionPreferences.INSTANCE.setStringPref(PreferenceKeys.PUBNUB_CHANNEL, authPayload.getPubnubChnl());
            InitActivity.this.hideProgress();
            final Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            InitActivity.this.hideProgress();
            this.finish();
        } else {
            InitActivity.this.hideProgress();
            Toast.makeText(InitActivity.this, "Oops an error occured!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
        Toast.makeText(InitActivity.this, "Oops an error occured!", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

}


