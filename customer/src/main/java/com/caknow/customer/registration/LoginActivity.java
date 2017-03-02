package com.caknow.customer.registration;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.BuildConfig;
import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.home.HomeActivity;
import com.caknow.customer.util.constant.PreferenceKeys;
import com.caknow.customer.util.SessionPreferences;
import com.caknow.customer.util.net.auth.AuthenticationAPI;
import com.caknow.customer.util.net.auth.AuthenticationPayload;
import com.caknow.customer.util.net.auth.AuthenticationResponse;
import com.caknow.customer.util.net.content.LoginRequestPayload;
import com.caknow.customer.widget.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements Callback<AuthenticationResponse> {


    private boolean securePass = true;

    @Inject
    Retrofit retrofit;
    private String email;
    private String password;
    // UI references.
    @BindView(R.id.login_user_pwd) EditText mPasswordView;
    @BindView(R.id.login_user_email) EditText mEmailView;
    @BindView(R.id.login_progress) View mProgressView;
    @BindView(R.id.login_layout_sign_in_btn) Button mLoginFormView;

    @OnClick(R.id.login_layout_sign_in_btn)
    void loginClicked() {
        showProgress();
        // prepare call in Retrofit 2.0
        AuthenticationAPI authenticationAPI = retrofit.create(AuthenticationAPI.class);
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){


            Call<AuthenticationResponse> call = authenticationAPI.login(LoginRequestPayload.getRequestBody(new LoginRequestPayload(email, password)));
            //asynchronous call
            call.enqueue(this);
        } else{
            Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_SHORT);
        }

    }

    @OnClick(R.id.login_show_password_icon)
    void toggleShowPassword(){
        if(securePass){
            mPasswordView.setTransformationMethod(new TransformationMethod() {
                @Override
                public CharSequence getTransformation(CharSequence charSequence, View view) {
                    return charSequence;
                }

                @Override
                public void onFocusChanged(View view, CharSequence charSequence, boolean b, int i, Rect rect) {

                }
            });
        }else{
            mPasswordView.setTransformationMethod(new PasswordTransformationMethod() );
        }
        securePass = !securePass;

    }

    @Override
    protected void initContentView() {
        try {
            TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext);
            title.setText("Sign In");
//            ((ImageView) getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (Exception e) {
            //
        }
    }

    @Override
    protected void initView() {


    }

    private void displayKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        }
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
            ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Sign In");
//            ((ImageView) getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e) {
            //
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        CAKNOWApplication.get().getNetComponent().inject(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEmailView.setOnClickListener(v -> displayKeyboard(v));
        mEmailView.requestFocus();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.blue_title));
        }

        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.login_user_pwd || id == EditorInfo.IME_NULL) {
                loginClicked();
                return true;
            }
            return false;
        });


    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }




    @Override
    public void onResponse(Call<AuthenticationResponse> call, retrofit2.Response<AuthenticationResponse> response) {
        hideProgress();
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

            final Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();
        }
        else{
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            mPasswordView.requestFocus();
        }
    }

    @Override
    public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
        hideProgress();
        Toast.makeText(LoginActivity.this, "Oops an error occured!", Toast.LENGTH_SHORT).show();

    }


}

