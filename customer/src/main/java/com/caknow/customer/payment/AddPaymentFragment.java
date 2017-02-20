package com.caknow.customer.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.caknow.app.R;
import com.caknow.customer.widget.BaseFragment;
import com.caknow.customer.util.constant.Constants;
import com.caknow.customer.util.net.payment.AddPaymentRequest;
import com.caknow.customer.util.net.payment.AddPaymentResponse;
import com.caknow.customer.util.net.payment.PaymentAPI;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.exception.AuthenticationException;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junu on 1/1/17.
 */

public class AddPaymentFragment extends BaseFragment implements Callback<AddPaymentResponse> {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + AddPaymentFragment.class.getName();
    public final int MY_SCAN_REQUEST_CODE = 1;
    @BindView(R.id.ancl_card_number_input)
    EditText ccNum;
    @BindView(R.id.ccExpMM)
    EditText ccExpM;
    @BindView(R.id.ccExpYY)
    EditText ccExpY;
    @BindView(R.id.ccCVV)
    EditText ccCVV;
    @BindView(R.id.ccName)
    EditText cardholderName;
    @BindView(R.id.ancl_camera_btn)
    TextView camera;


    @OnClick(R.id.ancl_camera_btn)
    void onScanPressed(){
        Intent scanIntent = new Intent(getContext(), CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";
                ccNum.setText(scanResult.cardNumber);
                cardholderName.setText(scanResult.cardholderName);
                ccExpM.setText(scanResult.expiryMonth);
                ccExpY.setText(scanResult.expiryYear);
            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
        }
        // else handle other activity results
    }


    @OnClick(R.id.ancl_add_card_btn)
    void addCard(){

        try {
            ((PaymentActivity)getActivity()).showProgress();

            String cardHolderName = cardholderName.getText().toString();

            Card.Builder builder = new Card.Builder(ccNum.getText().toString(),
                    Integer.valueOf(ccExpM.getText().toString()),
                    2000+Integer.valueOf(ccExpY.getText().toString()),
                    ccCVV.getText().toString()).name(cardHolderName);

            final Card card = builder.build();

            //Stripe stripe = new Stripe(SessionPreferences.INSTANCE.getStringPref(PreferenceKeys.STRIPE_TOKEN));
            Stripe stripe = new Stripe(Constants.stripePublicKeyCaknowTest);
            stripe.createToken(card, new TokenCallback() {
                @Override
                public void onError(Exception error) {
                    // Show localized error message
                    Toast.makeText(getContext(),
                            error.getLocalizedMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                    ((PaymentActivity)getActivity()).hideProgress();
                }

                @Override
                public void onSuccess(Token token) {
                    ((PaymentActivity)getActivity()).hideProgress();
                    Call<AddPaymentResponse> call = retrofit.create(PaymentAPI.class).addPayment(AddPaymentRequest.getRequestBody(new AddPaymentRequest(token.getId())));
                    call.enqueue(AddPaymentFragment.this);

                }
            });
        } catch (AuthenticationException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_payment, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }


    @Override
    public void onResponse(Call<AddPaymentResponse> call, Response<AddPaymentResponse> response) {
        boolean success = false;
        try{
            success = response.isSuccessful();
            success = success && response.body().isSuccess() ;
        } catch(Exception e){
            // Not thread safe
        }
        if(success){
            ((PaymentActivity)getActivity()).hideProgress();
            Intent intent;
            intent = new Intent(getActivity(), PaymentActivity.class);
            getActivity().finish();
            startActivity(intent);
        }
        else{
            Toast.makeText(getContext(),
                    response.body().getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    @Override
    public void onFailure(Call<AddPaymentResponse> call, Throwable t) {
        Toast.makeText(getContext(),
                t.getLocalizedMessage(),
                Toast.LENGTH_LONG
        ).show();
    }
}
