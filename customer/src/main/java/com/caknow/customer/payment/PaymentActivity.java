package com.caknow.customer.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.widget.BaseActivity;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.payment.GetPaymentsResponse;
import com.caknow.customer.util.net.payment.PaymentAPI;
import com.caknow.customer.util.net.payment.model.PaymentMethodItem;
import com.caknow.customer.util.net.payment.model.PaymentsPayload;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/2/2017.
 */

public class PaymentActivity extends BaseActivity implements PaymentMethodFragment.OnListFragmentInteractionListener, Callback<GetPaymentsResponse> {
    ArrayList<PaymentMethodItem> data;

    boolean paymentMode = false;
    String type = "";

    @Inject
    Retrofit retrofit;

    private PaymentAPI paymentAPI;
    @Override
    protected void initContentView() {
        CAKNOWApplication.get().getNetComponent().inject(this);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        try {
            type = getIntent().getExtras().getString(Constants.PAYMENT_TYPE_PARCEL_KEY);
            if(type.equalsIgnoreCase("payment")){
                paymentMode = true;
            }

        } catch(Exception e){

        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        paymentAPI = retrofit.create(PaymentAPI.class);
        getCards();

    }

    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Credit Cards");
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }

    @Override
    public void onListFragmentInteraction(PaymentMethodItem item) {
        if(paymentMode){
            selectPaymentForTransaction(item.getId());
        }
    }

    public void getCards(){
        Call<GetPaymentsResponse> call = paymentAPI.getPayments();
        call.enqueue(this);
    }

    public void deleteCard(String provider, String id){
        paymentAPI.deletePayments(provider, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(PaymentActivity.this, "Delete Card Success", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Delete Card Failure", Toast.LENGTH_SHORT).show();

            }
        });
        recreate();

    }

    private void loadPaymentMethodsFragment(){
        PaymentMethodFragment fragment = new PaymentMethodFragment();
        final Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.PAYMENT_METHOD_LIST_PARCEL_KEY, data);
        fragment.setArguments(args);
        addFragment(R.id.paymentContent, fragment, PaymentMethodFragment.FRAGMENT_TAG);

    }

    @Override
    public void onResponse(Call<GetPaymentsResponse> call, Response<GetPaymentsResponse> response) {
        try {
            if(!response.isSuccessful()){
                Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_LONG).show();
            }
            PaymentsPayload payment = response.body().getPayload();
            data = new ArrayList<>();
            data.addAll(payment.getPaymentSources().getData());
            loadPaymentMethodsFragment();
        } catch(Exception e){
            // Not Thread Safe
        }
    }

    @Override
    public void onFailure(Call<GetPaymentsResponse> call, Throwable t) {
        try {
            Toast.makeText(this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e){

        }
    }

    private void selectPaymentForTransaction(String paymentSource){
        Intent result = new Intent();
        result.putExtra(Constants.PAYMENT_SOURCE_PARCEL_KEY, paymentSource);
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
