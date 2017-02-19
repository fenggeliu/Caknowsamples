package com.caknow.customer.transaction;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.CAKNOWApplication;
import com.caknow.customer.payment.PaymentActivity;
import com.caknow.customer.service.model.VehicleServiceInterface;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.payment.PaymentAPI;
import com.caknow.customer.util.net.quote.GetQuotesByServiceId;
import com.caknow.customer.util.net.quote.Quote;
import com.caknow.customer.util.net.service.ServiceAPI;
import com.caknow.customer.util.net.service.quotes.PriceDetail;
import com.caknow.customer.util.net.service.quotes.QuoteList;
import com.caknow.customer.util.net.transaction.PaymentRequest;
import com.caknow.customer.widget.BaseActivity;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by junu on 1/2/2017.
 */

public class TransactionActivity extends BaseActivity implements Callback<ResponseBody>{

    static final int PAYMENT_SELECTED_REQUEST_CODE = 1;  // The request code
    static final int PAYMENT_CANCELLED_REQUEST_CODE = 2;  // The request code

    boolean paymentMode = false;
    @Inject
    Retrofit retrofit;

    Quote quote;
    QuoteList mapQuote;
    Bundle extras;
    VehicleServiceInterface item;
    String serviceRequestId;
    String promotionCodes = "";
    private String selectedPaymentSource;
    @Override
    protected void initContentView() {

        setContentView(R.layout.activity_transaction);
        CAKNOWApplication.get().getNetComponent().inject(this);
        ButterKnife.bind(this);
        extras = getIntent().getExtras();

        serviceRequestId = extras.getString(Constants.SERVICE_REQUEST_ID_PARCEL_KEY);
        item = extras.getParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY);
        try {
            paymentMode = extras.getBoolean("paymentMode");
        } catch(Exception e){

        }



    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if(paymentMode) {
            quote = extras.getParcelable(Constants.TOP_QUOTE_ITEM_ID_PARCEL_KEY);
            mapQuote = extras.getParcelable(Constants.SELECTED_QUOTE_ITEM_ID_PARCEL_KEY);
            TransactionDetailsFragment fragment = new TransactionDetailsFragment();
            final Bundle args = new Bundle();
            args.putParcelable(Constants.TOP_QUOTE_ITEM_ID_PARCEL_KEY, quote);
            args.putParcelable(Constants.SELECTED_QUOTE_ITEM_ID_PARCEL_KEY, mapQuote);
            args.putParcelable(Constants.JOB_FRAGMENT_SERVICE_ITEM_PARCEL_KEY, item);
            args.putBoolean("paymentMode", paymentMode);
            fragment.setArguments(args);
            addFragment(R.id.transactionContent, fragment, TransactionDetailsFragment.FRAGMENT_TAG);
        } else{
            retrofit.create(ServiceAPI.class).getQuotesForId(serviceRequestId).enqueue(new Callback<GetQuotesByServiceId>() {
                @Override
                public void onResponse(Call<GetQuotesByServiceId> call, Response<GetQuotesByServiceId> response) {
//                    List<Quote> quotes = response.body().getGetQuotesByServiceIdPayload().getTopQuote();
//                    quotes.addAll(response.body().getGetQuotesByServiceIdPayload().getQuotes());
//                    Quote acceptedQuote = null;
//                    for(int i=0; i<quotes.size(); i++){
//                        if(quotes.get(i).getStatus().equalsIgnoreCase("accepted")){
//                            acceptedQuote = quotes.get(i);
//                            break;
//                        }
//                    }

                    TransactionDetailsFragment fragment = new TransactionDetailsFragment();
                    final Bundle args = new Bundle();
                    args.putParcelable(Constants.QUOTE_ITEM_ID_PARCEL_KEY, response.body());
                    args.putBoolean("paymentMode", paymentMode);
                    fragment.setArguments(args);
                    addFragment(R.id.transactionContent, fragment, TransactionDetailsFragment.FRAGMENT_TAG);
                }

                @Override
                public void onFailure(Call<GetQuotesByServiceId> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT);
                }
            });

        }
    }

    @Override
    protected void configView() {

    }

    @Override
    protected void setTitle() {
        try {
            ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.mytext)).setText("Service Fee");
            ((ImageView)getSupportActionBar().getCustomView().findViewById(R.id.custom_ab_home_button)).setImageResource(R.drawable.ic_action_close);
        } catch (NullPointerException e){
            //
        }
    }

    private void selectPayment(){
        final Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("type", "payment");
        startActivityForResult(intent, PAYMENT_SELECTED_REQUEST_CODE);
    }

    private void confirmPayment(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //List<PriceDetail> priceDetails = quote.getItemizedAmounts();
        if (mapQuote != null ) {

            List<PriceDetail> priceDetails = mapQuote.getPriceDetails();
            PriceDetail totalPriceDetail = null;
            for (int i = priceDetails.size() - 1; i > -1; i--) {
                if (priceDetails.get(i).getPriceItem().equalsIgnoreCase("total")) {
                    totalPriceDetail = priceDetails.get(i);
                    break;
                }
            }
            if (totalPriceDetail != null) {
                String message = String.format("Are you sure you want to accept the quoted price of %s", totalPriceDetail.getPrice());
                alertDialogBuilder.setTitle("Confirm");
                alertDialogBuilder.setMessage(message);
                alertDialogBuilder.setPositiveButton("OK", (dialogInterface, i) -> {
                    try {
                        makePayment();
                    } catch (Exception e) {
                    }
                });
                alertDialogBuilder.setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());

                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.show();
            }
        }else{
            List<PriceDetail> priceDetails = quote.getItemizedAmounts();
            PriceDetail totalPriceDetail = null;
            for (int i = priceDetails.size() - 1; i > -1; i--) {
                if (priceDetails.get(i).getPriceItem().equalsIgnoreCase("total")) {
                    totalPriceDetail = priceDetails.get(i);
                    break;
                }
            }
            if (totalPriceDetail != null) {
                String message = String.format("Are you sure you want to pay the amount of %s to the shop?", totalPriceDetail.getPrice());
                alertDialogBuilder.setTitle("Confirm");
                alertDialogBuilder.setMessage(message);
                alertDialogBuilder.setPositiveButton("OK", (dialogInterface, i) -> {
                    try {
                        payToShop();
                    } catch (Exception e) {
                    }
                });
                alertDialogBuilder.setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());

                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PAYMENT_SELECTED_REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                selectedPaymentSource =  data.getExtras().getString(Constants.PAYMENT_SOURCE_PARCEL_KEY);
                if(!selectedPaymentSource.isEmpty()){
                    confirmPayment();
                }
                // Do something with the contact here (bigger example below)
            }
        }
    }

    public void makePayment(){
        showProgress();
        final PaymentRequest request = new PaymentRequest(serviceRequestId, mapQuote.getQuoteId(), "stripe", selectedPaymentSource);
        retrofit.create(PaymentAPI.class).makePayment(PaymentRequest.getRequestBody(request)).enqueue(this);

    }

    public void payToShop(){
        showProgress();
        JsonObject payment = new JsonObject();
        payment.addProperty("serviceRequestId", serviceRequestId);
        payment.addProperty("promotionCodes", promotionCodes);
        final RequestBody request = RequestBody.create(MediaType.parse("application/json"), payment.toString());
        retrofit.create(PaymentAPI.class).payToShop(request).enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                try{
                    hideProgress();
                        Toast.makeText(TransactionActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                } catch(Exception e){
                    // UI Events are not thread safe
                }

            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();

    }
}
